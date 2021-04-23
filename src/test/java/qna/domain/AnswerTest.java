package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("로그인 유저가 작성한 답변이 아닌경우 예외를 반환한다.")
    @Test
    void checkWriter_예외() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인 유저가 작성한 답변일 경우 true를 반환한다.")
    @Test
    void checkWriter() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        A2.delete(UserTest.SANJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }
}
