package bowling.domain.progress;

import static org.assertj.core.api.Assertions.*;

import bowling.Pin;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.first.Gutter;
import bowling.domain.state.end.first.HitNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneralProgressTest {

    private static final Pin MISS = Pin.of(0);
    private static final Pin FIRST_SPARE = Pin.of(3);
    private static final Pin SPARE = Pin.of(7);
    private static final Pin NORMAL = Pin.of(5);
    private static final Pin STRIKE = Pin.of(10);

    private GeneralProgress generalProgress;


    @BeforeEach
    void init() {
        FirstProgress firstProgress = new FirstProgress();
        EndState endState = firstProgress.pitch(FIRST_SPARE);

        generalProgress = new GeneralProgress(endState);
    }


    @Test
    @DisplayName("투구가 0건이면, Gutter이 반환된다.")
    void pitchGutterTest() {
        assertThat(generalProgress.pitch(MISS)).isInstanceOf(Gutter.class);
    }

    @Test
    @DisplayName("Spare 가 반환된다.")
    void pitchSpareTest() {
        assertThat(generalProgress.pitch(SPARE)).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("HitNumber 이 반환된다.")
    void pitchHitNumberTest() {
        assertThat(generalProgress.pitch(NORMAL)).isInstanceOf(HitNumber.class);
    }

    @Test
    @DisplayName("첫번째 투구가 Miss이고, 두번째 투구가 Strike이면, Spare이 반환된다.")
    void pitchSpareTest2() {
        EndState endState = new FirstProgress().pitch(MISS);
        generalProgress = new GeneralProgress(endState);

        assertThat(generalProgress.pitch(STRIKE)).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("첫번째 투구가 Strike이고, 두번째 투구가 Strike이면, Strike이 반환된다.")
    void pitchSpareTest3() {
        EndState endState = new FirstProgress().pitch(STRIKE);
        generalProgress = new GeneralProgress(endState);

        assertThat(generalProgress.pitch(STRIKE)).isInstanceOf(Strike.class);
    }


}