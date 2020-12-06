package by.gstu.edu.controller.api;

import by.gstu.edu.provider.JwtTokenProvider;
import by.gstu.edu.repository.UserRepository;
import by.gstu.edu.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * createdAt: 12/6/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/snippets")
class AuthenticateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnAccessAndRefreshJwtTokens() throws Exception {
        mvc.perform(post("/api/auth/auto")
                .secure(true)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("email=pet.home.project.test@gmail.com"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("auth"));
    }
}
