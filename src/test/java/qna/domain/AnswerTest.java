package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 작성자가 아닌 유저가 답변을 삭제하려하면 예외가 발생한다.")
    @Test
    void checkDeletePermissions() {
        assertThatThrownBy(() -> A1.checkDeletePermissions(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteAndAddHistory() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistories deleteHistories = new DeleteHistories();

        DeleteHistories addedDeleteHistories = answer.deleteAndAddHistory(deleteHistories);

        assertAll(
            () -> assertThat(answer.isDeleted()).isTrue(),
            () -> assertThat(addedDeleteHistories.size()).isEqualTo(1)
        );
    }
}
