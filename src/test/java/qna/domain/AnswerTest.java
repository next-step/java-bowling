package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1,
        "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1,
        "Answers Contents2");


    @Test
    @DisplayName("삭제가 정상적으로 되는지 확인한다.")
    void deleteTest() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI)).isEqualTo(
            new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("삭제시 로그인 사용자와, 답변의 답변자가 다른경우 예외가 발생한다.")
    void deleteExceptionTest() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.delete(UserTest.SANJIGI));
    }
}
