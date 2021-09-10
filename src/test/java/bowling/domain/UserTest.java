package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class UserTest {
    @DisplayName("유효하지 않는 이름 테스트")
    @Test
    public void invalidNameTest() {
        //given
        String name = "1234";

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new User(name));
    }
}
