package bowling;

import bowling.domain.Name;
import bowling.domain.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.view.InputView;

import static bowling.view.ResultView.printFrameResult;

public class BowlingApplication {
    public static void main(String[] args) {
        Name name = InputView.inputPlayerName();

        Frames frames = Frames.init();
        while(!frames.isOver()) {
            Pin pin = InputView.inputCountOfPin(frames.getFramesSize());
            Frame frame = frames.bowl(pin);
            printFrameResult(name, frames);
            frames = frames.addNextFrame(frame);
        }
    }
}
