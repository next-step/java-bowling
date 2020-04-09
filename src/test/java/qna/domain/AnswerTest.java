package qna.domain;

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

