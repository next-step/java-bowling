package bowling;

import bowling.domain.Frames;
import bowling.domain.NormalFrame;
import bowling.domain.GeneralScore;
import bowling.domain.PlayerName;
import bowling.strategy.FallenPinCalculateStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Random;

public class BowlingApp {

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private static final Random random = new Random();

    public void start(){
        PlayerName playerName = new PlayerName(inputView.getPlayName());
        resultView.printDefaultPanels(playerName);
        NormalFrame normalFrame = new NormalFrame(new GeneralScore(getPinCalculateStrategy()));
        Frames frames = new Frames(normalFrame);
        while (frames.isProgress()) {
            resultView.printGameScores(playerName, frames);
            frames.next();
        }
    }

    public static FallenPinCalculateStrategy getPinCalculateStrategy(){
        return (maxNum) -> random.nextInt(maxNum+1);
    }
}
