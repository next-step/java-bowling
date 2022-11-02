package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른 작성자가 삭제할 때 에러가 발생하는지에 대한 테스트")
    void 예외처리_테스트_다른작성자_삭제시도() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("정상적인 작성자가 삭제시도할 떄 상태값이 변경되는지에 대한 테스트")
    void 삭제테스트_정상작성자_시도() throws CannotDeleteException {
        Answer answer = A2;
        answer.delete(UserTest.SANJIGI);
        assertThat(answer.isDeleted()).isEqualTo(true);
    }
}
