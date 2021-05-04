package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.RoundNumber;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.domain.player.Players;
import bowling.exception.BowlingException;
import bowling.view.BoardHeaderView;
import bowling.view.FrameStatusView;
import bowling.view.PlayerNameView;
import bowling.view.ScoreView;
import bowling.view.ui.Cell;
import bowling.view.ui.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BowlingGame {

    private static final String EMPTY = "";

    private final Players players;
    private BowlingTemplate bowlingTemplate;

    public BowlingGame(List<PlayerName> playerNames) {
        this.players = playerNames.stream()
                .map(Player::from)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::from));
    }

    public List<Row> scoreBoard() {
        final List<Row> rows = new ArrayList<>();
        rows.add(BoardHeaderView.ROW);
        for (Player player : players.value()) {
            rows.addAll(playerStatusRow(player));
        }

        return rows;
    }

    private List<Row> playerStatusRow(Player player) {
        final List<Row> rows = new ArrayList<>();
        final Frames frames = player.frames();

        rows.add(playerNameAndStatusRow(player, frames));
        rows.add(scoreRow(frames));
        return rows;
    }

    private Row playerNameAndStatusRow(Player player, Frames frames) {
        final Row playerNameAndStatusRow = Row.create();

        playerNameAndStatusRow.addCell(PlayerNameView.from(player).cell());

        for (Frame frame : frames.value()) {
            playerNameAndStatusRow.addCell(FrameStatusView.from(frame).cell());
        }
        return playerNameAndStatusRow;
    }

    private Row scoreRow(Frames frames) {
        final Row scoreRow = Row.create();
        scoreRow.addCell(Cell.center(EMPTY));

        int totalCount = 0;
        for (Frame frame : frames.value()) {
            final ScoreView scoreView = new ScoreView(frame.score(), totalCount);
            totalCount = scoreView.totalCount();
            scoreRow.addCell(scoreView.cell());
        }
        return scoreRow;
    }

    public void playGame(BowlingTemplate bowlingTemplate) {
        this.bowlingTemplate = bowlingTemplate;

        for (int i = RoundNumber.MIN; i <= RoundNumber.MAX; i++) {
            bowlAll(i);
        }
    }

    private void bowlAll(int roundNumber) {
        for (Player player : players.value()) {
            bowl(roundNumber, player);
        }
    }

    private void bowl(int roundNumberSource, Player player) {
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);
        while (!player.isEnded(roundNumber)) {
            downPin(player, roundNumber);
        }
    }

    private void downPin(final Player player, final RoundNumber roundNumber) {
        try {
            bowlingTemplate.execute(player, pin -> player.knockDownPin(roundNumber, pin));
        } catch (BowlingException e) {
            System.err.println(e.getMessage());
            downPin(player, roundNumber);
        }
    }
}
