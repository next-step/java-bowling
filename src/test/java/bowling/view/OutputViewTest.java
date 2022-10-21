package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.PlayerName;
import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class OutputViewTest {

    @Test
    @DisplayName("게임 출력 확인")
    void print_bowlingGame() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.bowl(a -> new Pin(10));
        bowlingGame.bowl(a -> new Pin(9));
        bowlingGame.bowl(a -> new Pin(1));
        bowlingGame.bowl(a -> new Pin(0));
        bowlingGame.bowl(a -> new Pin(5));
        bowlingGame.bowl(a -> new Pin(4));
        OutputView.print(bowlingGame, new PlayerName("PJS"), new Pin(4));
    }

    @Test
    @DisplayName("퍼펙트 게임 출력")
    void print_perfectGame() {
        BowlingGame bowlingGame = new BowlingGame();
        IntStream.range(0, 11)
                .forEach(i -> bowlingGame.bowl(range -> new Pin(10)));
        OutputView.print(bowlingGame, new PlayerName("PJS"), new Pin(10));
    }


}