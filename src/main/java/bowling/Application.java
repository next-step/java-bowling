package bowling;

import bowling.domain.Frames;
import bowling.domain.Name;
import bowling.domain.PinCount;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Name name = InputView.readName();

        Frames frames = Frames.init();
        OutputView outputView = OutputView.init();

        while (frames.canBowling()) {
            PinCount pinCount = InputView.readPinCount(frames.getCurrentFrameIndex());

            frames.bowling(pinCount.getValue());
            outputView.print(name, frames);
        }

    }
}
