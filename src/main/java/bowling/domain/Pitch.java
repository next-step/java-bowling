package bowling.domain;

import java.util.Objects;

// 투구 관리 클래스
public class Pitch {

    public static final int BOWLING_PIN_MAX_SIZE = 10;
    public static final String GUIDE_ERR_MAX_SIZE = "넘어뜨린 핀은 10개까지 가능합니다.";
    public static final int BOWLING_PIN_MIN_SIZE = 0;
    // 한 번의 투구에 0 ~ 10개 까지의 핀을 가질 수 있음
    private final int pins;

    private Pitch(final int pins) {
        this.pins = pins;
    }

    public static Pitch valueOf(final int pins) {
        if(pins > BOWLING_PIN_MAX_SIZE || pins < BOWLING_PIN_MIN_SIZE) {
            throw new IllegalArgumentException(GUIDE_ERR_MAX_SIZE);
        }
        return new Pitch(pins);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pitch)) return false;
        final Pitch pitch = (Pitch) o;
        return pins == pitch.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }

    @Override
    public String toString() {
        return String.valueOf(pins);
    }
}
