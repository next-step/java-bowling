package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 작성자와 로그인 한 유저가 다른 경우 예외 처리가 발생한다.")
    void writerAndUserIsNotEqualThrowException() {
        assertThatThrownBy(() -> {
            A1.validate(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("정상적으로 삭제가 이뤄진다.")
    void canDeleteWhenWriterAndUserIsEqual() {
        A1.delete();
        assertThat(A1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("삭제 후 이력에 대한 정보를 반환한다.")
    void returnDeleteHistory() {
        assertThat(A1.delete()).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }
}
