package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.PlayerName;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputViewTest {

    @Test
    @DisplayName("게임 출력 확인")
    void print_bowlingGame(){
        BowlingGame bowingGame = new BowlingGame(new PlayerName("PJS"));
        bowingGame.bowl(a-> new Score(10));
        bowingGame.bowl(a-> new Score(9));
        bowingGame.bowl(a-> new Score(1));
        bowingGame.bowl(a-> new Score(0));
        bowingGame.bowl(a-> new Score(5));
        bowingGame.bowl(a-> new Score(4));
        bowingGame.bowl(a-> new Score(3));
        OutputView.print(bowingGame);
    }


}