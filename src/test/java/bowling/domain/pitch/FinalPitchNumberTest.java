package bowling.domain.pitch;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinalPitchNumberTest {

    @Test
    void 생성_테스트() {
        assertThat(FinalPitchNumber.of(3)).isEqualTo(FinalPitchNumber.of(3));
        assertThat(FinalPitchNumber.first()).isEqualTo(FinalPitchNumber.of(0));
    }

    @Test
    void 시도_횟수_유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinalPitchNumber.of(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinalPitchNumber.of(4));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinalPitchNumber.of(5));
    }

    @Test
    void 시도_횟수_테스트() {
        assertThat(FinalPitchNumber.of(3).isLastPitch()).isTrue();
        assertThat(FinalPitchNumber.of(2).isLastPitch()).isTrue();
        assertThat(FinalPitchNumber.of(1).isLastPitch()).isFalse();
        assertThat(FinalPitchNumber.of(0).isFirstPitch()).isTrue();
        assertThat(FinalPitchNumber.of(1).isFirstPitch()).isFalse();
    }

    @Test
    void 증가_테스트() {
        // given
        PitchNumber pitchNumber = FinalPitchNumber.of(1);
        // when & then
        assertThat(pitchNumber.increase()).isEqualTo(2);
    }

    @Test
    void 보너스_증가_테스트() {
        // given
        PitchNumber pitchNumber = FinalPitchNumber.of(2);
        PitchNumber pitchNumber2 = FinalPitchNumber.of(3);
        // when & then
        assertThat(pitchNumber.increase()).isEqualTo(3);
        Assertions.assertThrows(IllegalStateException.class, () -> pitchNumber2.increase());
    }

    @Test
    void 보너스_시도_횟수_테스트() {
        assertThat(FinalPitchNumber.of(1).isBonusPitch()).isFalse();
        assertThat(FinalPitchNumber.of(2).isBonusPitch()).isFalse();
        assertThat(FinalPitchNumber.of(3).isBonusPitch()).isTrue();
    }

    @Test
    void 두번째_투구_전_확인_테스트() {
        // given
        PitchNumber pitchNumber = FinalPitchNumber.of(1);
        PitchNumber pitchNumber2 = FinalPitchNumber.of(2);
        PitchNumber pitchNumber3 = FinalPitchNumber.of(3);
        // when & then
        assertThat(pitchNumber.isSecondPitch()).isTrue();
        assertThat(pitchNumber2.isSecondPitch()).isFalse();
        assertThat(pitchNumber3.isSecondPitch()).isFalse();
    }
}
