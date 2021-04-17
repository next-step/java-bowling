package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answer 삭제할때 작성자가 다르면 예외가 발생한다")
    void checkPossibleDelete() {
        assertThatThrownBy(() -> {
            A1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("Answer 삭제하면 deleted 가 true 로 변경된다")
    void delete() {
        // given
        A1.delete(UserTest.JAVAJIGI);

        // when
        boolean isDeleted = A1.isDeleted();

        // then
        assertThat(isDeleted).isTrue();
    }

    @Test
    @DisplayName("Answer 삭제하면 deleteHistory 생성된다")
    void deleteHistory() {
        // given
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);

        // when
        DeleteHistory expectedDeleteHistory =
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now());

        // then
        assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
    }
}
