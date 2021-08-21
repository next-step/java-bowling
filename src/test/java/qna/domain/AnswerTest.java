package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 같은지_테스트() throws CannotDeleteException {
        A1.otherPersonComment(UserTest.JAVAJIGI);
        assertThat(A1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
    }

    @Test
    void 예외처리() {
        assertThatThrownBy(() -> {
            A1.otherPersonComment(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);

    }
}
