package com.hemosoft.jsonSchema.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionalProperty {
    
    public AdditionalProperty(boolean visibility) {
        this.visibility = visibility;
    }

boolean visibility=false;

}
