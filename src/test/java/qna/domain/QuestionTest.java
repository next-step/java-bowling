package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;

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
        Q1.delete(JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 본인이 아니면 삭제 불가능")
    void delete_not_owner() {
        assertThatThrownBy(() -> Q2.delete(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                        .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
