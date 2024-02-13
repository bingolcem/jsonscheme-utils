package testClasses.mock;


import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.github.imifou.jsonschema.module.addon.annotation.JSData;
import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

@JsonSerializableSchema
public class TestClassForResolveMetadata {

    String unannotatedField;
    @JsonSchema
    int annotatedWithoutValueField;
    double annotatedWithoutValueGetterField;
    @JsonSchema(metadata = @JSData(key ="a",value="b"))
    Object annotatedField;
    boolean annotatedGetterField;

    public String getUnannotatedField() {
        return this.unannotatedField;
    }

    public int getAnnotatedWithoutValueField() {
        return this.annotatedWithoutValueField;
    }

    @JsonSchema
    public double getAnnotatedWithoutValueGetterField() {
        return this.annotatedWithoutValueGetterField;
    }

    public Object getAnnotatedField() {
        return this.annotatedField;
    }

    @JsonSchema(metadata = {@JSData(key ="a",value="b"),@JSData(key ="c",value="d")})
    public boolean isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }
}
