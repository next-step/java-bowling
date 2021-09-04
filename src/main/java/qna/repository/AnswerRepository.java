package qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qna.domain.answers.Answer;
import qna.domain.questions.Question;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionAndDeletedFalse(Question question);

    Optional<Answer> findByIdAndDeletedFalse(Long id);
}
