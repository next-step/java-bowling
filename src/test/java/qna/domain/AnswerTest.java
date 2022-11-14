package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static qna.domain.Answer.newAnswerWithDeleted;
import static qna.domain.Question.newQuestion;
import static qna.domain.UserTest.JAVAJIGI;

public class AnswerTest {

    private Answer A1;
    private Question Q1;

    @BeforeEach
    void setUp() {
        Q1 = newQuestion(1L, "title1", "contents1").writeBy(JAVAJIGI);
        A1 = Answer.newAnswer(1L, JAVAJIGI, Q1, "test");
    }

    @Test
    void 논리삭제() throws CannotDeleteException {
        A1.delete(JAVAJIGI);
        Assertions.assertThat(A1).isEqualTo(newAnswerWithDeleted(1L, JAVAJIGI, Q1, "test", true));
    }
}
