package bowling.domain;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {
    @Test
    void 유저_이름이_3글자_아닌_경우() {
        assertThatThrownBy(() -> {
            new User("ab");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}