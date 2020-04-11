package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTests {

    @DisplayName("Player 생성 테스트")
    @Test
    public void generatePlayerTest() {
        assertThatCode(() -> Player.newInstance("ABC"));
    }

    @DisplayName("Player 생성 오류 테스트")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"ABCD"})
    public void generatePlayerAbnormalTest(final String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Player.newInstance(name))
                .withMessageContaining("Player name must be three english character");
    }

    @DisplayName("Player 생성 오류 테스트")
    @Test
    public void generatePlayerNullTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Player.newInstance(null))
                .withMessageContaining("Player name is null");
    }
}
