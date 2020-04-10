package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

/*    @DisplayName("Answer 삭제 테스트")
    @Test
    public void deleteAnswerTest() {
        assertThatCode(A1.deleteByUser(UserTest.JAVAJIGI)).doesNotThrowAnyException();

        assertThatThrownBy(A2.deleteByUser(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }*/
}
