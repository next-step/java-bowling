package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Answer 클래스 테스트")
public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("Answer를 삭제할 수 있다.")
    @Test
    void delete_success() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @DisplayName("다른 사람이 쓴 답변이 있는 경우 예외를 반환한다.")
    @Test
    void delete_fail() {
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
