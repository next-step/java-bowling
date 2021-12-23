package bowling.domain.state.progress;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SecondProgressTest {

    private static final Pin FIRST_HIT_PIN = Pin.of(3);
    private static final Pin SECOND_HIT_SPARE_PIN = Pin.of(7);
    private static final Pin SECOND_HIT_GENERAL_PIN = Pin.of(5);

    private FirstProgress firstProgress;

    @BeforeEach
    void init() {
        firstProgress = new FirstProgress(FIRST_HIT_PIN);
    }

    @Test
    @DisplayName("Spare인경우, true를 반환한다.")
    void isSpareTrueTest() {
        assertThat(new SecondProgress(SECOND_HIT_SPARE_PIN, firstProgress).isSpare()).isTrue();
    }

    @Test
    @DisplayName("Spare가 아니면, false를 반환한다.")
    void isSpareFalseTest() {
        assertThat(new SecondProgress(SECOND_HIT_GENERAL_PIN, firstProgress).isSpare()).isFalse();
    }


    @Test
    @DisplayName("이전에 투구한 갯수가 정상적으로 출력된다.")
    void getBeforeProgressPinCountTest() {
        assertThat(new SecondProgress(SECOND_HIT_GENERAL_PIN,
            firstProgress).getBeforeProgressPinCount()).isEqualTo(3);
    }
}