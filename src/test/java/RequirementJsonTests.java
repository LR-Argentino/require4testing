import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.blackbird.require4testing.Require4testingApplication;
import org.blackbird.require4testing.requirement.Priority;
import org.blackbird.require4testing.requirement.Requirement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

/** Testing the Data Contract */
@SpringBootTest(classes = Require4testingApplication.class)
@AutoConfigureJsonTesters
class RequirementJsonTests {

  @Autowired private JacksonTester<Requirement> json;

  @Test
  void testSerialize() throws IOException {
    Requirement requirement = new Requirement();
    requirement.setId(1L);
    requirement.setTitle("Test Requirement");
    requirement.setDescription("This is a test requirement");
    requirement.setPriority(Priority.HIGH);

    assertThat(json.write(requirement)).hasJsonPathNumberValue("$.id");
    assertThat(json.write(requirement)).extractingJsonPathNumberValue("$.id").isEqualTo(1);
    assertThat(json.write(requirement)).hasJsonPathStringValue("$.title");
    assertThat(json.write(requirement))
        .extractingJsonPathStringValue("$.title")
        .isEqualTo("Test Requirement");
    assertThat(json.write(requirement)).hasJsonPathStringValue("$.description");
    assertThat(json.write(requirement))
        .extractingJsonPathStringValue("$.description")
        .isEqualTo("This is a test requirement");

    assertThat(json.write(requirement)).isStrictlyEqualToJson("expected.json");
  }
}
