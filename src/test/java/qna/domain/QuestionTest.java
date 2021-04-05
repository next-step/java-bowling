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
    @DisplayName("질문 작성자와 로그인 한 유저가 다른 경우 예외 처리가 발생한다.")
    void writerAndUserIsNotEqualThrowException() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 작성자와 모든 답변글의 답변자가 같지 않은 경우 예외 처리가 발생한다.")
    void writerAndAllAnswerIsNotEqualThrowException2() {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> {
            Q1.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변이 없는 경우 정상적으로 삭제가 이뤄진다.")
    void canDeleteWhenAnswerIsNotPresent() throws Exception {
        Q2.delete(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("질문 작성자와 모든 답변글의 답변자가 같은 경우 정상적으로 삭제가 이뤄진다.")
    void canDeleteWhenWriterAndUserIsEqual() throws Exception {
        Q2.delete(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("삭제 후 이력에 대한 정보를 반환한다.")
    void returnDeleteHistory() throws Exception {
        Q2.addAnswer(AnswerTest.A2);

        DeleteHistories deleteHistories = Q2.delete(UserTest.SANJIGI);

        assertThat(deleteHistories.getDeleteHistories().get(0)).isEqualTo(new DeleteHistory(ContentType.QUESTION, Q2.getId(), Q2.getWriter(), LocalDateTime.now()));
    }
}
