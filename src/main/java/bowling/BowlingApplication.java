package bowling;

import bowling.domain.BowlingPins;
import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import bowling.domain.ResultFrame;
import bowling.view.InputView;
import bowling.view.OutputView;
import bowling.vo.GameUser;

public class BowlingApplication {

    public static void main(String[] args) {
        GameUser gameUser = InputView.askUserName();
        ResultFrame resultFrame = new ResultFrame(gameUser);
        OutputView.printSnapshot(resultFrame);
        Frame frame = new NormalFrame(1);
        while (!frame.isFinalFrame()) {
            BowlingPins bowlingPins = InputView.askPins(frame.getFrameNumber());
            resultFrame.record(frame.getFrameNumber(), frame);
            frame = frame.execute(bowlingPins);
            OutputView.printSnapshot(resultFrame);
        }
    }
}
