package bowling.domain;

public class Pitch {

    private int score;

    public Pitch(int score) {
        validate(score);
        this.score = score;
    }

    private void validate(int score) {
        if (score > 10) {
            throw new IllegalArgumentException("한 핏치의 점수가 10을 넘을 수 없습니다. 점수 : " + score);
        }
    }

    public int score() {
        return score;
    }
}
