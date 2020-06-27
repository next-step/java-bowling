package bowling.view;

public class ViewMessage {
    public static final String INSTRUCTION_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
    public static final String INSTRUCTION_PITCH = "%d프레임 투구 : ";
    public static final String SCORE_BOARD_HEADER =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String DEFAULT_FRAME_SIGNATURE_ROW =
            "|  %s |      |      |      |      |      |      |      |      |      |      |\n";
    public static final String DEFAULT_FRAME_SCORE_ROW =
            "|      |      |      |      |      |      |      |      |      |      |      |\n";
    public static final String PLAYER_NAME = "|  %s |";
    public static final String VERTICAL_LINE = "|";
    public static final String BLANK_FRAME = "      |";
    public static final String FRAME_SIGNATURE_ROW_FORMAT = "  %-4s|";
    public static final String FRAME_SCORE_ROW_FORMAT = "  %-4d|";
    public static final String FRAME_SCORE_ROW_PREFIX = "|      |";

    private ViewMessage() {
    }
}
