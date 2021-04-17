package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제 전후의 삭제 상태 확인 테스트")
    public void 삭제전후_상태_테스트() {
        assertThat(A1.isDeleted()).isEqualTo(false);
        A1.delete();
        assertThat(A1.isDeleted()).isEqualTo(true);
    }
}
