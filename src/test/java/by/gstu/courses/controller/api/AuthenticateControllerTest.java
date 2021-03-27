package by.gstu.courses.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * createdAt: 12/6/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs //outputDir = "target/generated-snippets"
class AuthenticateControllerTest {

    private static final String EMAIL = "pocoge7908@pashter.com"; // used temp-mail

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnAccessAndRefreshJwtTokens() throws Exception {
        mvc.perform(post("/api/auth/auto")
                .secure(true)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("email="+ EMAIL))
                .andExpect(status().isCreated())
                .andDo(document("auth"));
    }
}
