package com.hemosoft.jsonSchema.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormField {
    String title() ;
    boolean readOnly() default false;
    boolean visibility() default true;
    
    //descriptor, order, tab (jsonschema fieldset) , condition'lı readonly ve visibility düşünülebilir
    // sonra multilanguage 
    // hemosoft-json-schema-utils kütüphanesi
}
