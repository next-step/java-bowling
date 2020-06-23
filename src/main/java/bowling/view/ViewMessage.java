package bowling.view;

public class ViewMessage {
    public static final String INSTRUCTION_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    public static final String SCORE_BOARD_HEADER =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String DEFAULT_SCORE_BOARD =
            "|  %S |      |      |      |      |      |      |      |      |      |      |\n";
    public static final String INSTRUCTION_PITCH = "%d프레임 투구 : ";

    private ViewMessage() {
    }
}
