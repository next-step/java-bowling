package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른 사람이 쓴 댓글을 삭제하려고 할 경우 Exceptiond을 반환해야 한다.")
    void assertIsOtherUsersAnswer() {
        assertThatThrownBy(() -> {
            A1.assertUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage(Answer.NO_DELETE_AUTHORITY_ANSWER_ERROR);
    }
}
