package bowling;

import bowling.domain.Bowling;
import bowling.domain.Name;
import bowling.domain.PinCount;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Name name = InputView.readName();

        Bowling bowling = new Bowling();
        OutputView outputView = new OutputView();

        while (!bowling.isGameEnd()) {
            PinCount pinCount = InputView.readPinCount(bowling.getCurrentFrameNo());

            bowling.bowl(pinCount.getValue());
            outputView.print(name, bowling.createResults());
        }

    }
}
