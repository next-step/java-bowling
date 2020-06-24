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

    @DisplayName("삭제하려는 답변작성자가 인자로받은 작성자와 다르면 CannotDeleteException throw")
    @Test
    void deleteAnswerFail() {
        User loginUser = UserTest.SANJIGI;
        assertThatThrownBy(() -> A1.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이여서 삭제할 수 없습니다.");
    }

    @DisplayName("답변 삭제에 성공하면 deleted를 true 바꾸고 해당 답변을 반환한다.")
    @Test
    void deleteAnswerSuccess() throws CannotDeleteException {
        User loginUser = UserTest.JAVAJIGI;

        Answer deletedAnswer = A1.delete(loginUser);

        assertAll(
                () -> assertThat(deletedAnswer).isEqualTo(A1),
                () -> assertThat(deletedAnswer.isDeleted()).isTrue()
        );
    }
}
