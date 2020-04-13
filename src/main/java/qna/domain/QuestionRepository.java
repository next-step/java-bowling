package qna.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDeletedFalse();

    @Query("SELECT q FROM Question q JOIN FETCH q.answers WHERE q.id = :id AND q.deleted = FALSE")
    Optional<Question> findByIdAndDeletedFalse(Long id);
}
