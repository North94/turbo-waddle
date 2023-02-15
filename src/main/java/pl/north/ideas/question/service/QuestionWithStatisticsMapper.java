package pl.north.ideas.question.service;

import pl.north.ideas.question.domain.model.Question;
import pl.north.ideas.question.dto.QuestionWithStatisticsDto;

public class QuestionWithStatisticsMapper {
    public static QuestionWithStatisticsDto map(Question question) {
        QuestionWithStatisticsDto questionDto = new QuestionWithStatisticsDto(
                question.getId(),
                question.getName(),
                question.getCategory().getName(),
                question.getAnswers() == null ? 0 : question.getAnswers().size()
        );
        return questionDto;
    }
}
