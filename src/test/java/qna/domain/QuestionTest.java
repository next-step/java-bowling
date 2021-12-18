package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문자와 작성자가 동일하지 않으면 삭제 권한이 없음 -> CannotDeleteException 반환")
    void checkQuestionException() {
        assertThatThrownBy(() -> Q1.checkDeleteAuth(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 작성자가 동일하면 삭제 권한 가짐")
    void checkQuestionOK() {
        assertThatCode(() -> Q1.checkDeleteAuth(UserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("질문 삭제 -> delete 상태값 확인, 히스토리 안에 저장 유무 확인")
    void delete() throws CannotDeleteException {
        DeleteHistories histories = new DeleteHistories();
        Q1.delete(UserTest.JAVAJIGI, histories);
        Q2.delete(UserTest.SANJIGI, histories);
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(Q2.isDeleted()).isTrue();
        assertThat(histories.getHistories()).contains(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
        assertThat(histories.getHistories()).contains(new DeleteHistory(ContentType.QUESTION, Q2.getId(), Q2.getWriter(), LocalDateTime.now()));
    }

}
