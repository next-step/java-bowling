package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Test
    void createTest() {
        User user = new User("LEE");

        assertThat(user).isEqualTo(new User("LEE"));
    }

    @Test
    @DisplayName("이름이 3글자를 넘어갈 때 오류 발생 검증")
    void nameExceptionTest() {
        assertThatThrownBy(() ->
                new User("TEST")).isInstanceOf(IllegalArgumentException.class);
    }
}
