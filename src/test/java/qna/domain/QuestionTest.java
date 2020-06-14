package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;
import qna.fixture.Fixture;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {

    private final Question question1 = Fixture.of().getQuestion1();
    private final Question question2 = Fixture.of().getQuestion2();

    @DisplayName("로그인한 유저가 작성자가 아닌 경우 예외 발생")
    @Test
    public void verifyOwner() {
        User loginUser = new User();
        loginUser.setUserId("heejeong");

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question1.verifyOwner(loginUser));
    }
}
