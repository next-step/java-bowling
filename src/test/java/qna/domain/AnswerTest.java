package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제유저 미입력")
    void deleteUserIsNull() {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> A1.delete(null))
                .withMessageMatching("유저 정보를 입력해 주세요.");
    }

    @Test
    @DisplayName("타인의 답변은 삭제 불가")
    void cannotDeleteOthers() {
        // given when then
        assertThrows(CannotDeleteException.class, () -> A1.delete(UserTest.SANJIGI), "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답변삭제 테스트")
    void delete() {
        // given
        Answer a1 = AnswerTest.A1;

        // when
        a1.delete(UserTest.JAVAJIGI);

        // then
        assertThat(a1.isDeleted()).isTrue();
    }
}
