package bowling.view;

import bowling.controller.dto.BowlingGameRequest;
import bowling.controller.dto.BowlingGameResponse;

import java.util.Arrays;

public class BowlingGameApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BowlingGameRequest bowlingGameRequest = inputView.inputParticipant();

        outputView.printFrame(new BowlingGameResponse("LDS", 1, false, Arrays.asList("1|/", "5|4")));
    }
}
