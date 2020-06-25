package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("정상 삭제")
    void deletedTest() {
        assertThatCode(() -> A1.deleteByOwner(UserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatCode(() -> A2.deleteByOwner(UserTest.SANJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("타인의 댓글 삭제시 예외처리")
    void notAvailableDeletedTest() {
        assertThatThrownBy(() -> A2.deleteByOwner(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A1.deleteByOwner(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
