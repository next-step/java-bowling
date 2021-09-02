package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.SANJIGI;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, Q1, "Answers Contents2");

    @Test
    void answersIsNotSameWiter() throws CannotDeleteException {

        Answer answer = A1;

        assertThatThrownBy(
                () -> answer.delete(SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);

    }
}
