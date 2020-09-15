package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제 테스트")
    @Test
    public void deleteTest() throws CannotDeleteException {
        assertThat(A1.deleteBy(A1.getWriter()))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @DisplayName("삭제 예외처리 테스트")
    @Test
    public void cannotDeleteTest() {
        assertThatThrownBy(() -> A1.deleteBy(A2.getWriter()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
