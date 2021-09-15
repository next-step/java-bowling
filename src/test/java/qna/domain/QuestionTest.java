package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void validateAuthorAreSameTest() {
        assertThatThrownBy(() -> Q1.validateAuthorAreSame(UserTest.SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    void validateThereIsAnyonesElseAnswerSuccessTest() throws CannotDeleteException {
        final Answer answer = new Answer(UserTest.JAVAJIGI, Q1, "");
        Q1.addAnswer(answer);
        Q1.validateThereIsAnyonesElseAnswer(UserTest.JAVAJIGI);
    }

    @Test
    void validateThereIsAnyonesElseAnswerExceptionTest() throws CannotDeleteException {
        final Answer answer = new Answer(UserTest.SANJIGI, Q1, "");
        Q1.addAnswer(answer);

        assertThatThrownBy(() -> Q1.validateThereIsAnyonesElseAnswer(UserTest.JAVAJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }
}
