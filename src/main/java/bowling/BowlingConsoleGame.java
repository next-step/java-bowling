package bowling;

import bowling.domain.player.BowlingPlayer;
import bowling.dto.BowlingPlayerDto;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingConsoleGame {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        BowlingPlayer bowlingPlayer = BowlingPlayer.from(name);

        ResultView.printScoreBoard(BowlingPlayerDto.from(bowlingPlayer));
        while(!bowlingPlayer.isBowlingEnd()) {
            int downedPins = InputView.inputNumberOfDownedPins(BowlingPlayerDto.from(bowlingPlayer));

            bowlingPlayer.play(downedPins);

            ResultView.printScoreBoard(BowlingPlayerDto.from(bowlingPlayer));
        }
    }
}
