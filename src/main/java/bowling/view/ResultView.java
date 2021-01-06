package bowling.view;

import bowling.dto.BowlingGameResultsDTO;
import bowling.dto.UserDTO;

public class ResultView {

    private static final String PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?:";
    private static final String CURRENT_FRAME_MESSAGE = "프레임 투구 : ";

    private ResultView() {
    }

    public static void printAskUserNameMessage() {
        System.out.print(PLAYER_NAME_MESSAGE);
    }

    public static void printCurrentFrameNumber(BowlingGameResultsDTO bowlingStatusDTO) {
        System.out.print(bowlingStatusDTO.getCurrentFrame() + CURRENT_FRAME_MESSAGE);
    }

    public static void printScoreBoard(UserDTO userDTO, BowlingGameResultsDTO bowlingStatusDTO) {
        System.out.print("|  NAME  |");
        for (int i = 1; i <= 9; i++) {
            System.out.print("   " + i + "   |");
        }
        System.out.println("    " + 10 + "     |");

        System.out.print("|   " + userDTO.getName() + "  |");
        for (String status : bowlingStatusDTO.getFrames()) {
            System.out.print(status + "|");
        }
        System.out.println();
    }
}
