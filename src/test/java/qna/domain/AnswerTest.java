package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    public static final Answer A1_WRITE_BY_JAVAJIGI = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1_WRITE_BY_JAVAJIGI, "Answers Contents1");
    public static final Answer A2_WRITE_BY_SANJIGI = new Answer(UserTest.SANJIGI, QuestionTest.Q1_WRITE_BY_JAVAJIGI, "Answers Contents2");

    @DisplayName("다른 사람이 작성한 답변이 있는지 확인")
    @Test
    void verifyOwner() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(()-> A1_WRITE_BY_JAVAJIGI.verifyOwner(UserTest.SANJIGI))
        .withMessage(Answer.ERROR_MESSAGE_OWNER_CHECK)
        ;
    }
}
