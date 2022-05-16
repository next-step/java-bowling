package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제 시, 답변등록자랑 로그인사용자가 동일하면 삭제 가능")
    @Test
    public void deletedTest() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI).isDeleted()).isTrue();
    }

    @DisplayName("삭제시, 답변등록자랑 로그인사용자가 다르면 CannotDeleteException 발생")
    @Test
    public void canNotDeletedTest() throws CannotDeleteException {
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("답변을 삭제할 권한이 없습니다.");
    }

    @DisplayName("삭제 시, 질문자와 답변자가 달라 CannotDeleteException 발생")
    @Test
    public void diffQuestionOwnerAndAnswerOwnerTest() throws CannotDeleteException {
        assertThatThrownBy(() -> A2.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문자와 답변자가 달라 답변을 삭제할 수 없습니다.");
    }
}
