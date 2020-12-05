package bowling;

import bowling.domain.*;
import bowling.domain.score.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {

    public static void main(String[] args) {
        String name = InputView.scanPlayer();
        Player player = Player.from(name);

        Bowling bowling = Bowling.of(player);
        OutputView.printFrame(bowling);

        while(bowling.isNotEnd()) {
            int frameNumber = bowling.getFrameNumber();
            Score score = Score.of(InputView.scanBowl(frameNumber));

            bowling.bowl(score);

            OutputView.printFrame(bowling);
        }

    }

}
