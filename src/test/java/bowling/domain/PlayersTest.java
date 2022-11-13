package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("스트라이크 시 순서 건너뜀")
    void skip_turn_when_strike() {
        //given
        List<Player> players = Arrays.asList(new Player(new PlayerName("aaa")), new Player(new PlayerName("bbb")));
        Players game = new Players(players);
        //when
        game.play(a -> new Pin(10));
        game.play(b -> new Pin(2));
        Player now = game.play(b -> new Pin(5));
        //then
        assertThat(now.getPlayerName()).isEqualTo("bbb");
    }

    @Test
    @DisplayName("스트라이크 아니면 순서 건너뛰지 않음")
    void not_skip_turn_when_not_strike() {
        //given
        List<Player> players = Arrays.asList(new Player(new PlayerName("aaa")), new Player(new PlayerName("bbb")));
        Players game = new Players(players);
        //when
        game.play(a -> new Pin(5));
        game.play(b -> new Pin(2));
        Player now = game.play(b -> new Pin(5));
        //then
        assertThat(now.getPlayerName()).isEqualTo("aaa");
    }

    @Test
    @DisplayName("게임 중 확인")
    void is_gaming() {
        //given
        List<Player> players = Arrays.asList(new Player(new PlayerName("aaa")), new Player(new PlayerName("bbb")));
        Players game = new Players(players);
        //when
        game.play(a -> new Pin(10));
        game.play(b -> new Pin(10));
        //then
        assertThat(game.isGaming()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 확인")
    void is_finished() {
        //given
        List<Player> players = Arrays.asList(new Player(new PlayerName("aaa")), new Player(new PlayerName("bbb")));
        Players game = new Players(players);
        //when
        for (int i = 0; i < 12; i++) {
            game.play(a -> new Pin(10));
            game.play(b -> new Pin(10));
        }
        //then
        assertThat(game.isGaming()).isFalse();
    }

}