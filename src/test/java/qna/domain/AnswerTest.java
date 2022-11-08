package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변_삭제_성공() throws CannotDeleteException {
        DeleteHistory history = A1.delete(UserTest.JAVAJIGI);
        assertAll(
                () -> assertThat(A1.isDeleted()).isTrue(),
                () -> assertThat(history).isNotNull()
        );
    }

    @Test
    void 작성자가_다른_유저일_경우_예외_발생() {
        assertAll(
                () -> assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                        .isInstanceOf(CannotDeleteException.class)
                        .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."),
                () -> assertThat(A2.isDeleted()).isFalse()
        );
    }
}
