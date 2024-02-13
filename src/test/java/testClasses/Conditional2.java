package testClasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

public class Conditional2 {
    ObjectMapper objectMapper = new ObjectMapper();
    
     public static void main(String[] args) throws Exception {
       
        ObjectMapper objectMapper = new ObjectMapper();
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(
                SchemaVersion.DRAFT_2020_12,
                OptionPreset.PLAIN_JSON
        );
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        Class<?> rootClass = WorkPackageUpdateRequest.class;
        
        ObjectNode jsonSchema = generator.generateSchema(rootClass);
        
        JsonNode maleQuestionsNode = createMaleQuestionsSchemaNode(generator);
        JsonNode femaleQuestionsNode = createFemaleQuestionsSchemaNode(generator);

        ObjectNode jsonSchema2= mergeProperties(jsonSchema, maleQuestionsNode, femaleQuestionsNode);

        // Convert the JSON schema to a string and print it
        String schemaString = objectMapper.writeValueAsString(jsonSchema);
        System.out.println(jsonSchema2.toPrettyString());
    }

    // Method to create schema for male questions
    private static JsonNode createMaleQuestionsSchemaNode(SchemaGenerator generator) {
        ObjectNode maleQuestionsSchema = generator.generateSchema(Male.class);
      
      // System.out.println(maleQuestionsSchema.toPrettyString());
        maleQuestionsSchema.remove("$schema");
        return maleQuestionsSchema;
    }

    // Method to create schema for female questions
    private static JsonNode createFemaleQuestionsSchemaNode(SchemaGenerator generator) {
        ObjectNode femaleQuestionsSchema = generator.generateSchema(Female.class);
       
       
        femaleQuestionsSchema.remove("$schema");
        return femaleQuestionsSchema;
    }

    // Method to merge conditional properties based on gender
    private static ObjectNode mergeProperties(ObjectNode propertiesNode, JsonNode maleQuestionsNode, JsonNode femaleQuestionsNode) {
       
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode ifNode = JsonNodeFactory.instance.objectNode();
        ObjectNode thenNode = JsonNodeFactory.instance.objectNode();
        try {
            JsonNode genderNode2 = objectMapper.readTree("{ \"gender\": { \"const\": \"MALE\" } }");
            ifNode.set("properties",genderNode2);
        } catch ( JsonProcessingException e) {
            e.printStackTrace();
        } 
        thenNode.set("properties", maleQuestionsNode);
        propertiesNode.set("if", ifNode);
        propertiesNode.set("then", maleQuestionsNode);
        propertiesNode.set("else", femaleQuestionsNode);
        return propertiesNode;
    }
    
    
}
