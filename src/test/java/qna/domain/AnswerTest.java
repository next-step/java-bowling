package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static qna.domain.Answer.newAnswerWithDeleted;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;

public class AnswerTest {

    private Answer A1;

    @BeforeEach
    void setUp() {
        A1 = Answer.newAnswer(1L, JAVAJIGI, Q1, "test");
    }

    @Test
    void 논리삭제() throws CannotDeleteException {
        A1.delete(JAVAJIGI);
        Assertions.assertThat(A1).isEqualTo(newAnswerWithDeleted(1L, JAVAJIGI, Q1, "test", true));
    }
}
