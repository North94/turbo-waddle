package pl.north.ideas.category.domain.model;


import lombok.Getter;
import pl.north.ideas.question.domain.model.Question;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "categories")
public class Category {
    public Category() {
        this.id = UUID.randomUUID();
    }
    @Id
    private UUID id;
    @NotBlank(message = "{ideas.validation.name.NotBlank.message}")
    @Size(min = 3, max = 255)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Question> questions;

    public Category(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
