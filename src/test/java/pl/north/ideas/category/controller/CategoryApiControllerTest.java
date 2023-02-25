package pl.north.ideas.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.north.ideas.category.domain.model.Category;
import pl.north.ideas.category.service.CategoryService;
import pl.north.ideas.question.domain.model.Question;

import java.util.List;
import java.util.UUID;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;
    @Autowired
    private ObjectMapper objectMapper;
    private PageImpl<Category> page;

    private Category categoryOne;
    private Category categoryTwo;

    @BeforeEach
    void setUp() {
        categoryOne = new Category("Cat1");
        categoryTwo = new Category(
                UUID.randomUUID(),
                "Cat2",
                List.of(new Question("Question1"), new Question("Question2"))
        );
        page = new PageImpl<>(
                List.of(categoryOne, categoryTwo, new Category("Cat3"))
        );
        when(categoryService.getCategories(any())).thenReturn(page);
        when(categoryService.getCategory(categoryOne.getId())).thenReturn(categoryOne);
        when(categoryService.getCategory(categoryTwo.getId())).thenReturn(categoryTwo);

        when(categoryService.createCategory(any())).thenAnswer(
                (InvocationOnMock invocationOnMock) -> invocationOnMock.getArguments()[0]);
        when(categoryService.updateCategory(any(), any())).thenAnswer(
                (InvocationOnMock invocationOnMock) -> invocationOnMock.getArguments()[1]);
    }

    @Test
    void shouldGetCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(page))
                );
    }

    @Test
    void shouldGetCategory() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/v1/categories/{categoryId}", categoryOne.getId()))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(categoryOne))
                );
        mockMvc.perform(get("http://localhost:8080/api/v1/categories/{categoryId}", categoryTwo.getId()))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(categoryTwo))
                );
    }

    @Test
    void shouldCreateCategory() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryOne))
                )
                .andExpect(status().isCreated())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(categoryOne))
                );
    }

    @Test
    void shouldUpdateCategory() throws Exception {
        mockMvc.perform(put("http://localhost:8080/api/v1/categories/{categoryId}", categoryOne.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryOne))
                )
                .andExpect(status().isAccepted())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(categoryOne))
                );
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/v1/categories/{categoryId}", categoryOne.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotRetrieveListOfQuestions() throws Exception {
        //when
        Category category = categoryOne;
        //given
        ResultActions response = mockMvc.perform(get("/api/v1/categories/{categoryId}", category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)));
        //then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(category.getName())))
                .andExpect(jsonPath( "$.questions").doesNotExist());
    }
    @Test
    void shouldRetrieveListOfQuestions() throws Exception {
        //when
        Category category = categoryTwo;
        //given
        ResultActions response = mockMvc.perform(get("/api/v1/categories/{categoryId}", category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)));
        //then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(category.getName())))
                .andExpect(jsonPath( "$.questions", Matchers.hasSize(2)));
    }
}
