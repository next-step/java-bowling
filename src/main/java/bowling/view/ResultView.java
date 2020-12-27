package bowling.view;

import bowling.dto.BowlingGameInformationDTO;
import bowling.dto.UserDTO;

import java.util.List;

public class ResultView {

    private static final int NUM_SCORE_CELL = 10;
    private static final String FRAME_INTRODUCE_MESSAGE = "프레임 투구 :";

    private ResultView() {
    }

    public static void printCurrentStage(BowlingGameInformationDTO bowlingGameInformationDTO) {
        System.out.print(bowlingGameInformationDTO.getCurrentFrame());
        System.out.print(FRAME_INTRODUCE_MESSAGE);
    }

    public static void printRoundDescription() {
        System.out.print("|  NAME  |");

        for (int i = 0; i < NUM_SCORE_CELL - 1; i++) {
            System.out.print("   " + (i + 1) + "   |");
        }

        System.out.println("     10    |");

    }

    public static void printContent(UserDTO userDTO, BowlingGameInformationDTO bowlingGameInformationDTO) {
        System.out.print("|   " + userDTO.getName() + "  |");

        List<String> contents = bowlingGameInformationDTO.getFrameContents();

        for (String content : contents) {
            System.out.print(content + "|");
        }

        System.out.println();
    }
}
