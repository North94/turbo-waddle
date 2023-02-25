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

    @BeforeEach
    void ShouldSaveQuestion(){
        questionRepository.deleteAll();
        questionRepository.saveAll(List.of(
                new Question("Question1"),
                new Question("Question2"),
                new Question("Question3")
                ));
    }
    @Test
    void getQuestions() {
        // given
        // when
        List<Question> questions = questionService.getQuestions();
        // then
        assertThat(questions)
                .hasSize(3)
                .extracting(Question::getName)
                .containsExactlyInAnyOrder("Question1", "Question3", "Question2");
    }

    @Test
    void shouldGetQuestion() {
        //given
        Question question = new Question("Question4");
        //when
        Question result = questionService.getQuestion(question.getId());
        //then
        assertThat(result.getId()).isEqualTo(question.getId());
    }

    @Test
    void createQuestion() {
        // given
        // when
        // then
    }

    @Test
    void updateQuestion() {
        // given
        // when
        // then
    }

    @Test
    void deleteQuestion() {
        // given
        // when
        // then
    }

    @Test
    void findAllByCategoryId() {
        // given
        // when
        // then
    }

    @Test
    void findHot() {
        // given
        // when
        // then
    }

    @Test
    void findUnanswered() {
        // given
        // when
        // then
    }

    @Test
    void findByQuery() {
        // given
        // when
        // then
    }
}