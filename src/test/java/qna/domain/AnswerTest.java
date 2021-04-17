package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제 전후의 삭제 상태 확인 테스트")
    public void 삭제전후_상태_테스트() {
        assertThat(A1.isDeleted()).isEqualTo(false);
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("답변 작성자 일치 테스트")
    public void 답변_작성자_테스트() {
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
