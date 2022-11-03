package bowling.domain;


public class Positions {

    private Position currentPosition;

    private Position calculatePosition = new Position(1);

    public Positions(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Positions(Position currentPosition, Position calculatePosition) {
        this.currentPosition = currentPosition;
        this.calculatePosition = calculatePosition;
    }

    public Positions(int currentPosition) {
        this.currentPosition = new Position(currentPosition);
    }

    public Position next() {
        currentPosition = currentPosition.next();
        return currentPosition;
    }

    public Position currentCalculatePosition() {
        return this.calculatePosition;
    }

    public Position currentPosition() {
        return this.currentPosition;
    }

    public void increaseCalculatePosition() {
        this.calculatePosition = calculatePosition.next();
    }
}
