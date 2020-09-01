package camp.nextstep.edu.rebellion.bowling.domain;

public class Score {
    private final static int MIN_HITS = 0;
    private final static int MAX_HITS = 10;

    private final Integer hits;

    private Score(int hits) {
        checkRange(hits);
        this.hits = Integer.valueOf(hits);
    }

    public boolean equals(int hits) {
        return equals(Score.of(hits));
    }

    public boolean equals(Score score) {
        return this.hits == score.hits;
    }

    public int getHits() {
        return hits.intValue();
    }

    public static Score of(int hits) {
        return new Score(hits);
    }

    private void checkRange(int hits) {
        if (MIN_HITS > hits || MAX_HITS < hits) {
            throw new IllegalArgumentException("투구 값이 잘못 되었습니다 (볼링 핀 개수 초과) " + hits);
        }
    }

    @Override
    public int hashCode() {
        return hits.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        return (obj instanceof Score &&
                ((Score) obj).hits == this.hits);
    }
}
