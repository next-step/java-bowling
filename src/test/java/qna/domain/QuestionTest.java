package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("Question 삭제")
    void delete() {
        // when
        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);

        // then
        Assertions.assertThat(deleteHistories.get(0))
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("Question 삭제 실패 : 작성자와 loginUser 일치 여부 확인")
    void delete_fail_different_user() {
        // when, then
        Assertions.assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("Question 삭제 실패 : loginUser 외 댓글 작성자 여부 확인")
    void delete_fail_different_comment_user() {
        // when, then
        Assertions.assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
