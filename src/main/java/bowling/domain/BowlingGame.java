package bowling.domain;

import java.util.List;

public class BowlingGame {
    public static final int FIRST_ROUND = 0;

    private List<Frames> bowlingGame;
    private Players players;
    private int round;

    public BowlingGame(List<Frames> bowlingGame, Players players, int round) {
        this.bowlingGame = bowlingGame;
        this.players = players;
        this.round = round;
    }

    public static BowlingGame of(List<Frames> bowlingGame, Players players) {
        return new BowlingGame(bowlingGame, players, FIRST_ROUND);
    }

    public boolean isRoundEnd() {
        return bowlingGame.stream()
                .allMatch(frames -> frames.isRoundEnd(round));
    }

    public boolean isEnd() {
        return bowlingGame.stream()
                .allMatch(frames -> frames.isEnd());
    }

    public int size() {
        return bowlingGame.size();
    }

    public Frames currentGame(Player player) {
        int participantNumber = players.participantNumber(player);
        return bowlingGame.get(participantNumber);
    }

    public Frame currentGameFrame(Player player) {
        return currentGame(player).currentFrame().next();
    }

    public Players getPlayers() {
        return players;
    }

    public void checkRound() {
        if (isRoundEnd()) {
            round++;
        }
    }

    public boolean isBowling(Player player) {
        Frame frame = currentGameFrame(player);

        if (!frame.isFinish() && frame.getFrameNumber() - 1 == round) {
            return true;
        }
        return false;
    }

    public void bowl(Player player, int score) {
        Frame frame = currentGameFrame(player);

        Pins pins = Pins.of(score);
        frame.bowl(pins);

        currentGame(player).add(frame);
    }
}
