package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.utils.StringUtils;
import bowling.view.ui.Row;

public final class OutputView {

    private static final int FRAME_SIZE = 6;
    private static final String BORDER = "|";

    private final BoardHeaderView boardHeaderView;

    public OutputView() {
        this(new BoardHeaderView());
    }

    public OutputView(BoardHeaderView boardHeaderView) {
        this.boardHeaderView = boardHeaderView;
    }

    public void printScoreBoard(Player player) {
        printBoardHeader();

        final Frames frames = player.frames();
        printPlayerNameAndStatus(player, frames);
        printScore(frames);
    }

    private void printBoardHeader() {
        System.out.println(boardHeaderView.boardHeader());
    }

    private void printPlayerNameAndStatus(Player player, Frames frames) {
        final Row playerNameAndStatusRow = new Row();

        playerNameAndStatusRow.addCell(PlayerNameView.from(player).cell());

        for (Frame frame : frames.value()) {
            playerNameAndStatusRow.addCell(FrameStatusView.from(frame).cell());
        }
        System.out.print(playerNameAndStatusRow.row());
    }

    private void printScore(Frames frames) {
        final StringBuilder scoreBuilder = new StringBuilder();

        scoreBuilder.append(BORDER).append("      ");
        int totalCount = 0;
        for (Frame frame : frames.value()) {
            final ScoreView scoreView = new ScoreView(frame.score(), totalCount);
            totalCount = scoreView.totalCount();
            scoreBuilder.append(BORDER).append(StringUtils.alignCenter(scoreView.totalScore(), FRAME_SIZE));
        }
        scoreBuilder.append(BORDER).append("\n");

        System.out.println(scoreBuilder);
    }
}
