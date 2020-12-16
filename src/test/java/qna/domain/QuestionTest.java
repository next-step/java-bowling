package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("login 유저가 본인 소유가 아닌 질문을 삭제하려 할 때의 예외 처리")
    void testQuestionsOwnerValidation() {
        assertThatThrownBy(
                () -> Q1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문글에 자신의 소유가 아닌 답변글이 포함되어 있을 시 예외 처리")
    void testQuestionsAnswerValidation() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(
                () -> Q1.delete(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
