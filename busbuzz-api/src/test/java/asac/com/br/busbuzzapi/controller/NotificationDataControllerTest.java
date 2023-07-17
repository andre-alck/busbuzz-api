package asac.com.br.busbuzzapi.controller;

import asac.com.br.busbuzzapi.model.NotificationDataModel;
import asac.com.br.busbuzzapi.service.NotificationDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(NotificationDataController.class)
class NotificationDataControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NotificationDataService service;

    @BeforeEach
    void setupMock() {
        Timestamp timestamp = mock(Timestamp.class);
        when(service.addData(any())).thenReturn(timestamp);
    }

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

    @Test
    void givenNullImageUrlThenShouldReturnOk() throws Exception {
        NotificationDataModel model = new NotificationDataModel("title", "text", null, "name");
        String modelJson = objectMapper.writeValueAsString(model);

        this.mockMvc.perform(post("/add-notification-data").contentType("application/json").content(modelJson)).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Timestamp request = ")));
    }

    @Test
    void givenValidImageUrlThenShouldReturnOk() throws Exception {
        NotificationDataModel model = new NotificationDataModel("title", "text", "https://avatars.githubusercontent.com/u/60830711?s=400&u=b201e170a816d600b000cde8a2135101fff0ec7f&v=4", "name");
        String modelJson = objectMapper.writeValueAsString(model);

        this.mockMvc.perform(post("/add-notification-data").contentType("application/json").content(modelJson)).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Timestamp request = ")));
    }

    @Test
    void givenNullTextThenShouldThrowAssertionError() throws Exception {
        NotificationDataModel model = new NotificationDataModel("title", null, "https://avatars.githubusercontent.com/u/60830711?s=400&u=b201e170a816d600b000cde8a2135101fff0ec7f&v=4", "name");
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