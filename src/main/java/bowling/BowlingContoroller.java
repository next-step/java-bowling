package bowling;

import bowling.domain.player.Bowler;
import bowling.dto.BowlingPlayResultData;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingContoroller {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Bowler bowler = Bowler.of(name);

        ResultView.printBowlingScore(BowlingPlayResultData.of(bowler));
    }

}
