package qna.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("Answer 삭제 테스트")
    @Test
    public void deleteAnswerTest() {
        assertThatCode(() -> A1.deleteByUser(UserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("Answer 삭제 테스트 - 삭제 불가")
    @Test
    public void cannotDeleteAnswerTest() {
        assertThatThrownBy(() -> A1.deleteByUser(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
