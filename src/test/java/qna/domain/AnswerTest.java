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

    @DisplayName("답변을 삭제하고 해당 답변을 반환한다.")
    @Test
    void deleteAnswerSuccess() {
        Answer deletedAnswer = A1.delete();

        assertAll(
                () -> assertThat(deletedAnswer).isEqualTo(A1),
                () -> assertThat(deletedAnswer.isDeleted()).isTrue()
        );
    }
}
