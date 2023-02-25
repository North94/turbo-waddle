package pl.north.ideas.question.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class QuestionWithStatisticsDto {

    private UUID id;
    private String name;
    private String categoryName;
    private long answers;
}
