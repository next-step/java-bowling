package bowling.view;

import bowling.dto.BowlingGameDTO;
import bowling.dto.UserDTO;

import java.util.List;

public class ResultView {

    private static final int NUM_SCORE_CELL = 10;

    private ResultView() {
    }

    public static void printRoundDescription() {
        System.out.print("|  NAME  |");

        for (int i = 0; i < NUM_SCORE_CELL - 1; i++) {
            System.out.print("   " + (i + 1) + "   |");
        }

        System.out.println("     10    |");

    }

    public static void printContent(UserDTO userDTO, BowlingGameDTO bowlingGameDTO) {
        System.out.print("|   " + userDTO.getName() + "  |");

        List<String> contents = bowlingGameDTO.getContents();

        for (String content : contents) {
            System.out.print(content + "|");
        }

        System.out.println();
    }
}
