package bowling.model.player;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayersTest {

    @Test
    void addNewPlayer_동일한_이름의_플레이어_추가() {
        Players players = new Players();
        players.addNewPlayer("aaa");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> players.addNewPlayer("AAA"))
                .withMessage("동일한 이름은 추가할 수 없습니다.");
    }

    @Test
    void addNewPlayer_플레이어_추가() {
        Players players = new Players();
        players.addNewPlayer("aaa");
        assertThat(players.nowPlayerName().toLowerCase()).isEqualTo("aaa");
    }

    @Test
    void bowling_STRIKE_후_플레이어_교체() {
        Players players = new Players();
        players.addNewPlayer("aaa");
        players.addNewPlayer("bbb");

        players.bowling(10);
        assertThat(players.nowPlayerName().toLowerCase()).isEqualTo("bbb");
    }

    @Test
    void bowling_SPARE_후_플레이어_교체() {
        Players players = new Players();
        players.addNewPlayer("aaa");
        players.addNewPlayer("bbb");

        players.bowling(1);
        players.bowling(9);
        assertThat(players.nowPlayerName().toLowerCase()).isEqualTo("bbb");
    }

    @Test
    void bowling_MISS_후_플레이어_교체() {
        Players players = new Players();
        players.addNewPlayer("aaa");
        players.addNewPlayer("bbb");

        players.bowling(1);
        players.bowling(1);
        assertThat(players.nowPlayerName().toLowerCase()).isEqualTo("bbb");
    }

    @Test
    void bowling_마지막_프레임_세번_플레이어_고정() {
        Players players = new Players();
        players.addNewPlayer("aaa");
        players.addNewPlayer("bbb");

        IntStream.range(0, 18).forEach(idx -> players.bowling(10));

        for (int i = 0; i < 3; i++){
            assertThat(players.nowPlayerName().toLowerCase()).isEqualTo("aaa");
            players.bowling(10);
        }

        for (int i = 0; i < 3; i++){
            assertThat(players.nowPlayerName().toLowerCase()).isEqualTo("bbb");
            players.bowling(10);
        }
    }

    @Test
    void isEnd_모두_스트라이크로_마무리() {
        Players players = new Players();
        players.addNewPlayer("aaa");
        players.addNewPlayer("bbb");

        IntStream.range(0, 23).forEach(idx -> players.bowling(10));
        assertThat(players.isGameEnd()).isFalse();
        players.bowling(10);
        assertThat(players.isGameEnd()).isTrue();
    }


    @Test
    void isEnd_마지막_프레임이_스트라이크가_아닌_경우() {
        Players players = new Players();
        players.addNewPlayer("aaa");
        players.addNewPlayer("bbb");

        IntStream.range(0, 18).forEach(idx -> players.bowling(10));
        assertThat(players.isGameEnd()).isFalse();

        players.bowling(1);
        assertThat(players.isGameEnd()).isFalse();
        players.bowling(2);
        assertThat(players.isGameEnd()).isFalse();
        players.bowling(3);
        assertThat(players.isGameEnd()).isFalse();
        players.bowling(4);
        assertThat(players.isGameEnd()).isTrue();
    }
}