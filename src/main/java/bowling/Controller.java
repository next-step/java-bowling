package bowling;

import bowling.domain.BowlingGame;
import bowling.dto.ResultDto;
import bowling.ui.Placeholder;
import bowling.ui.ScoreBoard;

public class Controller {
    public static void main(String[] args) {
        Placeholder placeholder = new Placeholder();
        placeholder.printNamingMessage();

        ScoreBoard scoreBoard = new ScoreBoard(placeholder.inputFromUser());
        BowlingGame bowlingGame = new BowlingGame();

        int frameNumber = 1;

        while(bowlingGame.isAvailable()) {
            placeholder.printInputScoreMessage(frameNumber);

            ResultDto resultDto = bowlingGame.bowling(placeholder.inputFromUser());
            scoreBoard.addData(resultDto.getFrameNumber(), resultDto.getScore());
            frameNumber = resultDto.getFrameNumber();
            scoreBoard.printScoreBoard();

            bowlingGame.nextFrameIfAvailable();
        }
    }
}
