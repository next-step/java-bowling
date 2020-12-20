package bowling.view;

import bowling.dto.BowlingGameDTO;
import bowling.dto.UserDTO;

public class ResultView {

    private static final int NUM_SCORE_CELL = 10;

    private static final String PARTITION = "|";
    private static final String MEDIUM_MARGIN = "   ";
    private static final String NAME_LABEL = "  NAME  ";

    private ResultView() {
    }

    public static void printCurrentRound(BowlingGameDTO bowlingGameDTO) {
        System.out.print((bowlingGameDTO.getCurrentWorkingFrame()) + "프레임 입니다. : ");
    }

    public static void printRoundDescription() {
        System.out.print(PARTITION + NAME_LABEL + PARTITION);

        for (int i = 0; i < NUM_SCORE_CELL - 1; i++) {
            System.out.print(MEDIUM_MARGIN + (i + 1) + MEDIUM_MARGIN + PARTITION);
        }

        System.out.println("     " + "10" + "    " + PARTITION);
    }

    public static void printGameStatus(UserDTO userDTO, BowlingGameDTO bowlingGameDTO) {
        System.out.print(PARTITION + "   " + userDTO.getName() + "  |");
        for (String normalFrameOutputForm : bowlingGameDTO.getNormalFrames()) {
            System.out.print(normalFrameOutputForm + "|");
        }

        System.out.print(bowlingGameDTO.getLastFrame());

        System.out.println();
    }
}
