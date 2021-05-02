package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.score.FrameScores;
import bowling.ui.Placeholder;
import bowling.ui.ScoreBoard;

public class Controller {
    public static void main(String[] args) {
        int frameNumber = 1;

        Placeholder placeholder = new Placeholder();
        placeholder.printNamingMessage();
        ScoreBoard scoreBoard = new ScoreBoard(placeholder.inputFromUser());

        BowlingGame bowlingGame = new BowlingGame(frameNumber);
        FrameScores frameScores = new FrameScores();

        while(bowlingGame.isAvailable()) {
            placeholder.printInputScoreMessage(frameNumber);

            int score = bowlingGame.bowling(placeholder.inputFromUser());
            frameScores.addScore(frameNumber, score);
            scoreBoard.addData(frameNumber, frameScores.getFrameScore(frameNumber));
            scoreBoard.printScoreBoard();

            frameNumber = bowlingGame.nextFrameIfAvailable();
        }
    }
}
