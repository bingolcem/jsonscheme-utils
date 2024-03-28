package testClasses;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;
import com.hemosoft.jsonSchema.utils.Conditional;
import com.hemosoft.jsonSchema.utils.FormField;
import com.hemosoft.jsonSchema.utils.LookUp;
import com.hemosoft.jsonSchema.utils.LookupItem;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WorkPackageUpdateRequest {
    // @FormField(title = "Id", visibility = false, endpoint = "ghj", param = "hmö",
    // order = 0, value = "personel")
    // Long id;
    // @JsonIgnore
    // Long project;

    // @FormField(endpoint = "", order = 0,a = Female.class, title = "65y"
    // ,dependentRequired = "OptionBir", getSchemaPropertyName = "")
    // String name;

    // int a;

    // @FormField(dependentRequired = {"male", "maleQuestions"}, endpoint =
    // "ghnhfhg", getSchemaPropertyName = "ghghg", order = 0, title =
    // "",conditionalVisibility = "gn")
    // @Conditional(condition = { @KeyValue(key = "MALE", value = Male.class) })
    // public Gender gender;

    // @FormField(endpoint = "dgng", getSchemaPropertyName = "fgfg", order = 0,
    // title = "fgfgfgf",conditionalVisibility = "render()",
    // dependentSchemas={Male.class})
    @Conditional(key = "gbfdgd", value = "fdfddfd")
    //@FormField(description = "gdfd",  getSchemaPropertyName = "", order = 0, title = "")
    @LookUp(endpoint = "deneme",dataSource ="L.Gender")
    public LookupItem gender;
   // @JsonSchema(ignore = ( (Gender.FEMALE).equals(Gender.FEMALE)))
    public String femaleQuestions;
    // String a;

    // public Boolean render(){
    // return gender!=null&& getGender().equals(Gender.MALE);
    // }

}
