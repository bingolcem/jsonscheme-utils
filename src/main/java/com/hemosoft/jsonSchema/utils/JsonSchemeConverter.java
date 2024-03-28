package com.hemosoft.jsonSchema.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.imifou.jsonschema.module.addon.AddonModule;
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

public class JsonSchemeConverter {

  public JsonNode getModel(Class clazz) {
    ObjectMapper objectMapper = new ObjectMapper();
    AddonModule module3 = new AddonModule();
    JacksonModule module = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
    Swagger2Module module2 = new Swagger2Module();
    SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(objectMapper,
        SchemaVersion.DRAFT_2020_12,
        OptionPreset.PLAIN_JSON).with(module).with(module2).with(module3)
        .with(Option.EXTRA_OPEN_API_FORMAT_VALUES).with(Option.ADDITIONAL_FIXED_TYPES)
        .with(Option.ALLOF_CLEANUP_AT_THE_END);

    configBuilder.forFields()
        .withReadOnlyCheck(field -> resolveReadOnlyCheck(field))
        .withStringFormatResolver(field -> format(field))
        .withAdditionalPropertiesResolver((scope, X) -> resolveAdditionalAttributes(scope))
        .withTitleResolver(field -> resolveTitle(field))
        .withDependentRequiresResolver(field -> Optional
            .ofNullable(field.getAnnotationConsideringFieldAndGetter(FormField.class))
            .map(FormField::dependentRequired)
            .filter(dependentRequired -> dependentRequired.length > 0)
            .map(dependentRequired -> Arrays.asList(dependentRequired))
            .orElse(null));
    // .withInstanceAttributeOverride(new
    // MandatoryCompanyPropertyAttributeOverride());

    configBuilder.forFields()
        .withDescriptionResolver(field -> extracted(field));

    configBuilder.forTypesInGeneral()
        .withPropertySorter(Comparator.comparing(member -> Optional.ofNullable(member.getAnnotation(FormField.class))
            .map(FormField::order)
            .orElse(0)));

    configBuilder.forTypesInGeneral()
        .withDescriptionResolver(scope -> scope.getType().getErasedType() == clazz ? "main schema description" : null);

    SchemaGeneratorConfig config = configBuilder.build();
    SchemaGenerator generator = new SchemaGenerator(config);
    JsonNode jsonSchema = generator.generateSchema(clazz);

    return jsonSchema;

  }

  private String extracted(FieldScope field) {
    FormField formField = field.getAnnotationConsideringFieldAndGetter(FormField.class);
    return (formField != null && !formField.description().equals("")) ? formField.description() : null;
  }

  private String format(FieldScope field) {
    FormField formField = field.getAnnotationConsideringFieldAndGetter(FormField.class);
    return (formField != null && !formField.format().equals("")) ? formField.format() : null;
  }

  private boolean resolveReadOnlyCheck(FieldScope field) {
    FormField formField = field.getAnnotationConsideringFieldAndGetter(FormField.class);
    return formField != null ? formField.readOnly() : false;
  }

  private String resolveTitle(FieldScope field) {
    FormField formField = field.getAnnotationConsideringFieldAndGetter(FormField.class);
    return formField != null && !formField.title().equals("") ? formField.title() : field.getName();
  }

  private JsonNode resolveAdditionalAttributes(FieldScope fieldscope) {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = null;
    FormField formField = fieldscope.getAnnotationConsideringFieldAndGetter(FormField.class);
    LookUp lookUp = fieldscope.getAnnotationConsideringFieldAndGetter(LookUp.class);
    if (formField != null || lookUp != null) {
      AdditionalProperty additionalProperty = new AdditionalProperty();
      if (lookUp != null) {
        additionalProperty.setEndpoint(lookUp.endpoint());
        additionalProperty.setDataSource(lookUp.dataSource());
        additionalProperty.setOffset(lookUp.offset());
        additionalProperty.setPageSize(lookUp.pageSize());
        additionalProperty.setSearchTerm(lookUp.searchTerm());
      }
      if (formField != null) {
        additionalProperty.setVisibility(formField.visibility());
      }

      try {
        jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(additionalProperty));
        // new AdditionalProperty(false)
      } catch (JsonProcessingException e) {
        // todo log
      }
    }
    return jsonNode;
  }
}

// configBuilder.forFields()
// .withStringFormatResolver(field -> format(field));

// configBuilder.forFields()
// .withAdditionalPropertiesResolver((scope, X) ->
// resolveAdditionalAttributes(scope));

// configBuilder.forFields()
// .withTitleResolver(field -> resolveTitle(field));

// configBuilder.forFields()
// .withDependentRequiresResolver(field -> Optional
// .ofNullable(field.getAnnotationConsideringFieldAndGetter(FormField.class))
// .map(FormField::dependentRequired)
// .filter(dependentRequired -> dependentRequired.length > 0)
// .map(dependentRequired -> Arrays.asList(dependentRequired))
// .orElse(null));

// configBuilder.forFields()
// .withInstanceAttributeOverride(new
// MandatoryCompanyPropertyAttributeOverride());

// configBuilder.forMethods()
// .withDependentRequiresResolver(method ->
// Optional.ofNullable(method.findGetterField())
// .map(FieldScope::getSchemaPropertyName)
// .map(Collections::singletonList)
// .orElse(null));

// CustomPropertyDefinitionProvider provider = (member, context) -> Optional
// .ofNullable(member.getAnnotationConsideringFieldAndGetter(FormField.class))
// .map(FormField::format())
// .map(rawSchema -> {
// try {
// return context.getGeneratorConfig().getObjectMapper().readTree(rawSchema);
// } catch (Exception ex) {
// return null;
// }
// })
// .map(CustomPropertyDefinition::new)
// .orElse(null);