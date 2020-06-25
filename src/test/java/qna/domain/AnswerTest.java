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
    @DisplayName("답변 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태만 변경한다.")
    void delete_성공() throws CannotDeleteException {
        assertThat(A1.isDeleted()).isFalse();
        A1.deleteBy(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 답변한 사람이 다른 경우 삭제 불가능하다.")
    void delete_사용자_검증_성공() {
        assertThatThrownBy(() -> A2.deleteBy(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("답변 삭제 시 DeleteHistory를 반환한다.")
    void delete_DeleteHistory_반환() throws CannotDeleteException {
        assertThat(A1.deleteBy(UserTest.JAVAJIGI))
                .isEqualTo(DeleteHistory.deleteAnswer(null, UserTest.JAVAJIGI));
    }
}
