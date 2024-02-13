import org.junit.Test;

import com.hemosoft.jsonSchema.utils.JsonSchemeConverter;

import testClasses.WorkPackageUpdateRequest;

public class JsonSchemeTest {

  JsonSchemeConverter jsonSchemeConverter = new JsonSchemeConverter();

  @Test
  public void method() {
    /// System.out.println(jsonSchemeConverter.getModel(
    /// WorkPackageUpdateRequest.class).toPrettyString());

  }

  @Test
  public void method2() {

    System.out.println(jsonSchemeConverter.getModel(WorkPackageUpdateRequest.class).toPrettyString());
    // TestClassForResolveMetadata.class

  }

}
