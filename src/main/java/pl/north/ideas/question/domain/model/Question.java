package pl.north.ideas.question.domain.model;

import lombok.*;
import pl.north.ideas.category.domain.model.Category;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


@Data
@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString
public class Question {
    @Id
    @Column(name = "question_id")
    private UUID id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;
    private LocalDateTime created;
    private LocalDateTime modified;


    public Question() {
        this.id = UUID.randomUUID();
    }
    public Question(String name) {
        this();
        this.name = name;
    }
    public Category getCategory() {
        return category;
    }
    @PrePersist
    void prePersist() {
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
    }
    @PreUpdate
    void preUpdate() {
        modified = LocalDateTime.now();
    }
    public Question addAnswer(Answer answer) {
        if (answers == null) {
            answers = new LinkedHashSet<>();
        }
        answer.setQuestion(this);
        answers.add(answer);
        return this;
    }
}
