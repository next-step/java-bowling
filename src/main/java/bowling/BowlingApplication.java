package bowling;

import bowling.application.BowlingService;
import bowling.dto.BowlingRequest;
import bowling.dto.BowlingResponse;
import bowling.ui.BowlingController;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    private static final int START_FRAME = 1;

    public static void main(String[] args) {
        BowlingService bowlingService = new BowlingService();
        BowlingController bowlingController = new BowlingController(bowlingService);

        String playerName = InputView.inputUser();
        ResultView.board();
        int fallenPin = InputView.inputPins(START_FRAME);
        BowlingResponse response = bowlingController.play(new BowlingRequest(fallenPin, playerName));
        ResultView.board(response);

        while (!response.isFinish()) {
            int frameNumber = response.nextFrame();
            BowlingRequest request = new BowlingRequest(InputView.inputPins(frameNumber), playerName);
            response = bowlingController.play(request);
            ResultView.board(response);
        }
    }
}
