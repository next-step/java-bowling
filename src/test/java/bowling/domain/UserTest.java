package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class UserTest {
    @Test
    void 유저이름약자가_3글자가_아닌경우() {
        assertThatThrownBy(() -> {
            new User("ab");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}