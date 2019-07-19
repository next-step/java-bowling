package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerTest {
    @Test
    void 플레이어의_이름을_입력받아서_생성한다() {
        String name = "HJS";
        Player playerName = Player.from(name);

        Assertions.assertThat(playerName.isSameName(name)).isTrue();
    }

    @Test
    void 플레이어의_이름이_세_자리_이상이면_예외가_발생한다() {
        String name = "test";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Player.from(name);
                }).withMessage(Player.ALERT_INVALID_PLAYER_NAME);
    }

    @Test
    void 플레이어의_이름이_세_자리_미만이면_예외가_발생한다() {
        String name = "HJ";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Player.from(name);
                }).withMessage(Player.ALERT_INVALID_PLAYER_NAME);
    }

    @Test
    void 플레이어의_이름에_한글이_포함되면_예외가_발생한다() {
        String name = "한JS";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Player.from(name);
                }).withMessage(Player.ALERT_INVALID_PLAYER_NAME);
    }

    @Test
    void 플레이어의_이름에_숫자가_포함되면_예외가_발생한다() {
        String name = "1nt";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Player.from(name);
                }).withMessage(Player.ALERT_INVALID_PLAYER_NAME);
    }

    @Test
    void 플레이어의_이름을_대문자로_바꾼다() {
        String name = "hjs";
        Player playerName = Player.from(name);

        Assertions.assertThat(playerName.isSameName("HJS")).isTrue();
    }
}
