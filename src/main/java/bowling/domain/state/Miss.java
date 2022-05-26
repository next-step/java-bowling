package bowling.domain.state;

import bowling.domain.Score;

public class Miss extends Finished{
    private final int firstCountOfPins;
    private final int secondCountOfPins;

    public Miss(int firstCountOfPins, int secondCountOfPins) {
        validate(firstCountOfPins, secondCountOfPins);
        this.firstCountOfPins = firstCountOfPins;
        this.secondCountOfPins = secondCountOfPins;
    }

    private void validate(int firstCountOfPins, int secondCountOfPins) {
        if(firstCountOfPins + secondCountOfPins > 10) {
            throw new IllegalArgumentException("핀의 합은 10개가 넘을 수 없습니다.");
        }
    }

    @Override
    public Score getScore() {
        return new Score(this.firstCountOfPins+this.secondCountOfPins, 0);
    }

    @Override
    public String expression() {
        if(firstCountOfPins != 0 && secondCountOfPins == 0) {
            return firstCountOfPins + "|-";
        }
        if(firstCountOfPins ==0 && secondCountOfPins != 0) {
            return "-|" + secondCountOfPins;
        }
        return firstCountOfPins + "|" +secondCountOfPins;
    }

    public int getFirstCountOfPins() {
        return firstCountOfPins;
    }

    public int getSecondCountOfPins() {
        return secondCountOfPins;
    }
}
