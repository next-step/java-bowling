package qna.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 요청한 유저가 답변글 작성 유저가 아닐 경우 Exception이 발생해야 한다.")
    void failByLoginAndAnswerIdDismatchTest() {

        // when & then
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.deleteAnswer(UserTest.SANJIGI))
            .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
