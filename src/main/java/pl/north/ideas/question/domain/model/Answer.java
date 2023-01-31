package pl.north.ideas.question.domain.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;
    @Entity
    @Table(name = "Answers")
    @Getter
    @Setter
    @ToString
    public class Answer {
    @Id
    private UUID id;
    private String name;

    @ManyToOne
    private Question question;


    public Answer() {
        this.id = UUID.randomUUID();
    }

    public Answer(String name) {
        this();
        this.name = name;
    }

}
