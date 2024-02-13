package testClasses;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.FieldScope;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.github.victools.jsonschema.module.swagger2.Swagger2Module;
import com.hemosoft.jsonSchema.utils.Conditional;
import com.hemosoft.jsonSchema.utils.FormField;

public class OneOff {

    public static void main(String[] args) throws Exception {
        JacksonModule module = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        Swagger2Module module2 = new Swagger2Module();
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12,
                OptionPreset.PLAIN_JSON).with(module).with(module2)
                .with(Option.EXTRA_OPEN_API_FORMAT_VALUES).with(Option.ADDITIONAL_FIXED_TYPES);

        configBuilder.forFields()
                .withDependentRequiresResolver(field -> Optional
                        .ofNullable(field.getAnnotationConsideringFieldAndGetter(FormField.class))
                        .map(FormField::dependentRequired)
                        .filter(dependentRequired -> dependentRequired.length > 0)
                        .map(dependentRequired -> Arrays.asList(dependentRequired))
                        .orElse(null));

        configBuilder.forFields()
                .withAdditionalPropertiesResolver((scope, X) -> resolveAdditionalAttributes(scope));
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(WorkPackageUpdateRequest.class);
        addDefaultValues(jsonSchema);
        // System.out.println(jsonSchema.toPrettyString());

    }

    private static JsonNode resolveAdditionalAttributes(FieldScope scope) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        Conditional formField = scope.getAnnotationConsideringFieldAndGetter(Conditional.class);

        if(formField!=null){
            
        }
        return null;
    }

    private static void addDefaultValues(JsonNode schemaNode) {

        if (schemaNode.has("properties") && schemaNode.get("properties").has("gender")) {
            System.out.println(schemaNode.get("properties").toPrettyString());
            // ((ObjectNode) schemaNode.get("properties").get("gender")).put("default",
            // "FEMALE");
        }
        // You can add default values for other properties in a similar manner
    }
}
