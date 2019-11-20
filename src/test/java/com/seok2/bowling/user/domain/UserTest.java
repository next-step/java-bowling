package com.seok2.bowling.user.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("플레이어의 이름은 3글자를 초과하여 지정 할 수 없다.")
    void exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> User.of("Pray"));
    }
}