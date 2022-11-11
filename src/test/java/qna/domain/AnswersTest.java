package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

public class AnswersTest {

    @Test
    void testOtherAnswerValidation() {
        Answers answers = new Answers(A1, A2);
        assertThatThrownBy(() -> answers.validateIfHasOthersAnswer(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
