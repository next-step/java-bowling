package controller;

import bowling.BowlingGame;
import bowling.Pin;
import bowling.Player;
import dto.PinDTO;
import dto.NameDTO;
import dto.ResultFramesDTO;
import view.InputView;
import view.ResultView;

public class controller {

    private static final int TOTAL_FRAMES = 3;
    private static final InputView inputView = new InputView();
    private static final ResultView resultView = new ResultView();

    public static void main(String[] args) {
        NameDTO nameDTO = inputView.inputName();
        BowlingGame bowlingGame = BowlingGame.of(TOTAL_FRAMES, nameDTO.getName());

        ResultFramesDTO resultFramesDTO = ResultFramesDTO.of(bowlingGame.getFrames());
        resultView.printColumns(TOTAL_FRAMES);
        resultView.printPlayerName(nameDTO);
        resultView.printPins(resultFramesDTO);

        while (!bowlingGame.isFinished()) {
            printScoreBoard(bowlingGame);
        }
    }

    private static void printScoreBoard(BowlingGame bowlingGame) {
        try {
            Player player = bowlingGame.getPlayer();
            NameDTO nameDTO = NameDTO.of(player.getName());

            PinDTO pinDTO = inputView.inputPin(nameDTO);
            bowlingGame.bowl(Pin.of(pinDTO.getPin()));
            ResultFramesDTO resultFramesDTO = ResultFramesDTO.of(bowlingGame.getFrames());

            resultView.printColumns(TOTAL_FRAMES);
            resultView.printPlayerName(nameDTO);
            resultView.printPins(resultFramesDTO);
        } catch (Exception exception) {
            resultView.printExceptionMessage(exception);
            printScoreBoard(bowlingGame);
        }
    }
}
