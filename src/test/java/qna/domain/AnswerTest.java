package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 테스트")
    void delete() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        A2.delete(UserTest.SANJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른 유저가 삭제 요청시 CannotDeleteException")
    void delete_other_then_exception() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
