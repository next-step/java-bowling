package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("사용자가 답변자가 아닌 경우 UnAuthorizedException 예외를 throw한다.")
    @Test
    void validate_writer() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(UnAuthorizedException.class);
    }
}
