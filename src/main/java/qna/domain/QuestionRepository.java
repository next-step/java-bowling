package qna.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	List<Question> findByDeletedFalse();

	Optional<Question> findByIdAndDeletedFalse(Long id);
}
