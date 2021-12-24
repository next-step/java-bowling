package qna.domain.question.answer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.user.UserTest;
import qna.domain.question.QuestionTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("로그인 한 사용자와 답변 작성자가 같으면 true를 반환한다")
    void shouldReturnTrueWhenOwner() throws CannotDeleteException {
        boolean result = A1.isOwner(UserTest.JAVAJIGI);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로그인 한 사용자와 답변 작성자가 다르면 예외를 던진다")
    void shouldReturnFalseWhenNotOwner() {
        assertThatThrownBy(() -> A1.isOwner(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

    }
}
