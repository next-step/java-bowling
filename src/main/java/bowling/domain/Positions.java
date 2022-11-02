package bowling.domain;

/**
 * Created by P-161 at : 2022-11-02
 *
 * 여기에 Positions 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public class Positions {

    private Position currentPosition;

    private Position calculatedPosition = new Position(1);

    public Positions(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Position next() {
        currentPosition = currentPosition.next();
        return currentPosition;
    }

    public boolean isCalculated() {
        return this.currentPosition.equals(calculatedPosition);
    }

    public Position getCalculatedPosition() {
        return calculatedPosition;
    }

    public boolean isSpareRoundCalculable() {
        return currentPosition.minus(calculatedPosition) > 0;
    }

    public boolean isStrikeRoundCalculable(){
        return currentPosition.minus(calculatedPosition) > 1;
    }
}