package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 작성자가_아닐시_예외처리() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.delete(UserTest.SANJIGI));
    }

    @Test
    void 삭제성공() throws CannotDeleteException {
         assertThat(A1.delete(UserTest.JAVAJIGI).isDeleted()).isTrue();
    }
}
