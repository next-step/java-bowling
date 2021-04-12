package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문 작성자와 로그인 유저가 다른경우 예외 발생 테스트")
    public void 예외발생_테스트() {
        assertThatThrownBy(() -> Q1.validate(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 작성자와 답변글의 답변자가 같지 않은 경우 예외 처리가 발생 테스트")
    void writerAndAllAnswerIsNotEqualThrowException2() {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> {
            Q1.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 하는 테스트")
    void 삭제_테스트() throws CannotDeleteException {
        Q1.deleteQuestion(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
