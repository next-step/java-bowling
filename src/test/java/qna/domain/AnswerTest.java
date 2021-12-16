package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void delete_성공_유저와_답변쓴이_같음() throws CannotDeleteException {
        // given
        User loginUser = UserTest.JAVAJIGI;

        // when
        A1.delete(loginUser);

        // then
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_실패_유저와_답변쓴이_다름() {
        // given
        User loginUser = UserTest.JAVAJIGI;

        // then
        assertThatThrownBy(() -> {
            A2.delete(loginUser);
        })
        .isInstanceOf(CannotDeleteException.class)
        .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
