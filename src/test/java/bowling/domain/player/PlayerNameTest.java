package bowling.domain.player;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerNameTest {
    public static final PlayerName NAME_ONE = new PlayerName("LMJ");

    @ParameterizedTest
    @NullAndEmptySource
    void PlayerName은_name없이_생성_될_경우_예외를_발생_시킨다(String name) {
        assertThatThrownBy(() -> {
            new PlayerName(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"LMJJ", "LM"})
    void PlayerName은_길이가_유효하지_않으면_예외를_발생_시킨다(String name) {
        assertThatThrownBy(() -> {
            new PlayerName(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
