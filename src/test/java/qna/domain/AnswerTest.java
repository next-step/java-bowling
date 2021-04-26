package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 작성자가 본인이 아니면, 삭제할 수 없다.")
    void cannotDeleteAnswerByOtherUserTest() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변 작성자가 본인이면, 삭제하고 히스토리를 남긴다.")
    void deleteAnswerByWriterTest() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI)).isInstanceOf(DeleteHistory.class);
    }
}
