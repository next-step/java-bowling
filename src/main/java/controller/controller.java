package controller;

import bowling.*;
import dto.*;
import view.InputView;
import view.ResultView;

import java.util.List;

public class controller {

    private static final int TOTAL_FRAMES = 3;
    private static final InputView inputView = new InputView();
    private static final ResultView resultView = new ResultView();

    public static void main(String[] args) {
        int personnel = inputView.inputPersonnel();
        NamesDTO namesDTO = inputView.inputNames(personnel);
        BowlingGames bowlingGames = BowlingGames.of(TOTAL_FRAMES, namesDTO.getNames());

        while (!bowlingGames.isAllFinished()) {
            NameDTO nameDTO = NameDTO.of(bowlingGames.getCurrentPlayerName());
            PinDTO pinDTO = inputView.inputPin(nameDTO);
            bowlingGames.bowlCurrentEntry(Pin.of(pinDTO.getPin()));

            resultView.printColumns(TOTAL_FRAMES);
            bowlingGames.roundBowlingGames(bowlingGame -> printScoreBoard(bowlingGame));
        }
    }

    private static void printScoreBoard(NameDTO nameDTO, ResultFramesDTO resultFramesDTO, ScoresDTO scoresDTO) {
        resultView.printPlayerName(nameDTO);
        resultView.printPins(resultFramesDTO);
        resultView.printScores(scoresDTO);
    }

    private static void printScoreBoard(BowlingGame bowlingGame) {
        try {
            NameDTO nameDTO = NameDTO.of(bowlingGame.getPlayerName());

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
