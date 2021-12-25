package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents3");
    public static final Answer A4 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q3, "Answers Contents3");

    @DisplayName("다른 사람이 쓴 답변은 삭제 불가능")
    @Test
    void delete_다른사람답변_예외() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("본인이 쓴 답변은 삭제 가능")
    @Test
    void delete_본인답변삭제() {
        assertThat(A2.isDeleted()).isFalse();
        A2.delete(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
    }

    @DisplayName("본인이 쓴 답변을 삭제할 경우, 삭제 이력이 남음")
    @Test
    void delete_삭제이력() {
        assertThat(A1.delete(UserTest.JAVAJIGI)).isEqualTo(new DeleteHistory(A1));
    }

}
