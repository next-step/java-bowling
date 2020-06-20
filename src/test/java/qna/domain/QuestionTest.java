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
    @DisplayName("질문 삭제 권한이 없을 경우")
    void validateOwner() throws CannotDeleteException {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문 삭제")
    void deleteQuestion() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("질문&답글 삭제 이력 생성")
    void makeDeleteHistories() throws CannotDeleteException {
        Q2.addAnswer(new Answer(UserTest.SANJIGI, Q2, "Answers Contents2"));
        DeleteHistories deleteHistories = Q2.delete(UserTest.SANJIGI);
        assertThat(deleteHistories.getDeleteHistories()).hasSize(2);
    }
}
