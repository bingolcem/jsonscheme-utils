package com.hemosoft.jsonSchema.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdditionalProperty {

    public AdditionalProperty(boolean visibility) {
        this.visibility = visibility;
    }

    boolean visibility = false;
    String endpoint;
    // String code;
    String conditionalVisibilty;

    String DataSource;

    String SearchTerm;

    int Offset;

    int PageSize;
}
