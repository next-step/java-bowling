package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("본인이 작성한 답변인지 확인")
    void validationWriterIsOwner() {
        //then
        assertThatThrownBy(() -> A1.validationWriterIsOwner(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
