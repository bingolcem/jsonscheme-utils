package com.hemosoft.jsonSchema.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LookUp {
    String endpoint();
    // String code();

    String dataSource();

    String searchTerm() default "";

    int offset() default 0;

    int pageSize() default 0;
}
