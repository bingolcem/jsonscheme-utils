package testClasses.mock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = B.class, name = "Child"),
        @JsonSubTypes.Type(value = D.class, name = "abstractchild"),
        @JsonSubTypes.Type(value = B1.class, name = "abstractchild1")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class A {
    @JsonProperty(required = true)
    String z;
    private String zz;
}





