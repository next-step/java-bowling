package bowling;

import bowling.domain.GameResult;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

    private static final int FRAME_COUNT = 9;

    public static void main(String[] args) {

        String user = InputView.createUser();

        GameResult gameResult = new GameResult(user);

        while (!gameResult.isEnd()) {
            int frameNumber = gameResult.getCurrentFrameIndex();
            gameResult.createFrame(InputView.createScore(frameNumber));
            ResultView.printScore(gameResult);
        }
    }

}
