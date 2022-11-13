package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @AfterEach
    void clear() {
       Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
       Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @Test
    @DisplayName("질문 삭제 성공")
    void testDeleteSuccess() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        deleteHistories.forEach(deleteHistory -> assertThat(deleteHistory.isDeletedBy(Q1.getWriter())).isTrue());
    }

    @Test
    @DisplayName("다른 사람이 작성한 질문은 삭제할 수 없다.")
    void testDeleteFailByOtherUser() {
        assertThatThrownBy(() -> Q1. deleteBy(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("다른 사람이 쓴 답변이 존재하는 질문은 삭제할 수 없다.")
    void testDeleteFailByExistingOtherAnswers() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.deleteBy(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
