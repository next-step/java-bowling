package bowling.domain.progress;

import static org.assertj.core.api.Assertions.*;

import bowling.domain.Pin;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.first.HitNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstProgressTest {

    private static final Pin STRIKE = Pin.of(10);
    private static final Pin NORMAL = Pin.of(5);

    private FirstProgress progress;


    @BeforeEach
    void init() {
        progress = new FirstProgress();
    }


    @Test
    @DisplayName("Strike 수행시, `Strike` 이 반환된다.")
    void pitchStrikeTest() {
        assertThat(progress.pitch(STRIKE)).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("Strike가 아닌경우 `HitNumber` 이 반환된다.")
    void pitchNormalTest() {
        assertThat(progress.pitch(NORMAL)).isInstanceOf(HitNumber.class);
    }


}