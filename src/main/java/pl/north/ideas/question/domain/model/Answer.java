package pl.north.ideas.question.domain.model;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "answers")
@Getter
@Setter
@ToString
public class Answer {
    @Id
    @Column(name = "answer_id")
    private UUID id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @ToString.Exclude
    private Question question;
    public Answer() {
        this.id = UUID.randomUUID();
    }
    public Answer(String name) {
        this();
        this.name = name;
    }

}
