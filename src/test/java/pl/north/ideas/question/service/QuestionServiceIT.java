package pl.north.ideas.question.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.north.ideas.category.domain.repository.CategoryRepository;
import pl.north.ideas.question.domain.model.Question;
import pl.north.ideas.question.domain.repository.AnswerRepository;
import pl.north.ideas.question.domain.repository.QuestionRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceIT {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
    }


    @Test
    void getQuestions() {
        // given
        questionRepository.deleteAll();

        questionRepository.saveAll(List.of(
                new Question("Question1"),
                new Question("Question2"),
                new Question("Question3")
        ));
        // when
        List<Question> questions = questionService.getQuestions();
        // then
        assertThat(questions)
                .hasSize(3)
                .extracting(Question::getName)
                .containsExactlyInAnyOrder("Question1", "Question3", "Question2");
    }

    @Test
    void getQuestion() {
    }

    @Test
    void createQuestion() {
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void findAllByCategoryId() {
    }

    @Test
    void findHot() {
    }

    @Test
    void findUnanswered() {
    }

    @Test
    void findByQuery() {
    }
}