package bowling.domain;

public class Chance {
    private final Point point;
    private final Status status;

    public Chance(Point point, Status status) {
        this.point = point;
        this.status = status;
    }

    public Point point() {
        return point;
    }

    public String chance() {
        if (status == Status.NONE || status == Status.MISS) {
            return String.valueOf(point.point());
        }
        return status.symbol();
    }

    public boolean isStrike() {
        return status == Status.STRIKE;
    }

    public boolean isSPARE() {
        return status == Status.SPARE;
    }

}
