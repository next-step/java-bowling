package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.GameRecord;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {


    public static void main(String[] args) {

        String user = InputView.createUser();

        GameRecord gameRecord = new GameRecord(user);
        BowlingGame bowlingGame = new BowlingGame(gameRecord);

        while (!gameRecord.isEnd()) {
            int frameNumber = gameRecord.getCurrentFrameIndex();
            bowlingGame.bowl(InputView.createScore(frameNumber));
            ResultView.printScoreBoard(gameRecord);
        }
    }

}
