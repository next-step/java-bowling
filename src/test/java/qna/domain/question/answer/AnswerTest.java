package qna.domain.question.answer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.question.QuestionTest;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.user.UserTest.JAVAJIGI;
import static qna.domain.user.UserTest.SANJIGI;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("로그인 한 사용자와 답변 작성자가 같으면 true를 반환한다")
    void shouldReturnTrueWhenOwner() {
        boolean result = A1.isOwner(JAVAJIGI);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로그인 한 사용자와 답변 작성자가 다르면 false를 반환한다")
    void shouldReturnFalseWhenNotOwner() {
        boolean result = A1.isOwner(SANJIGI);
        assertThat(result).isFalse();
    }
}
