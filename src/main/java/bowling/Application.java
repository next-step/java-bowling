package bowling;

import bowling.domain.Bowlings;
import bowling.domain.PinCount;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {

        OutputView outputView = new OutputView();
        Bowlings bowlings = new Bowlings(InputView.readName(InputView.readPlayerCount()));

        while (bowlings.isGameNotEnd()) {
            bowlings.forEach(bowling -> {
                PinCount pinCount = InputView.readPinCount(bowling.getName());
                bowling.bowl(pinCount.getValue());
                outputView.print(bowlings);
            });
        }

    }
}
