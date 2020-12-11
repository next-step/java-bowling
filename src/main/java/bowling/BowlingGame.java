package bowling;

import bowling.domain.Frames;
import bowling.domain.PlayerName;
import bowling.helper.ValidInputHelper;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    private final InputView inputView;
    private final ResultView resultView;

    public BowlingGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        PlayerName playerName = ValidInputHelper.get(this::getPlayerName, inputView::printError);
        Frames frames = Frames.init(playerName);

        while (!frames.isEnd()) {
            setKnockDownPins(frames);
            resultView.print(frames);
        }
    }

    private PlayerName getPlayerName() {
        String inputPlayerName = ValidInputHelper.get(inputView::getPlayerName, inputView::printError);
        return PlayerName.valueOf(inputPlayerName);
    }

    private void setKnockDownPins(Frames frames) {
        try {
            int knockDownPins = inputView.getKnockDownPins(frames.getCurrentFrameIndex());
            frames.setKnockDownPins(knockDownPins);
        } catch (RuntimeException e) {
            inputView.printError(e);
            setKnockDownPins(frames);
        }
    }
}
