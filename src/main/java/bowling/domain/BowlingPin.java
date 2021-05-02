package bowling.domain;

public class BowlingPin {
    private int pins;

    public BowlingPin() {
        pins = 10;
    }

    public int update(int score) {
        if (pins < score) {
            throw new IllegalArgumentException("남아있는 볼링핀 개수보다 입력한 점수가 더 많습니다.");
        }
        pins = pins - score;
        return score;
    }

    public boolean isPinCleared() {
        if (pins == 0) {
            return true;
        }
        return false;
    }

    public void fillPins() {
        pins = 10;
    }
}
