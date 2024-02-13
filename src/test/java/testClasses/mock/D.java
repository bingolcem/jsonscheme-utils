package testClasses.mock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = B.class, name = "Child1"),
        @JsonSubTypes.Type(value = B1.class, name = "Child2")
})
public abstract class D extends  A {
    private String x;
    private C c;
}
