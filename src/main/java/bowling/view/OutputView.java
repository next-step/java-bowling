package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.view.ui.Cell;
import bowling.view.ui.Row;

public final class OutputView {

    private static final String EMPTY = "";

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
        System.out.print(boardHeaderView.row());
    }

    private void printPlayerNameAndStatus(Player player, Frames frames) {
        final Row playerNameAndStatusRow = Row.create();

        playerNameAndStatusRow.addCell(PlayerNameView.from(player).cell());

        for (Frame frame : frames.value()) {
            playerNameAndStatusRow.addCell(FrameStatusView.from(frame).cell());
        }
        System.out.print(playerNameAndStatusRow.row());
    }

    private void printScore(Frames frames) {
        final Row scoreRow = Row.create();
        scoreRow.addCell(Cell.center(EMPTY));

        int totalCount = 0;
        for (Frame frame : frames.value()) {
            final ScoreView scoreView = new ScoreView(frame.score(), totalCount);
            totalCount = scoreView.totalCount();
            scoreRow.addCell(scoreView.cell());
        }
        System.out.println(scoreRow.row());
    }
}
