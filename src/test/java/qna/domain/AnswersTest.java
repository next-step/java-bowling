package qna.domain;


import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answers AS1 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

    @Test
    void 동일한작성자() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));
        answers.areOwner(UserTest.JAVAJIGI);
    }

    @Test
    void 다른작성자가있으면_예외() {
        assertThatThrownBy(() -> {
            AS1.areOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
