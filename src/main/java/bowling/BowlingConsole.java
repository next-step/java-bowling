package bowling;

import bowling.pin.Pin;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingConsole {
    public static void main(String[] args) {
        final BowlingFacade facade = BowlingFacade.of(InputView.playerName());
        ResultView.scoreBoard(facade.getResult());

        while (!facade.isEnd()) {
            ResultView.newLine();
            facade.pitch(Pin.from(InputView.pitch(facade.getResult())));
            ResultView.scoreBoard(facade.getResult());
        }
    }
}
