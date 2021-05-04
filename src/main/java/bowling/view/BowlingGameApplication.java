package bowling.view;

import bowling.controller.BowlingGameController;
import bowling.controller.dto.BowlingGameResponse;

public class BowlingGameApplication {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final BowlingGameController controller = new BowlingGameController();

    public static void main(String[] args) {

        BowlingGameResponse response = null;
        int participantCount = inputView.inputParticipantCount();
        for (int i = 0; i < participantCount; i++) {
             response = controller.startGame(inputView.inputParticipant());
        }

        if (response!=null) {
            startGame(response);
        }
    }

    private static void startGame(BowlingGameResponse response) {
        outputView.printFrame(response);
        while (!response.isGameEnd()) {
            response = controller.pitchBall(inputView.inputPitch(response.getNextTurnParticipant()));
            outputView.printFrame(response);
        }
    }
}
