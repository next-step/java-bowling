package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("작성자와 로그인한 사용자가 다를 경우 예외를 던진다.")
    public void Given_CheckOwner_Then_Throw() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 로그인한 사용자가 같을 경우 예외를 던지지 않는다.")
    public void Given_CheckOwner_Then_NotThrow() {

        assertThatNoException().isThrownBy(() -> A1.delete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("isDeleted 필드가 true 로 업데이트된다.")
    public void Given_SoftDelete_Then_Deleted() {
        assertThat(A1.isDeleted()).isFalse();
        A1.delete(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }
}
