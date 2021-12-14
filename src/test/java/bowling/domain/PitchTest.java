package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchTest {

    @Test
    void 첫_투구를_생성한다() {
        //given
        Pitch pitch = Pitch.first();
        //when
        //then
        assertThat(pitch).isEqualTo(Pitch.first());
    }

    @Test
    void 첫_투구_n개_이후_다음_투구를_생성한다() {
        //given
        Pitch pitch = Pitch.first();
        int exist = pitch.run((bound) -> 6);
        //when
        Pitch nextPitch = pitch.next();
        //then
        assertThat(nextPitch.pinsSize()).isEqualTo(exist);
    }

    @Test
    void 투구를_수행한다() {
        //given
        Pitch pitch = Pitch.first();
        //when
        int exist = pitch.run((bound) -> 7);
        //then
        assertThat(pitch.pinsSize()).isEqualTo(exist);
    }
}
