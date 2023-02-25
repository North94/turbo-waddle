package pl.north.ideas.question.service;

import pl.north.ideas.question.domain.model.Question;
import pl.north.ideas.question.dto.QuestionDto;

public class QuestionMapper {
    public static QuestionDto map(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setName(question.getName());
        questionDto.setCategoryId(question.getCategory().getId());
        questionDto.setCategoryName(question.getCategory().getName());
        questionDto.setAnswers(question.getAnswers() == null ? 0 : question.getAnswers().size());
        questionDto.setCreated(question.getCreated());
        return questionDto;
    }
}