package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswersTest.A1;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void isOwner() {
        assertThat(Q1.isOwner(JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("작성자가 본인이면 삭제 가능")
    void delete_owner() throws CannotDeleteException {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.deleteQnA(JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 본인이 아니면 삭제 불가능")
    void delete_not_owner() {
        assertThatThrownBy(() -> Q2.deleteQnA(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                        .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("다른 사람이 작성한 답변이 있을 경우 삭제 불가능")
    void delete_answer_not_owner() {
        Q2.addAnswer(A1);
        assertThatThrownBy(() -> Q2.deleteQnA(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
