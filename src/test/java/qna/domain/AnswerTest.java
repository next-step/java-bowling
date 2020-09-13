package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("답변 테스트")
public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제(A1: 작성자 JAVAJIGI)")
    @Test
    public void delete_A1() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제(A1: 작성자 SANJIGI)")
    @Test
    public void delete_A2() throws CannotDeleteException {
        A2.delete(UserTest.SANJIGI);
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
