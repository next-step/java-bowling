package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.PlayerName;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class OutputViewTest {

    @Test
    @DisplayName("게임 출력 확인")
    void print_bowlingGame() {
        BowlingGame bowlingGame = new BowlingGame(new PlayerName("PJS"));
        bowlingGame.bowl(a -> new Score(10));
        bowlingGame.bowl(a -> new Score(9));
        bowlingGame.bowl(a -> new Score(1));
        bowlingGame.bowl(a -> new Score(0));
        bowlingGame.bowl(a -> new Score(5));
        bowlingGame.bowl(a -> new Score(4));
        bowlingGame.bowl(a -> new Score(3));
        OutputView.print(bowlingGame);
    }

    @Test
    @DisplayName("퍼펙트 게임 출력")
    void print_perfectGame() {
        BowlingGame bowlingGame = new BowlingGame(new PlayerName("PJS"));
        IntStream.range(0, 11)
                .forEach(i -> bowlingGame.bowl(range -> new Score(10)));
        OutputView.print(bowlingGame);
    }


}