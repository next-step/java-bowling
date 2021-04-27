package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("작성자가 아닌 다른 사람이 삭제 요청을 했을시 예외처리 발생 여부 테스트")
    @Test
    void 예외_다른_작성자() {
        // when and then
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("삭제가 되었을 때 삭제상태값이 true 로 변경되었는지 테스트")
    @Test
    void 변환_삭제상태() throws CannotDeleteException {
        // when
        A1.delete(UserTest.JAVAJIGI);
        A2.delete(UserTest.SANJIGI);

        // then
        assertAll(
                () -> assertThat(A1.isDeleted()).isTrue(),
                () -> assertThat(A2.isDeleted()).isTrue()
        );
    }

    @DisplayName("삭제가 되었을 때 반환 값으로 삭제 내역을 기록한 객체를 반환하는지 테스트")
    @Test
    void 반환_삭제기록() {

        // then
        assertAll(
                () -> assertThat(A1.delete(UserTest.JAVAJIGI)).isNotNull(),
                () -> assertThat(A1.delete(UserTest.JAVAJIGI)).isInstanceOf(DeleteHistory.class),
                () -> assertThat(A2.delete(UserTest.SANJIGI)).isNotNull(),
                () -> assertThat(A2.delete(UserTest.SANJIGI)).isInstanceOf(DeleteHistory.class)
        );
    }

}
