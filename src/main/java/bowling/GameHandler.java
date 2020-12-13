package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.KnockDownPins;
import bowling.domain.PlayerName;
import bowling.helper.ValidInputHelper;
import bowling.view.InputView;
import bowling.view.ResultView;

public class GameHandler {
    private final InputView inputView;
    private final ResultView resultView;

    public GameHandler(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        PlayerName playerName = ValidInputHelper.get(this::getPlayerName, inputView::printError);
        BowlingGame bowlingGame = BowlingGame.init(playerName);

        while (!bowlingGame.isEnd()) {
            setKnockDownPins(bowlingGame);
            resultView.print(bowlingGame);
        }
    }

    private PlayerName getPlayerName() {
        String inputPlayerName = ValidInputHelper.get(inputView::getPlayerName, inputView::printError);
        return PlayerName.valueOf(inputPlayerName);
    }

    private void setKnockDownPins(BowlingGame bowlingGame) {
        try {
            KnockDownPins knockDownPins = ValidInputHelper.get(() -> getKnockDownPins(bowlingGame), inputView::printError);
            bowlingGame.setKnockDownPins(knockDownPins);
        } catch (RuntimeException e) {
            inputView.printError(e);
            setKnockDownPins(bowlingGame);
        }
    }

    private KnockDownPins getKnockDownPins(BowlingGame bowlingGame) {
        Integer knockDownPins = ValidInputHelper.get(() -> inputView.getKnockDownPins(bowlingGame.getCurrentFrameIndex()), inputView::printError);
        return KnockDownPins.valueOf(knockDownPins);
    }
}
