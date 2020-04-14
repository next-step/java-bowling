package bowling.view;

import bowling.ViewUtils;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.state.State;

import java.util.List;

public class OutputView {
    private static final String BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String VERTICAL_LINE = "|";
    private static final String SPACE = " ";
    private static final int SPACE_PER_FRAME = 6;

    public void showFrameResult(Player player) {
        showBoardHeader();
        showState(player.getName(), player.getFrames());
    }

    private void showBoardHeader() {
        ViewUtils.printLine(BOARD_HEADER);
    }

    private void showState(String name, List<Frame> frames) {
        showPlayerName(name);

        for(Frame frame : frames) {
            showStateHistory(frame);
        }
    }
    private void showPlayerName(String playerName) {
        showVerticalLine();

        int spaceCount = SPACE_PER_FRAME - playerName.length();
        ViewUtils.printLine(appendSpaces(playerName, spaceCount));

        showVerticalLine();
    }

    private void showStateHistory(Frame frame) {
        StringBuilder builder = new StringBuilder();

        for(State state : frame.getStateHistory()) {
            appendStateContent(builder, state);
        }

        ViewUtils.printLine(appendSpaces(builder.toString(), SPACE_PER_FRAME));
        ViewUtils.printLine(VERTICAL_LINE);
    }

    private void appendStateContent(StringBuilder builder, State state) {
        builder.append(state.getString());

        if(!state.isEndedState()) {
            builder.append(VERTICAL_LINE);
        }
    }

    private String appendSpaces(String content, int spaceCount) {
        StringBuilder builder = new StringBuilder();

        builder.append(content);
        for(int i = 0; i < spaceCount; i++) {
            builder.append(SPACE);
        }
        return builder.toString();
    }

    private void showVerticalLine() {
        ViewUtils.printLine(VERTICAL_LINE);
    }
}
