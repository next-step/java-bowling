package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UsersTest {

    @Test
    @DisplayName("1명미만 에러 확인")
    public void exception() throws Exception {
        assertThatThrownBy(() -> Users.of(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
