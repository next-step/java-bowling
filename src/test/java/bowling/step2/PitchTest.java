package bowling.step2;

import bowling.step2.domain.Pitch;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PitchTest {
    @Test
    public void pitchSuccess() {
        //given

        //when
        Pitch pitch = Pitch.of(10);

        //then
        assertThat(pitch.count()).isEqualTo(10);
    }

    @Test
    public void pitchFail() {
        //given

        //when
        assertThatThrownBy(() -> Pitch.of(11)).isInstanceOf(RuntimeException.class);

        //then
    }
}
