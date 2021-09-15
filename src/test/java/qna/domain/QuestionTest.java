package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문 작성자와 로그인 유저가 다른 경우 삭제 불가")
    void validateAuthorAreSameTest() {
        assertThatThrownBy(() -> Q1.validateAuthorAreSame(UserTest.SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 작성자와 답변 작성자가 같은 경우 삭제 가능")
    void validateThereIsAnyonesElseAnswerSuccessTest() throws CannotDeleteException {
        final Answer answer = new Answer(UserTest.JAVAJIGI, Q1, "");
        Q1.addAnswer(answer);
        Q1.validateThereIsAnyonesElseAnswer(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("질문 작성자와 답변 작성자가 다른 경우 삭제 불가")
    void validateThereIsAnyonesElseAnswerExceptionTest() throws CannotDeleteException {
        final Answer answer = new Answer(UserTest.SANJIGI, Q1, "");
        Q1.addAnswer(answer);

        assertThatThrownBy(() -> Q1.validateThereIsAnyonesElseAnswer(UserTest.JAVAJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }
}
