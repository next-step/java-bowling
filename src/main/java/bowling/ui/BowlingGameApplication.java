package bowling.ui;

import bowling.domain.Player;

public class BowlingGameApplication {
    public static void main(String[] args) {
        Player initPlayer = initGame();

        // 1 프레임 처리
        FirstFrameController firstFrameController = new FirstFrameController(initPlayer);
        Player firstFrameCompletedPlayer = firstFrameController.doFirstFrame();

        // 프레임 처리
        MiddleFrameController middleFrameController = new MiddleFrameController(firstFrameCompletedPlayer);
        middleFrameController.doMiddleFrame();
    }

    private static Player initGame() {
        String userName = InputView.getPlayerName();

        OutputView.printEmptyResult(userName);

        return Player.createByName(userName);
    }
}
