package com.hemosoft.jsonSchema.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {})
public @interface FormField {
    String title();

    boolean readOnly() default false;

    boolean visibility() default true;

    String conditionalVisibility() default "";

    //String endpoint();

    String param() default "";

    String[] dependentRequired() default {};

    String format() default "";

    String getSchemaPropertyName();

    String description() default "";

    int order();

    // descriptor, order, tab (jsonschema fieldset) , condition'lı readonly ve
    // visibility düşünülebilir
    // sonra multilanguage
    // hemosoft-json-schema-utils kütüphanesi
}
