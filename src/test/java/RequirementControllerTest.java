import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.blackbird.require4testing.requirement.services.RequirementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

@SpringBootTest(classes = org.blackbird.require4testing.Require4testingApplication.class)
@AutoConfigureMockMvc
class RequirementControllerTest {

  @Autowired private MockMvc mockMvc;

  @Mock private RequirementService requirementService;

  @Test
  void test_post_shouldReturnCreatedRequirement() throws Exception {
    mockMvc
        .perform(
            post("/api/requirements")
                .param("title", "Neue Anforderung")
                .param("description", "Description of new requirement")
                .param("priority", "MEDIUM")
                .contentType(MediaType.APPLICATION_JSON.getMediaType()))
        .andExpect(status().isOk());
  }
}
