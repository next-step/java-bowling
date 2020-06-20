package bowling.domain;


import java.util.Objects;

public class FrameBowlState {
    private int downPin;
    private ScoreType scoreType;

    public FrameBowlState(int downPin, ScoreType scoreType) {
        this.downPin = downPin;
        this.scoreType = scoreType;
    }

    public int getDownPin() {
        return downPin;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameBowlState that = (FrameBowlState) o;
        return downPin == that.downPin &&
            scoreType == that.scoreType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(downPin, scoreType);
    }
}
