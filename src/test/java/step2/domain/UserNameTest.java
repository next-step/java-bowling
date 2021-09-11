package step2.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.exceptions.UserNameLengthError;

import static org.junit.jupiter.api.Assertions.*;

class UserNameTest {
    @DisplayName("유저이름이 4글자인 경우 에러 출력")
    @Test
    void userNameTest1() {
        Assertions.assertThatThrownBy(() -> {
            new UserName("abcd");
        }).isInstanceOf(UserNameLengthError.class);
    }

    @DisplayName("유저이름이 2글자인 경우 에러 출력")
    @Test
    void userNameTest2() {
        Assertions.assertThatThrownBy(() -> {
            new UserName("ab");
        }).isInstanceOf(UserNameLengthError.class);
    }

    @DisplayName("유저이름이 3글자인 경우 정상 출력")
    @Test
    void userNameTest3() {
        new UserName("abc");
    }
}