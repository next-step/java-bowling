package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제")
    void deleteAnswer() {
        // given
        Answer actual = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        actual.deleteAnswer(UserTest.JAVAJIGI);

        // then
        assertTrue(actual.isDeleted());
    }

    @Test
    @DisplayName("삭제 실패")
    void deleteAnswer_validate() {
        // when
        Answer actual = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThatThrownBy(() ->
                actual.deleteAnswer(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
