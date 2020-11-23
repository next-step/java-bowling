package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    private static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private boolean delete(Question Q, User loginUser) throws CannotDeleteException {
        Q.delete(loginUser);
        return Q.isDeleted();
    }

    @Test
    @DisplayName("정상적으로 삭제하면, isDeleted 가 true 가 된다.")
    void delete_success() {
        assertAll(
                () -> assertThat(Q1.isDeleted())
                        .isFalse(),
                () -> assertThat(Q2.isDeleted())
                        .isFalse(),
                () -> assertThat(delete(Q1, UserTest.JAVAJIGI))
                        .isTrue(),
                () -> assertThat(delete(Q2, UserTest.SANJIGI))
                        .isTrue()
        );
    }

    @Test
    @DisplayName("로그인한 유저와 다른 question 을 삭제하면, CannotDeleteException 이 발생한다.")
    void delete_fail_question() {
        assertAll(
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> delete(Q1, UserTest.SANJIGI))
                        .withMessage("질문을 삭제할 권한이 없습니다."),
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> delete(Q2, UserTest.JAVAJIGI))
                        .withMessage("질문을 삭제할 권한이 없습니다.")
        );
    }

    @Test
    @DisplayName("다른 사람이 쓴 answer 가 있으면, CannotDeleteException 이 발생한다.")
    void delete_fail_answer() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(1L, UserTest.JAVAJIGI, question, "contents"));
        question.addAnswer(new Answer(1L, UserTest.SANJIGI, question, "contents"));
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> delete(question, UserTest.JAVAJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
