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
    HttpPost httpPost;
}
