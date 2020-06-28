package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("로그인유저와 작성자가 다르면 Exception")
    @Test
    void failTest() {
        assertThatThrownBy(() -> A1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인유저와 작성자가 같으면 삭제하고 삭제기록을 반환한다")
    @Test
    void success() {
        DeleteHistory deleteHistory = A1.deleteBy(UserTest.JAVAJIGI);
        assertAll(
                () -> assertThat(deleteHistory).isNotNull(),
                () -> assertThat(A1.isDeleted()).isTrue()
        );
    }

}
