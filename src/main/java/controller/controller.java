package controller;

import bowling.BowlingGame;
import bowling.Frame;
import bowling.Pin;
import bowling.Player;
import dto.*;
import view.InputView;
import view.ResultView;

import java.util.List;

public class controller {

    private static final int TOTAL_FRAMES = 5;
    private static final InputView inputView = new InputView();
    private static final ResultView resultView = new ResultView();

    public static void main(String[] args) {
        NameDTO nameDTO = inputView.inputName();
        BowlingGame bowlingGame = BowlingGame.of(TOTAL_FRAMES, nameDTO.getName());

        List<Frame> frames = bowlingGame.getFrames();
        ResultFramesDTO resultFramesDTO = ResultFramesDTO.of(frames);
        ScoresDTO scoresDTO = ScoresDTO.of(frames);

        printScoreBoard(nameDTO, resultFramesDTO, scoresDTO);

        while (!bowlingGame.isFinished()) {
            printScoreBoard(bowlingGame);
        }
    }

    private static void printScoreBoard(NameDTO nameDTO, ResultFramesDTO resultFramesDTO, ScoresDTO scoresDTO) {
        resultView.printColumns(TOTAL_FRAMES);
        resultView.printPlayerName(nameDTO);
        resultView.printPins(resultFramesDTO);
        resultView.printScores(scoresDTO);
    }

    private static void printScoreBoard(BowlingGame bowlingGame) {
        try {
            Player player = bowlingGame.getPlayer();
            NameDTO nameDTO = NameDTO.of(player.getName());

            PinDTO pinDTO = inputView.inputPin(nameDTO);
            bowlingGame.bowl(Pin.of(pinDTO.getPin()));

            List<Frame> frames = bowlingGame.getFrames();
            ResultFramesDTO resultFramesDTO = ResultFramesDTO.of(frames);
            ScoresDTO scoresDTO = ScoresDTO.of(frames);

            printScoreBoard(nameDTO, resultFramesDTO, scoresDTO);
        } catch (Exception exception) {
            resultView.printExceptionMessage(exception);
            printScoreBoard(bowlingGame);
        }
    }
}
