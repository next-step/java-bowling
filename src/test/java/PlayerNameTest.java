import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerNameTest {
    @Test
    void 플레이어의_이름을_입력받아서_생성한다() {
        String name = "HJS";
        PlayerName playerName = PlayerName.from(name);

        assertThat(playerName.getName()).isEqualTo(name);
    }

    @Test
    void 플레이어의_이름이_세_자리_이상이면_예외가_발생한다() {
        String name = "test";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    PlayerName.from(name);
                }).withMessage(PlayerName.ALERT_INVALID_PLAYER_NAME);
    }

    @Test
    void 플레이어의_이름이_세_자리_미만이면_예외가_발생한다() {
        String name = "HJ";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    PlayerName.from(name);
                }).withMessage(PlayerName.ALERT_INVALID_PLAYER_NAME);
    }

    @Test
    void 플레이어의_이름에_한글이_포함되면_예외가_발생한다() {
        String name = "한JS";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    PlayerName.from(name);
                }).withMessage(PlayerName.ALERT_INVALID_PLAYER_NAME);
    }

    @Test
    void 플레이어의_이름에_숫자가_포함되면_예외가_발생한다() {
        String name = "1nt";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    PlayerName.from(name);
                }).withMessage(PlayerName.ALERT_INVALID_PLAYER_NAME);
    }
}
