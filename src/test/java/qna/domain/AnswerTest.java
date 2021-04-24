package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void checkIsOwner_다른사람이_쓴_댓글() throws Exception {
        // given

        // when

        // then
        assertThatThrownBy(() -> {
            A1.checkIsOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> {
            A2.checkIsOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
