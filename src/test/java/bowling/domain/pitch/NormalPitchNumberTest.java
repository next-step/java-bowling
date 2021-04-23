package bowling.domain.pitch;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NormalPitchNumberTest {

    @Test
    void 생성_테스트() {
        assertThat(NormalPitchNumber.of(1)).isEqualTo(NormalPitchNumber.of(1));
        assertThat(NormalPitchNumber.first()).isEqualTo(NormalPitchNumber.of(0));
    }

    @Test
    void 시도_횟수_유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> NormalPitchNumber.of(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> NormalPitchNumber.of(3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> NormalPitchNumber.of(5));
    }

    @Test
    void 시도_횟수_테스트() {
        assertThat(NormalPitchNumber.of(2).isLastPitch()).isTrue();
        assertThat(NormalPitchNumber.of(1).isLastPitch()).isFalse();
        assertThat(NormalPitchNumber.of(0).isFirstPitch()).isTrue();
        assertThat(NormalPitchNumber.of(1).isFirstPitch()).isFalse();
    }

    @Test
    void 증가_테스트() {
        // given
        PitchNumber pitchNumber = NormalPitchNumber.of(1);
        // when & then
        assertThat(pitchNumber.increase()).isEqualTo(2);
    }

    @Test
    void 보너스_증가_테스트() {
        // given
        PitchNumber pitchNumber = NormalPitchNumber.of(2);
        // when & then
        Assertions.assertThrows(IllegalStateException.class, () -> pitchNumber.increase());
    }

    @Test
    void 보너스_시도_테스트() {
        // given
        PitchNumber pitchNumber = NormalPitchNumber.of(2);
        // when & then
        Assertions.assertThrows(IllegalStateException.class, () -> pitchNumber.isBonusPitch());
    }

    @Test
    void 두번째_투구_전_확인_테스트() {
        // given
        PitchNumber pitchNumber = NormalPitchNumber.of(1);
        PitchNumber pitchNumber2 = NormalPitchNumber.of(2);
        // when & then
        assertThat(pitchNumber.isSecondPitch()).isTrue();
        assertThat(pitchNumber2.isSecondPitch()).isFalse();
    }
}
