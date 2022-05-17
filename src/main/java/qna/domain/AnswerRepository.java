package qna.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

  List<Answer> findByQuestionAndDeletedFalse(Question question);

  Optional<Answer> findByIdAndDeletedFalse(Long id);
}
