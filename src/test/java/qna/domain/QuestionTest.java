package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인한 유저가 작성자가 아닌 경우 예외 발생")
    @Test
    public void verifyOwner() {
        User loginUser = new User();
        loginUser.setUserId("heejeong");

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.verifyOwner(loginUser));
    }
}
