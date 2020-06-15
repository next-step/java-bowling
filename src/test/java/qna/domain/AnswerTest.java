package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    public static final Answer A1J = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents1");
    public static final Answer A2J = new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3J = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A1S = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2");

    @DisplayName("정상적으로 삭제된 경우 해당 답변의 삭제 이력을 반환한다.")
    @Test
    public void afterDeleteGetHistory() {
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistory)
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("질문자와 답변자가 다른 답변은 삭제할 수 없다.")
    @Test
    public void deleteValidationTest() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
