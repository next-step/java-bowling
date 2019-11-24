package bowling;

import bowling.domain.set.FrameSet;
import bowling.domain.state.*;

import java.util.List;
import java.util.Scanner;

public class View {

    private static final String PLAYER_NAME_QUESTION = "플레이어 이름은(3 english letters)?";
    private static final String HIT_COUNT_QUESTION = "%d 프레임 투구";
    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_BOARD_FIRST_DIVIDER = "|  %s |  ";
    private static final String SCORE_BOARD_FRAME_DIVIDER = " |  ";
    private static final String SCORE_BOARD_STATE_DIVIDER = "|";
    private static final String SCORE_BOARD_SET_DIVIDER = " |";

    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        showTextLine(PLAYER_NAME_QUESTION);
        return getLine();
    }

    public int getHitCount(int playCount) {
        showTextLine(String.format(HIT_COUNT_QUESTION, playCount));
        return getNumber();
    }

    public void showFrameSetResult(String playerName, List<FrameSet> results) {
        showBoardHeader();
        showScore(playerName, results);
    }

    private void showScore(String playerName, List<FrameSet> results) {
        showText(String.format(SCORE_BOARD_FIRST_DIVIDER, playerName));

        for (FrameSet set : results) {
            showFrameScore(set.getState());
            showDividerIfPossible(set);
        }

        nextLine();
    }

    private void showFrameScore(State state) {
        StringBuilder sb = new StringBuilder();

        sb.append(state.getString());

        if (!state.isEnd()) {
            sb.append(SCORE_BOARD_STATE_DIVIDER);
        }

        showText(sb.toString());
    }

    private void showDividerIfPossible(FrameSet set) {
        if (set.isEnd()) {
            showText(SCORE_BOARD_SET_DIVIDER);
        } else if (set.getState().isEnd()) {
            showText(SCORE_BOARD_FRAME_DIVIDER);
        }
    }

    private void showBoardHeader() {
        showTextLine(SCORE_BOARD_HEADER);
    }

    private void nextLine() {
        showText(System.lineSeparator());
    }

    private void showText(String text) {
        System.out.print(text);
    }

    private void showTextLine(String text) {
        System.out.println(text);
    }

    private int getNumber() {
        return Integer.parseInt(getLine());
    }

    private String getLine() {
        return scanner.nextLine();
    }
}
