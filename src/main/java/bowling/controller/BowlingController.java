package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.ui.Input;
import bowling.ui.Output;

import java.util.Arrays;
import java.util.List;

public class BowlingController {

    private static final String MSG_PLAYER_TURN = "%%'s turn : ";

    private static final String TAB_NAME = "NAME";
    private static final List<String> ROUND =
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");

    private static final String BLANK = "";

    public static final String REPLACE_TARGET = "%%";

    private final Input input;
    private final Output output;
    private final BowlingGames games;

    public BowlingController(Input input, Output output, List<String> names) {
        this.input = input;
        this.output = output;
        this.games = new BowlingGames(names);
    }

    public static BowlingControllerBuilder builder() {
        return new BowlingControllerBuilder();
    }

    private void printBoard() {
        output.printRow(TAB_NAME, ROUND);

        for (BowlingGame game : games.getGames()) {
            output.printRow(game.getPlayerName(), game.getSwingRecords());
            output.printRow(BLANK, game.getScores());
        }
    }

    public void playGame() {
        while(games.isNotEnd()) {
            output.print(currentRoundInputMessage(games.getCurrentGame()));
            games.swing(input.nextInt());
            printBoard();
        }
    }

    private String currentRoundInputMessage(BowlingGame game) {
        return MSG_PLAYER_TURN.replace(REPLACE_TARGET,
                String.valueOf(game.getPlayerName()));
    }
}
