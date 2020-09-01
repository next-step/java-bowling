package camp.nextstep.edu.rebellion.bowling;

import camp.nextstep.edu.rebellion.bowling.domain.BowlingGame;
import camp.nextstep.edu.rebellion.bowling.view.InputView;

public class BowlingGameRunner {
    public static void main(String[] args) {
        BowlingGame bowlingGame = BowlingGame.start();
        while (bowlingGame.hasNext()) {
            bowlingGame.record(InputView.getHitScore(bowlingGame.currentRound()));
            bowlingGame.getScores()
                    .forEach(n -> System.out.print(n + "\t"));
            System.out.println();
        }
    }
}
