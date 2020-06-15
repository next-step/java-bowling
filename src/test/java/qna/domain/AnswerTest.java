package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    public static final Answer A1J = new Answer(UserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents1");
    public static final Answer A2J = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3J = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A1S = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2");

    @DisplayName("질문자와 답변자가 다른 답변은 삭제할 수 없다.")
    @Test
    public void deleteValidationTest() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
