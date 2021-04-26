package bowling.view;

import bowling.controller.BowlingGameController;
import bowling.controller.dto.BowlingGameResponse;

public class BowlingGameApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BowlingGameController controller = new BowlingGameController();

        BowlingGameResponse response = controller.startGame(inputView.inputParticipant());
        outputView.printFrame(response);

        while (!response.isFinished()) {
            response = controller.pitchBall(inputView.inputPitch(response.getParticipantName(), response.getNextFrameNumber()));
            outputView.printFrame(response);
        }
    }
}
