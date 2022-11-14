package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변자가 로그인 사용자와 같을 경우 정상 삭제")
    @Test
    void deleteTest() throws Exception {
        A1.delete(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("답변자가 로그인 사용자와 다를 경우 예외 발생")
    @Test
    void cannotDeleteTest() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
            .isExactlyInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
