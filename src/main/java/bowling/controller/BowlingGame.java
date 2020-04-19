package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerCount;
import bowling.domain.player.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final Frames frames;

    public BowlingGame() {
        this.frames = Frames.create();
    }

    public void play(final InputView inputView) {
        Players players = join(inputView);

        showOverHead(players);
//        while (!isEnd()) {
//            BowlCount bowlCount = inputView.inputBowlCount(getFrameNumber());
//            Pins pins = Pins.of();
//            frames.bowl(pins.knockOver(bowlCount));
//            OutputView.printOverHead(player.getName(), frames.getStates(), frames.getScores());
//        }
    }

    private void showOverHead(final Players players) {
        OutputView.printFramesHeader();
        for (Player player : players.getPlayers()) {
            OutputView.printOverHead(player.getName(), player.getStates(), player.getScores());
        }
    }

    private Players join(final InputView inputView) {
        final PlayerCount playerCount = inputView.inputPlayerCount();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCount.getPlayerCount(); i++) {
            players.add(inputView.inputPlayerName(i));
        }
        return new Players(players);
    }

    private boolean isEnd() {
        Frame current = frames.getCurrent();
        return current.getFrameNumber().isFinal() && current.isEnd();
    }

    private FrameNumber getFrameNumber() {
        return frames.getCurrent().getFrameNumber();
    }
}
