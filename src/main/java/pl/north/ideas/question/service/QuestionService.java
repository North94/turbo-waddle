package pl.north.ideas.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.north.ideas.common.dto.StatisticsDto;
import pl.north.ideas.question.domain.model.Question;
import pl.north.ideas.question.domain.repository.QuestionRepository;
import pl.north.ideas.question.dto.QuestionDto;
import pl.north.ideas.question.dto.QuestionWithStatisticsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;


    @Transactional(readOnly = true)
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Question getQuestion(UUID id) {
        return questionRepository.getById(id);
    }
    @Transactional
    public Question createQuestion(Question questionRequest) {
        Question question = new Question();
        question.setName(questionRequest.getName());
        return questionRepository.save(question);
    }
    @Transactional
    public Question updateQuestion(UUID id, Question questionRequest) {
        Question question = questionRepository.getById(id);
        question.setName(questionRequest.getName());
        return questionRepository.save(question);
    }
    @Transactional
    public void deleteQuestion(UUID id) {
        questionRepository.deleteById(id);

    }

    @Transactional(readOnly = true)
    public List<Question> findAllByCategoryId(UUID id) {
       return questionRepository.findAllByCategoryId(id);

    }

    @Transactional(readOnly = true)
    public Page<Question> findHot(Pageable pageable) {
        return questionRepository.findHot(pageable);
    }
    @Transactional(readOnly = true)
    public Page<Question> findUnanswered(Pageable pageable) {
        return questionRepository.findUnanswered(pageable);
    }

    public Page<Question> findByQuery(String query, Pageable pageable) {
        return questionRepository.findByQuery(query, pageable);
    }

    @Transactional(readOnly = true)
    public List<QuestionDto> findTop(int limit) {
        return questionRepository.findAll(PageRequest.of(0, limit))
                .get()
                .map(QuestionMapper::map)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<QuestionDto> findTop(UUID categoryId, int limit) {
        return questionRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(QuestionMapper::map)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<QuestionDto> findRandom(int limit) {
        return questionRepository.findRandomQuestions(limit)
                .stream()
                .map(QuestionMapper::map)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public StatisticsDto statistics() {
        return questionRepository.statistics();
    }


    public Page<Question> getQuestions(String search, Pageable pageable) {
        if (search == null) {
            return questionRepository.findAll(pageable);
        } else {
            return questionRepository.findByNameContainingIgnoreCase(search, pageable);
        }
    }
    @Transactional(readOnly = true)
    public List<QuestionWithStatisticsDto> findAllWithStatisticsQuestions() {
        List<Question> questions = questionRepository.findAll();

        List<QuestionWithStatisticsDto> mappedQuestions = new ArrayList<>();
        for (Question question: questions) {
            mappedQuestions.add(QuestionWithStatisticsMapper.map(question));
        }
        return mappedQuestions;
    }
}
