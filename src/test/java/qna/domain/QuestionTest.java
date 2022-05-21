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
    void loginUserIsNotOwnerGiven_ThrowException() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void loginUserIsOwnerGiven_ReturnSuccess() throws CannotDeleteException {
        DeleteHistories deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("로그인 사용자가 질문의 작성자이지만 답변은 아닐 경우 삭제 불가")
    @Test
    void loginUserIsOwnerButIsNotAnswerOwnerGiven_ThrowExp() throws CannotDeleteException {
        Q2.addAnswer(AnswerTest.A1);
        assertThatThrownBy(() -> Q2.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("로그인 사용자가 질문과 답변 모두의 작성자이면 삭제 성공")
    @Test
    void loginUserIsOwnerAndIsAnswerOwnerGiven_ReturnSuccess() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        DeleteHistories deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories.size()).isEqualTo(2);
    }
}
