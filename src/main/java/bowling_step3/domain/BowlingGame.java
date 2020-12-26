package bowling_step3.domain;

import bowling_step3.domain.Frame.GameFrames;
import bowling_step3.exception.PitchOverBoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BowlingGame {

    private final Player player;

    private final GameFrames gameFrames;

    public BowlingGame(Player playerName) {
        this.player = playerName;
        this.gameFrames = new GameFrames();
    }

    public void pitch(int countOfKnockDown) {
        if (isFinish()) {
            throw new PitchOverBoundException();
        }

        gameFrames.pitch(countOfKnockDown);
    }

    public boolean isFinish() {
        return gameFrames.isFinish();
    }

    public GameFrames getGameFrames() {
        return this.gameFrames;
    }

    public List<Integer> getScore() {
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (Integer score : Collections.unmodifiableList(gameFrames.getScores())) {
            sum += score;
            list.add(sum);
        }
        return list;
    }

    public Player getPlayer() {
        return player;
    }

    public int getFrameCount() {
        return gameFrames.getIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
