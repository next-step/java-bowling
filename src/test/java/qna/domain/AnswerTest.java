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
    @DisplayName("질문자와 답변자가 다른 경우 답변을 삭제 할 수 없다.")
    void delete_실패() {
        assertThatThrownBy(
                () -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class
        );
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.")
    void delete_성공() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }
}
