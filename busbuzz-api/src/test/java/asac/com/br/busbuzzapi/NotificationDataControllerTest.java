package asac.com.br.busbuzzapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(NotificationDataController.class)
class NotificationDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenDataWhenObjectMapperSerializeModelThenResultShouldNotBeNull() throws JsonProcessingException {
        NotificationDataModel model = new NotificationDataModel("title", "text", "imageUrl", "name");
        String modelJson = objectMapper.writeValueAsString(model);
        assertThat(modelJson).isNotNull();
    }

    @Test
    void givenInvalidImageUrlThenShouldReturnAssertionError() throws JsonProcessingException {
        NotificationDataModel model = new NotificationDataModel("title", "text", "imageUrl", "name");
        String modelJson = objectMapper.writeValueAsString(model);

        AssertionError exception = assertThrows(
                AssertionError.class, () -> {
                    this.mockMvc.perform(post("/add-notification-data")
                                    .contentType("application/json")
                                    .content(modelJson))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(content().string(containsString("hello, world!")));
                }
        );

        String actualMessage = exception.getMessage();
        String expectedMessage = "Status expected:<200> but was:<400>";
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}