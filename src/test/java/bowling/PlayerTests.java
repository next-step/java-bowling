package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTests {

    @DisplayName("Player 생성 테스트")
    @Test
    public void generatePlayerTest() {
        assertThat(Player.newInstance("ABC"));
    }

    @DisplayName("Player 생성 오류 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"ABCD"})
    public void generatePlayerAbnormalTest(final String name) {
        assertThat(Player.newInstance(name));
    }
}
