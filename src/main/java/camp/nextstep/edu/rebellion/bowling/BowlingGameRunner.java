package camp.nextstep.edu.rebellion.bowling;

import camp.nextstep.edu.rebellion.bowling.domain.BowlingGame;
import camp.nextstep.edu.rebellion.bowling.domain.Player;
import camp.nextstep.edu.rebellion.bowling.view.InputView;
import camp.nextstep.edu.rebellion.bowling.view.ResultView;

public class BowlingGameRunner {
    public static void main(String[] args) {
        Player player = new Player(InputView.getPlayer());
        BowlingGame bowlingGame = BowlingGame.start(player);

        while (bowlingGame.hasNext()) {
            ResultView.print(bowlingGame.getScoreBoard());
            bowlingGame.record(InputView.getHitScore(bowlingGame.currentRound()));
        }
        ResultView.print(bowlingGame.getScoreBoard());
    }
}
