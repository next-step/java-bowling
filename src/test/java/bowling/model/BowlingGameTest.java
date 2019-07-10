package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @DisplayName("참가하는 플레이어를 주입하는데 성공한다")
    @Test
    void setting_inputPlayer_success() {
        // given
        Player player = Player.of("YJY");

        // when
        BowlingGame bowlingGame = BowlingGame.settingOf(player);

        // then
        assertThat(bowlingGame).isNotNull();
    }
}