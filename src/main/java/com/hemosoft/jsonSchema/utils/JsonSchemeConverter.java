package com.hemosoft.jsonSchema.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class JsonSchemeConverter {
    
    
    public JsonNode getModel(Class clazz) {
        JacksonModule module = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12,
            OptionPreset.PLAIN_JSON).with(module)
            .with(Option.EXTRA_OPEN_API_FORMAT_VALUES);
        ;
        configBuilder.forFields()
            .withReadOnlyCheck(field -> resolveReadOnlyCheck(field));
    
        configBuilder.forFields()
            .withAdditionalPropertiesResolver((scope, X) -> resolveAdditionalAttributes(scope));
    
        configBuilder.forFields()
            .withTitleResolver(field -> resolveTitle(field));
    
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(clazz);
        
        
        return jsonSchema;
      }
    
    
       private boolean resolveReadOnlyCheck(FieldScope field) {
        FormField formField = field.getAnnotationConsideringFieldAndGetter(FormField.class);
        return formField != null ? formField.readOnly() : false;
      }
    
      private String resolveTitle(FieldScope field) {
        FormField formField = field.getAnnotationConsideringFieldAndGetter(FormField.class);
        return formField != null ? formField.title() : field.getName();
      }
    
      private JsonNode resolveAdditionalAttributes(FieldScope fieldscope) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        FormField formField = fieldscope.getAnnotationConsideringFieldAndGetter(FormField.class);
        if (formField != null && !formField.visibility()) {
          try {
            jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(new AdditionalProperty(false)));
            // new AdditionalProperty(false)
          } catch (JsonProcessingException e) {
            // todo log
          }
        }
        return jsonNode;
      }
}
