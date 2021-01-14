package bowling.domain.frame.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OnSecondPitchTest {

    @Test
    @DisplayName("출력 확인")
    void getDescription() {
        OnSecondPitch onSecond = new OnSecondPitch(5);

        assertThat(onSecond.getDescription())
                .isEqualTo("5");
    }

    @Test
    @DisplayName("추가 피치에 따른 반환 확인")
    void record() {
        OnSecondPitch onSecondPitch = new OnSecondPitch(5);

        assertThat(onSecondPitch.record(5))
                .isInstanceOf(TwoTimesPitched.class);
    }
}
