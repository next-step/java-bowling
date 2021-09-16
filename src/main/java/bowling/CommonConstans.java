package bowling;

import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class CommonConstans {

    public static final Boolean IS_FIRST = TRUE;
    public static final Boolean IS_NOT_FIRST = FALSE;


    public static final int MAX_PINS = 10;
    public static final int MIN_PINS = 0;
    public static final int MAX_SCORE = 30;
    public static final int MIN_SCORE = 0;
    public static final int MAX_CHANCE = 2;
    public static final int MIN_CHANCE = 0;
    public static final int NORMAL_PINS_MAX_SIZE = 2;
    public static final int FINAL_PINS_MAX_SIZE = 3;
    public static final int ZERO = 0;
    public static final int MINUS_SIZE_ONE = 1;
    public static final int FRAME_MAX_SIZE = 10;
    public static final int LAST_FRAME_NUMBER = 10;
    public static final int DEFAULT_SIZE = 1;
    public static final int MINUS_ONE = 1;
    public static final int MAX_SIZE = 10;
    public static final int MINUS_INDEX_ONE = 1;


    public static final String MAX_OVER_PINS = "넘어뜨리는 볼링핀은 10개가 최대입니다.";
    public static final String MIN_UNDER_PINS = "넘어뜨리는 볼링핀은 0개 미만이 안됩니다.";
    public static final String MAX_OVER_SCORE = "프레임당 최대 점수는 30점 입니다.";
    public static final String MIN_UNDER_SCORE = "프레임당 최대 점수는 0점 입니다.";
    public static final String MAX_OVER_CHANCE = "최대 보너스 기회는 2회 입니다.";
    public static final String MIN_UNDER_CHANCE = "최소 보너스 기회는 0회 입니다";
    public static final String IS_NOT_END = "해당 프레임이 종료되지 않았습니다.";
    public static final String DEFAULT_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String FRAME_EMPTY = "      |";
    public static final String SCORE_FIRST = "|      |";
    public static final String PIPE = "|";
    public static final String MIN_SIZE_UNDER = "최소 참여자는 1명 입니다.";
    public static final Pattern PLAYER_NAME_CHECK = Pattern.compile("(^[a-zA-Z]{3})*$");


}
