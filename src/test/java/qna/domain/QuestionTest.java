package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void 삭제_성공() throws CannotDeleteException
    {
        User user = UserTest.JAVAJIGI;

        Q1.delete(user);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void 삭제_실패()
    {
        User user = UserTest.SANJIGI;

        assertThatThrownBy(() ->
            Q1.delete(user)).isInstanceOf(CannotDeleteException.class);
    }


}
