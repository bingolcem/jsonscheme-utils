package testClasses;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

public class SchemaDependencies {
    public static void main(String[] args) {
        // Create SchemaGeneratorConfig
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(
                 SchemaVersion.DRAFT_2020_12,
                OptionPreset.PLAIN_JSON
        );
        SchemaGeneratorConfig config = configBuilder.build();
 
        SchemaGenerator generator = new SchemaGenerator(config);

        
     
        // Create SchemaGenerator
       

        // Generate JSON Schema
        JsonNode schemaNode = generator.generateSchema(MyClass.class);

        // Print the generated JSON Schema
        System.out.println(schemaNode.toPrettyString());
    }
}

class MyClass {
    private String name;
    private int creditCard;
    private String billingAddress;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}