package bowling.step2;

import bowling.step2.domain.Pitch;
import bowling.step2.domain.PitchGroup;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PitchGroupTest {
    @Test
    public void pitchSuccess() {
        //given
        PitchGroup pitchGroup = PitchGroup.of();

        //when
        pitchGroup.pitch(10);

        //then

    }

    @Test
    public void pitchFail() {
        //given
        PitchGroup pitchGroup = PitchGroup.of();

        //when
        assertThatThrownBy(() -> {
            pitchGroup.pitch(11);
        }).isInstanceOf(RuntimeException.class);

        //then
    }

    @Test
    public void pitchCount() {
        //given
        PitchGroup pitchGroup = PitchGroup.of();

        //when
        pitchGroup.pitch(3);
        pitchGroup.pitch(3);

        //then
        assertThat(pitchGroup.size()).isEqualTo(2);
    }
}
