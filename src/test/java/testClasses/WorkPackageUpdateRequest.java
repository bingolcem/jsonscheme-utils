package testClasses;




import com.hemosoft.jsonSchema.utils.Conditional;
import com.hemosoft.jsonSchema.utils.FormField;
import com.hemosoft.jsonSchema.utils.KeyValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkPackageUpdateRequest {
    // @FormField(title = "Id", visibility = false, endpoint = "ghj", param = "hmö", order = 0, value = "personel")
    // Long id;
    // @JsonIgnore
    // Long project;

//    @FormField(endpoint = "", order = 0,a = Female.class, title = "65y" ,dependentRequired = "OptionBir", getSchemaPropertyName = "")
//     String name;

//     int a;

//@FormField(dependentRequired = {"male", "maleQuestions"}, endpoint = "ghnhfhg", getSchemaPropertyName = "ghghg", order = 0, title = "")
@Conditional(condition = { @KeyValue(key = "MALE", value = Male.class) })
public Gender gender;

// @FormField(endpoint = "dgng", getSchemaPropertyName = "fgfg", order = 0, title = "fgfgfgf")
// public String maleQuestions;
// public String femaleQuestions;




    

  

}
