package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.utils.StringUtils;

public final class OutputView {

    private static final int FRAME_SIZE = 6;
    private static final int NAME_PADDING_SIZE = 5;

    private final BoardHeaderView boardHeaderView;

    public OutputView() {
        this(new BoardHeaderView());
    }

    public OutputView(BoardHeaderView boardHeaderView) {
        this.boardHeaderView = boardHeaderView;
    }

    public void printScoreBoard(Player player) {
        printBoardHeader();
        printPlayerNameAndStatus(player);
    }

    private void printBoardHeader() {
        System.out.println(boardHeaderView.boardHeader());
    }

    private void printPlayerNameAndStatus(Player player) {
        final Frames frames = player.frames();
        final StringBuilder playerResultBuilder = new StringBuilder();

        playerResultBuilder.append("|").append(StringUtils.padLeft(new PlayerNameView(player).playerName(), NAME_PADDING_SIZE)).append(" ");
        for (Frame frame : frames.value()) {
            playerResultBuilder.append("|").append(StringUtils.alignCenter(new FrameStatusView(frame).frameStatus(), FRAME_SIZE));
        }
        playerResultBuilder.append("|").append("\n");

        System.out.println(playerResultBuilder);
    }
}
