package bowling.view;

public class ViewMessages {
    public static final String INSTRUCTION_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
    public static final String INSTRUCTION_PITCH = "%d프레임 투구 : ";
    public static final String SCORE_BOARD_HEADER =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String SCORE_BOARD_DEFAULT =
            "|  %s |      |      |      |      |      |      |      |      |      |      |\n\n";
    public static final String SCORE_BOARD_NAME = "|  %s |";
    public static final String VERTICAL_LINE = "|";
    public static final String STRIKE = "X";
    public static final String GUTTER = "-";
    public static final String SPARE = "/";

    private ViewMessages() {
    }
}
