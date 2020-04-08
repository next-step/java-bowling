package qna.domain.qna.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import qna.domain.qna.question.Question;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionAndDeletedFalse(Question question);

    Optional<Answer> findByIdAndDeletedFalse(Long id);
}
