package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변을 삭제할 수있는지 체크하고, 삭제 할 수 없다면 예외를 발생시킨다.")
    @Test
    void validateAnswer() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            A1.validateAnswer(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 삭제하라는 메세지를 주고 삭제 할 수 있는 객체인지 확인 해본다.")
    @Test
    void deleteAnswer() {
        A1.delete();
        boolean deleted = A1.isDeleted();
        assertThat(deleted).isTrue();
    }
}

