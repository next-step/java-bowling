package bowling.domain;

import java.util.Objects;

public class FinalFrame implements Frame{

    private int countOfPoint;

    public FinalFrame(int countOfPin) {
        this.countOfPoint = countOfPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return countOfPoint == that.countOfPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPoint);
    }
}
