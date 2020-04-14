package bowling.domain;

import bowling.dto.PlayerDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    @Test
    void of() {
        assertThatCode(() -> Player.of("SKT"))
                .doesNotThrowAnyException();
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> Player.of("tester"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getCurrentFrameNumber() {
        Player player = Player.of("skt");
        assertThat(player.getCurrentFrameNumber())
                .isEqualTo(1);

        player.shot(10);
        assertThat(player.getCurrentFrameNumber())
                .isEqualTo(2);

        player.shot(5);
        assertThat(player.getCurrentFrameNumber())
                .isEqualTo(2);
    }

    @Test
    void shot() {
        PlayerDto currentDto = Player.of("skt").shot(5);
        assertThat(currentDto.getName())
                .isEqualTo("skt");

        assertThat(currentDto.getFrames())
                .anyMatch(v -> v.getShotScores().get(0).getScore() == 5)
                .hasSize(1);

    }
}