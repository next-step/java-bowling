package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @DisplayName("삭제가능 (계정동일, 답변없음)")
    @Test
    public void deleteTest() throws CannotDeleteException {
        assertThat(Q1.delete(UserTest.JAVAJIGI).isDeleted()).isTrue();
    }

    @DisplayName("삭제가능 (계정동일, 답변작성자와 질문작성자 동일)")
    @Test
    public void deleteSameOwnerTest() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);

        assertThat(Q1.delete(UserTest.JAVAJIGI).isDeleted()).isTrue();
    }

    @DisplayName("삭제불가 (계정다름)")
    @Test
    public void canNotDeleteDiffOwnerTest() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("삭제불가 (계정동일, 답변작성자와 질문작성자 다름)")
    @Test
    public void canNotDeleteDiffOwnerWithAnswerTest() {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }


    @DisplayName("질문과 답변의 총 사이즈만큼 삭제내역 생성")
    @Test
    public void deleteHistoriesSizeTest() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.createDeleteHistories()).hasSize(2);
    }
}
