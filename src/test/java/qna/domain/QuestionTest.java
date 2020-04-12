package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deleteTest() {
        assertThat(Q1.isDeleted()).isFalse();
    }

    @Test
    void isSameOwnerTest() {
        assertThatThrownBy(() -> Q1.canDelete(Q2.getWriter())).isInstanceOf(CannotDeleteException.class);
    }

}
