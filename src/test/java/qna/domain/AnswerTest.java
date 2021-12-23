package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("로그인 한 사용자와 답변 작성자가 같으면 true를 반환한다")
    void shouldReturnTrueWhenOwner() {
        boolean result1 = A1.isOwner(UserTest.JAVAJIGI);
        boolean result2 = A2.isOwner(UserTest.SANJIGI);

        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
    }

    @Test
    @DisplayName("로그인 한 사용자와 답변 작성자가 다르면 true를 반환한다")
    void shouldReturnFalseWhenNotOwner() {
        boolean result1 = A1.isOwner(UserTest.SANJIGI);
        boolean result2 = A2.isOwner(UserTest.JAVAJIGI);

        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
    }
}
