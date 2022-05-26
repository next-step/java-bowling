package bowling.domain;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingGameTest {
    @DisplayName("볼링게임 객체를 생성한다.")
    @ParameterizedTest
    @NullSource
    void BowlingGame_생성(Player player) {
        assertThatThrownBy(() -> new BowlingGame(player, Frames.initialize())).isInstanceOf(IllegalArgumentException.class);
    }
}