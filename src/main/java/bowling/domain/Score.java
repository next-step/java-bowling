package bowling.domain;

public class Score {
    private int pins;

    public Score() {
        pins = 10;
    }

    public int updateScore(int score) {
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
