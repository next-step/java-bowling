package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Test
    @DisplayName("참여자 이름 3글자 초과 에러 확인")
    public void userNameMaxLength3OverException() throws Exception {
        assertThatThrownBy(() -> {
            new User("povi");
        });
    }
}
