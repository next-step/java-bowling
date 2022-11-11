package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void testOwnerValidation() {
        User user = new User("user1", "jackson", "jackson", "jackson");
        assertThatThrownBy(() -> Q1.validateIfIsOwner(user)).isInstanceOf(CannotDeleteException.class);
    }
}
