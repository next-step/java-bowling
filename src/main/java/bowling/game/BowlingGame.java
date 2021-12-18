package bowling.game;

import bowling.model.Name;
import bowling.model.frame.Frame;
import bowling.model.frame.NormalFrame;
import bowling.model.gameresult.GameResult;
import bowling.view.InputView;

public class BowlingGame {

    private static final int FIRST_FRAME_NO = 1;
    private final Name name;
    private Frame frame;

    public BowlingGame(Name name) {
        this.name = name;
        this.frame = new NormalFrame(FIRST_FRAME_NO);
    }

    public GameResult play() {
        int knockedDownPin = InputView.getIntValue(String.format("%d 프레임 투구 :", this.frame.getFrameNo()));
        Frame nextFrame = frame.bowl(knockedDownPin);
        GameResult gameResult = getGameResult();
        this.frame = nextFrame;
        return gameResult;
    }

    private GameResult getGameResult() {
        return new GameResult(this.frame.getFrameNo(), this.frame.getStateDesc());
    }

    public boolean canPlay() {
        return this.frame.isNormalFrame() || !this.frame.isFinish();
    }

    public boolean bonusGame() {
        return this.frame.isStrike() || this.frame.isSpare();
    }
}
