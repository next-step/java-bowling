package bowling.domain;

public class Score {
    private int score;

    public Score(int score) {
        if(score > 10) {
            throw new IllegalArgumentException("점수는 10을 넘을 수 없습니다.");
        }
        this.score = score;
    }

    public boolean isStrike(){
        return this.score == 10;
    }

}
