package bowling;

import bowling.domain.set.FrameSet;
import bowling.domain.state.*;

import java.util.List;
import java.util.Scanner;

public class View {

    private static final String PLAYER_NAME_QUESTION = "플레이어 이름은(3 english letters)?";
    private static final String HIT_COUNT_QUESTION = "%d 프레임 투구";
    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DIVIDER = "|";

    private static final int SCORE_TEXT_LENGTH = 6;

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

    private void showScore(String playerName, List<FrameSet> resultSets) {
        showPlayerName(playerName);

        int lastIndex = resultSets.size() - 1;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= lastIndex; i++) {

            addFrameScoreText(sb, resultSets.get(i).getState());

            if (resultSets.get(i).getState().isEnd() || i == lastIndex) {
                showText(StringUtils.addBlank(sb, SCORE_TEXT_LENGTH));
                showText(DIVIDER);

                sb = new StringBuilder();
            }
        }

        nextLine();
    }

    private void showPlayerName(String playerName) {
        showText(DIVIDER);
        showText(StringUtils.addBlank(playerName, 6));
        showText(DIVIDER);
    }

    private void addFrameScoreText(StringBuilder sb, State state) {
        sb.append(state.getString());

        if (!state.isEnd()) {
            sb.append(DIVIDER);
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
