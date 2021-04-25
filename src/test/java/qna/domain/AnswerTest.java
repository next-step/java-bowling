package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("자신의 질문은 삭제 할 수 있다")
    @Test
    void answerDeleteTest() {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isEqualTo(TRUE);
    }

    @DisplayName("타인의 질문은 삭제할 수 없다")
    @Test
    void answerDeleteExceptionTest() {
        assertThatThrownBy(()-> A2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
