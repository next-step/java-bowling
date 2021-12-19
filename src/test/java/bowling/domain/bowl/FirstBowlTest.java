package bowling.domain.bowl;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {

    Bowl bowl;

    @BeforeEach
    void initBowl() {
        bowl = FirstBowl.bowl();
    }
    
    @DisplayName("10개의 핀을 넘어 뜨렸다면 스트라이크를 반환한다.")
    @Test
    void pitch_Strike() {
        Bowl pitchedBowl = bowl.pitch(pin(10));
        assertThat(pitchedBowl).isInstanceOf(StrikeBowl.class);
    }

    @DisplayName("10개 미만의 핀을 넘어뜨렸다면 NextBowl을 반환한다.")
    @ParameterizedTest(name = "[{index}] hitCount: {0}")
    @ValueSource(ints = {0, 4, 9})
    void pitch_NotStrike(int hitCount) {
        Bowl pitchedBowl = bowl.pitch(pin(hitCount));
        assertThat(pitchedBowl).isInstanceOf(NextBowl.class);
    }

    public Pin pin(int hitCount) {
        return Pin.from(hitCount);
    }

}
