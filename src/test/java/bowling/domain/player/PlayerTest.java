package bowling.domain.player;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void 생성_테스트() {
        // given
        Player player = Player.of("LDH");
        // when & then
        assertThat(player.name()).isEqualTo(PlayerName.of("LDH").toString());
        assertThat(player.states().size()).isEqualTo(1);
    }

    @Test
    void 플레이_테스트() {
        // given
        Player player = Player.of("LDH");
        // when & then
        assertThat(player.isDone()).isFalse();
        IntStream.range(0, 10).forEach(i -> {
            player.bowl(3);
            player.bowl(3);
            player.changeNextFrame();
        });
        assertThat(player.isDone()).isTrue();
    }
}
