package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.ui.Input;
import bowling.ui.Output;

import java.util.Arrays;
import java.util.List;

public class BowlingController {

    private static final String MSG_INPUT_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String MSG_INPUT_SWING = "%% 프레임 투구 : ";

    private static final String TAB_NAME = "NAME";
    private static final List<String> ROUND =
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");

    private static final String REPLACE_TARGET = MSG_INPUT_SWING.substring(0, 2);

    private final Input input;
    private final Output output;
    private BowlingGame bowlingGame;

    public BowlingController(Input input, Output output) {
        this.input = input;
        this.output = output;
        output.print(MSG_INPUT_NAME);
    }

    public void inputName(String name) {
        bowlingGame = new BowlingGame(name);
        printBoard();
    }

    private void printBoard() {
        output.printRow(TAB_NAME, ROUND);
        output.printRow(bowlingGame.getName(), bowlingGame.getSwingInfo());
    }

    public void gamePlay() {
        while(bowlingGame.isBowlingGameRun()) {
            output.print(currentRoundInputMessage());
            bowlingGame.swing(input.nextInt());
            printBoard();
        }
    }

    private String currentRoundInputMessage() {
        return MSG_INPUT_SWING.replace(REPLACE_TARGET,
                String.valueOf(bowlingGame.currentRound()));
    }
}
