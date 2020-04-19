package bowling.domain.score;

public class NomalScore extends Score {

    public NomalScore(ScoreType scoreType, int point) {
        super(scoreType, point);
    }

    @Override
    public Score nextScore(int point) {
        throw new IllegalArgumentException("스코어를 추가 할 수 없습니다.");
    }
}
