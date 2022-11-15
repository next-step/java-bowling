package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete 메소드는 답변을 삭제한다.")
    void delete() {
        DeleteHistory history = A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
        assertThat(history).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName("User 객체가 누락된 경우 답변을 삭제하는데 실패한다.")
    void delete_with_null() {
        assertThatThrownBy(() -> A1.delete(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("입력 값이 누락되었습니다.");
    }

    @Test
    @DisplayName("답변 작성자와 삭제 주체가 다른 경우 답변을 삭제하는데 실패한다.")
    void delete_with_not_equal_writer() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
