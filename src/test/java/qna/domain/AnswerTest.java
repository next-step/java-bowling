package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.QuestionTest.DELETE_HISTORIES;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능")
    void delete_ok() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI, DELETE_HISTORIES);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 답변자가 다른 경우 삭제 실패")
    void delete_fail() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI, DELETE_HISTORIES))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }


    @Test
    @DisplayName("삭제 완료시 deleteHistory를 추가")
    void check_deleteHistory() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI, DELETE_HISTORIES);
        assertThat(DELETE_HISTORIES.size()).isEqualTo(1);
    }

}
