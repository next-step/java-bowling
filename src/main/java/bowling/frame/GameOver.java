package bowling.frame;

import bowling.state.Throwing;

import java.util.Objects;

public class GameOver {

    private int count;

    public void increaseBowlCount() {
        this.count++;
    }

    public boolean isEndGame(Throwing state) {
        if (count == LastFrame.FRAME_MAX_BOWL) {
            return true;
        }
        return count == LastFrame.FRAME_MIN_BOWL && state.isMiss();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameOver gameOver = (GameOver) o;
        return count == gameOver.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "GameOver{" +
                "count=" + count +
                '}';
    }
}
