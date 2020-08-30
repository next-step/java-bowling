package bowling.domain.player;

import bowling.domian.player.Player;
import bowling.domian.player.exception.InvalidNameException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "AA", "AAAA", "$kk"})
    @DisplayName("사용자의 이름이 3글자 아닌 경우 Exception")
    public void nameValidation(String name) {
        assertThatExceptionOfType(InvalidNameException.class).isThrownBy(
                () -> Player.get(name)
        );
    }

    @Test
    @DisplayName("사용자의 투구 프레임 확인")
    public void getFrameNumberOfPlayer() {
        Player player = Player.get("AAA");

        assertThat(player.isFrameNumber(1)).isTrue();
        player.bowl(10);

        assertThat(player.isFrameNumber(2)).isTrue();
    }
}
