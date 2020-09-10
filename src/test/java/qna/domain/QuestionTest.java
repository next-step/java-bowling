package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    public void delete_정상적으로_삭제처리되어_상태가_변하는지_확인() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertTrue(Q1.isDeleted());
    }

    @Test
    public void delete_작성자와_요청자가_다를때_확인() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_삭제이력_추가_확인() throws CannotDeleteException {
        DeleteHistories deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
    }
}
