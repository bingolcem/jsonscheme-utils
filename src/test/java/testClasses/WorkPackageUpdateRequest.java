package testClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemosoft.jsonSchema.utils.FormField;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkPackageUpdateRequest {
    @FormField(title = "Id", visibility = false)
    Long id;
    @JsonIgnore
    Long project;

    String workPackageNo;

    String workPackageName;
  

    Long personel;

    Date expectedStartDate;

    Date expectedEndDate;

    Date actualStartDate;

    Date actualEndDate;

    

    String notes;

}
