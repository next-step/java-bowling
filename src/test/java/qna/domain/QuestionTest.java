package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("작성자가 답변 없는 질문을 삭제할 경우 성공")
    void deleteQuestionSuccess() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 질문을 삭제할 경우 실패")
    void deleteQuestionFailure() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 답변 글 모두 작성자가 동일한 경우 삭제가 성공")
    void deleteSuccessQuestionAndAnswerWithSameWriter() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자와 답변 글의 작성자가 다를 경우 삭제 실패")
    void deleteFailureQuestionAndAnswerWithOtherWriter() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
