package bowling.domain;

public class Hit {
    public static final int MIN = 0;
    public static final int MAX = 10;
    private final int hit;


    public Hit(int hit) {
        validate(hit);
        this.hit = hit;
    }

    private void validate(int hit) {
        if (hit < MIN || hit > MAX) {
            throw new IllegalArgumentException("범위를 벗어나는 점수가 입력되었습니다.");
        }
    }

    public Hit plus(Hit target) {
        return new Hit(hit + target.hit);
    }

    public boolean isMin() {
        return hit == MIN;
    }

    public boolean isMax() {
        return hit == MAX;
    }

    public int getScore() {
        return hit;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Hit)) {
            return false;
        }
        return hit == ((Hit) obj).hit;
    }
}
