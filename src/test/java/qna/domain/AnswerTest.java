package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_다른사람답변_예외() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void delete_본인답변삭제() {
        assertThat(A2.isDeleted()).isFalse();
        A2.delete(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    void delete_삭제이력() {
        assertThat(A1.delete(UserTest.JAVAJIGI)).isEqualTo(new DeleteHistory(A1));
    }

}
