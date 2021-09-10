package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("Question을 삭제한다.")
    void delete() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertTrue(Q1.isDeleted());
    }

    @Test
    @DisplayName("질문의 주인이 다르면 CannotDeleteException 이 발생한다.")
    void validate() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .withFailMessage("질문을 삭제할 권한이 없습니다.");
    }
}
