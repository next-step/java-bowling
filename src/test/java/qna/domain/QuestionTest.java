package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("다른 작성자가 삭제할 때 에러가 발생하는지에 대한 테스트")
    void 예외처리_테스트_다른작성자_삭제시도() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("정상적인 작성자가 삭제시도할 떄 상태값이 변경되는지에 대한 테스트")
    void 삭제테스트_정상작성자_시도() throws CannotDeleteException {
        Question question = Q2;
        question.delete(UserTest.SANJIGI);
        assertThat(question.isDeleted()).isEqualTo(true);
    }
}
