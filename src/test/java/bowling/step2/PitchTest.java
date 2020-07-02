package bowling.step2;

import bowling.step2.domain.pitch.NormalPitch;
import bowling.step2.domain.pitch.Pitch;
import bowling.step2.domain.pitch.PitchType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PitchTest {

    @Test
    @DisplayName("객체 생성 테스트")
    void create() {
        Pitch pitch = new NormalPitch(3, PitchType.DEFAULT);
        assertThat(pitch).isEqualTo(new NormalPitch(3,PitchType.DEFAULT));
    }

    @ParameterizedTest
    @DisplayName("pitch 유효성 검사")
    @ValueSource(ints = {-1,11})
    void pitch_invalid(int inputPitch) {
        assertThatThrownBy(() -> new NormalPitch(inputPitch, PitchType.DEFAULT))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("몇번째 투구에 핀을 다 쓰러뜨리냐에 따라 스트라이크 or 스페어처리 테스트")
    void firstPitch_nextPitch_test() {
        assertThat(new NormalPitch(10, PitchType.DEFAULT)
                                            .firstPitch()).isEqualTo(new NormalPitch(10, PitchType.STRIKE));

        assertThat(new NormalPitch(0, PitchType.DEFAULT)
                .nextPitch(10)).isEqualTo(new NormalPitch(10, PitchType.SPARE));
    }
}
