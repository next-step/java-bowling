package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void validation_작성자_본인_여부_정상() {
        assertThatCode(() -> A1.validateOwner(UserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    void validation_작성자_본인_여부_오류() {
        assertThatThrownBy(() -> A1.validateOwner(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 댓글_삭제() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }
}
