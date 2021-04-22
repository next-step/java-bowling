package bowling.domain.engine;

public class Pins {

    static final int MAXIMUM_PINS = 10;

    private int remains = MAXIMUM_PINS;

    public void knockDown(PitchResult result) {
        if (remains < result.getValue()) {
            throw new IllegalArgumentException("남아있는 핀의 개수보다 더 많은 핀을 쓰러트릴 수 없습니다.");
        }

        remains -= result.getValue();
    }

    public boolean isAllCleared() {
        return remains == 0;
    }

    public void reset() {
        remains = MAXIMUM_PINS;
    }

}
