package bowling.domain.player;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void 생성_테스트() {
        // given
        Players players = new Players(Arrays.asList(Player.of("ldh"), Player.of("kkk")));
        // when & then
        assertThat(players.toList().size()).isEqualTo(2);
    }

    @Test
    void 모든_플레이어_종료_테스트() {
        // given
        Player player = Player.of("ldh");
        Player player2 = Player.of("kkk");

        // when
        IntStream.range(0, 10).forEach(i -> {
            player.bowl(3);
            player.bowl(3);

            player2.bowl(3);
            player2.bowl(3);
        });

        Players players = new Players(Arrays.asList(player, player2));
        // then
        assertThat(players.isAllDone()).isTrue();
    }

    @Test
    void 모든_플레이어_미종료_테스트() {
        // given
        Player player = Player.of("ldh");
        Player player2 = Player.of("kkk");

        // when
        IntStream.range(0, 10).forEach(i -> {
            player.bowl(3);
            player.bowl(3);
        });
        IntStream.range(0, 9).forEach(i -> {
            player.bowl(3);
            player.bowl(3);
        });

        Players players = new Players(Arrays.asList(player, player2));
        // then
        assertThat(players.isAllDone()).isFalse();
    }
}
