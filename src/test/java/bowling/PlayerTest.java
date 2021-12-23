package bowling;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void init() {
        player = new Player("PJS");
    }

    @Test
    @DisplayName("플레이어의 이름은, 3글자를 입력해야한다.")
    void createValidTest() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Player("TT"));
    }
}
