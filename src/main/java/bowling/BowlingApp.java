package bowling;

import bowling.domain.NormalFrame;
import bowling.domain.PinScore;
import bowling.domain.PlayerName;
import bowling.strategy.FallenPinCalculateStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Random;

public class BowlingApp {

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final Random random = new Random();

    public void start(){
        PlayerName playerName = inputView.getPlayName();
        resultView.printDefaultPanels(playerName);
        NormalFrame normalFrame = new NormalFrame(new PinScore(getPinCalculateStrategy()));
        resultView.printGameScores(playerName, normalFrame);
        normalFrame.nextTry();
        resultView.printGameScores(playerName, normalFrame);
    }

    public FallenPinCalculateStrategy getPinCalculateStrategy(){
        return random::nextInt;
    }
}
