package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인한 유저가 작성자가 아니면 예외를 발생한다.")
    @Test
    void throwCannotDeleteException() {
        User loginUser = new User();
        loginUser.setUserId("1L");
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(
                () -> Q1.checkSameWriter(loginUser)
        );
    }
}
