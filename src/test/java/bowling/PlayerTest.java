package bowling;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new Player("PJS")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어의 이름은, 3글자를 입력해야한다.")
    void createValidTest() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Player("TT"));
    }
}
