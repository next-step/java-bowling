package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    @DisplayName("삭제여부 테스트 성공")
    void 삭제여부_테스트_성공() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("삭제여부 테스트 실패")
    void 삭제여부_테스트_실패() {
        assertThatThrownBy(
                () -> A1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
