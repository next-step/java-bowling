package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("글쓴이를 확인한다.")
    @Test
    void validWriter() throws CannotDeleteException {
        A1.validateUserAuthority(UserTest.JAVAJIGI);
        A2.validateUserAuthority(UserTest.SANJIGI);
    }

    @DisplayName("글쓴이가 아니면 에러가 발생한다.")
    @Test
    void validWriterException() {
        assertThatThrownBy(() -> A1.validateUserAuthority(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.validateUserAuthority(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
