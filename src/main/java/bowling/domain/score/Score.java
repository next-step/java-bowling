package bowling.domain.score;

public class Score {

    private int point;
    private int bonusCount;

    public Score(int point, int bonusCount) {
        this.point = point;
        this.bonusCount = bonusCount;
    }

    public void addBonusPoint(int point) {
        this.point += point;
        bonusCount--;
    }

    public boolean isEnd() {
        return bonusCount == 0;
    }

    public int getPoint() {
        return point;
    }
}
