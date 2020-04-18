package bowling.view;

import bowling.ViewUtils;
import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import bowling.domain.state.State;

import java.util.List;

public class OutputView {
    private static final String BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String VERTICAL_LINE = "|";
    private static final String SPACE = " ";
    private static final int LENGTH_PER_FRAME = 6;

    public void showFrameResult(Player player) {
        showBoardHeader();
        showState(player.getName(), player.getFrames().getValue());
        showScore(player.getCurrentResult().getScores());
        newLine();
    }

    private void showBoardHeader() {
        ViewUtils.printLine(BOARD_HEADER);
    }

    private void showState(String name, List<Frame> frames) {
        showPlayerName(name);

        for (Frame frame : frames) {
            showStateHistory(frame);
        }

        newLine();
    }

    private void showPlayerName(String playerName) {
        showVerticalLine();

        int spaceCount = LENGTH_PER_FRAME - playerName.length();
        ViewUtils.print(appendSpaces(playerName, spaceCount));

        showVerticalLine();
    }

    private void showStateHistory(Frame frame) {
        StringBuilder builder = new StringBuilder();

        for (State state : frame.getStateHistory().getValue()) {
            appendStateContent(builder, state);
        }

        ViewUtils.print(appendSpaces(builder.toString(), LENGTH_PER_FRAME));
        ViewUtils.print(VERTICAL_LINE);
    }

    private void appendStateContent(StringBuilder builder, State state) {
        builder.append(state.getString());

        if (!state.isEndedState()) {
            builder.append(VERTICAL_LINE);
        }
    }

    private String appendSpaces(String content, int spaceCount) {
        StringBuilder builder = new StringBuilder();

        builder.append(content);
        for (int i = 0; i < spaceCount; i++) {
            builder.append(SPACE);
        }

        if (builder.length() > LENGTH_PER_FRAME) {
            return builder.substring(0, LENGTH_PER_FRAME);
        }

        return builder.toString();
    }

    public void showScore(List<Integer> scores) {
        showEmptyFrame();

        for(Integer score : scores) {
            ViewUtils.print(appendSpaces(String.valueOf(score), LENGTH_PER_FRAME));
            ViewUtils.print(VERTICAL_LINE);
        }
    }

    private void showEmptyFrame() {
        ViewUtils.print(VERTICAL_LINE);
        ViewUtils.print(appendSpaces("", 6));
        ViewUtils.print(VERTICAL_LINE);
    }

    private void showVerticalLine() {
        ViewUtils.print(VERTICAL_LINE);
    }

    private void newLine() {
        ViewUtils.printLine("");
    }
}
