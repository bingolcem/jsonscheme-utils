package testClasses;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

import lombok.Data;
@Data
public class TestClassForResolveIgnore {
    Gender enfum;
    String unannotatedField;
    @JsonSchema(ignore = ("enfum"=="name"))
    int annotatedAsNotHiddenField;
    double annotatedAsNotHiddenGetterField;
    @JsonSchema(ignore = true)
    Object annotatedField;
    boolean annotatedGetterField;

    public String getUnannotatedField() {
        return this.unannotatedField;
    }

    public int getAnnotatedWithoutValueField() {
        return this.annotatedAsNotHiddenField;
    }

    @JsonSchema
    public double getAnnotatedWithoutValueGetterField() {
        return this.annotatedAsNotHiddenGetterField;
    }

    public Object getAnnotatedField() {
        return this.annotatedField;
    }

    @JsonSchema(ignore = true)
    public boolean isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }
    
}
