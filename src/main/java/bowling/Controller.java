package bowling;

import bowling.domain.BowlingGame;
import bowling.ui.Placeholder;
import bowling.ui.ScoreBoard;
import bowling.ui.util.ScoreSymbol;

public class Controller {
    public void run() {
        int frameNumber = 1;

        Placeholder placeholder = new Placeholder();
        placeholder.printNamingMessage();
        ScoreBoard scoreBoard = new ScoreBoard(placeholder.inputFromUser());

        BowlingGame bowlingGame = new BowlingGame();

        while(bowlingGame.isAvailable()) {
            placeholder.printInputScoreMessage(frameNumber);

            int score = bowlingGame.bowl(placeholder.inputIntegerFromUser());
            scoreBoard.addData(frameNumber, ScoreSymbol.convert(score));
            scoreBoard.printScoreBoard();

            frameNumber = bowlingGame.nextFrameIfAvailable();
        }
    }
}
