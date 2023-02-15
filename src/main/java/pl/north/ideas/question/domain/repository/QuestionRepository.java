package pl.north.ideas.question.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.north.ideas.category.dto.CategoryWithStatisticsDto;
import pl.north.ideas.common.dto.StatisticsDto;
import pl.north.ideas.question.domain.model.Question;
import pl.north.ideas.question.dto.QuestionWithStatisticsDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository <Question, UUID> {
    List<Question> findAllByCategoryId(UUID id);
    @Query(value = "from Question q order by q.answers.size desc")
    Page<Question> findHot(Pageable pageable);


    @Query(value = "from Question q where q.answers.size = 0")
    Page<Question> findUnanswered(Pageable pageable);

    @Query(value = "select new pl.north.ideas.common.dto.StatisticsDto(count(q), count(a)) from Question q join q.answers a")
    StatisticsDto statistics();


    @Query(
            value = "select * from questions q where upper(q.name) like upper('%' || :query || '%') ",
            countQuery = "select count(*) from questions q where upper(q.name) like upper('%' || :query || '%') ",
            nativeQuery = true
    )
    Page<Question> findByQuery(String query, Pageable pageable);

    @Query(value = "select * from questions q order by random() limit :limit", nativeQuery = true)
    List<Question> findRandomQuestions(int limit);

    Page<Question> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
