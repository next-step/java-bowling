package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PitchesGroupTest {

    @DisplayName("PitchesGroup 객체 생성 테스트")
    @Test
    public void makePitchesGroup_정상() {
        assertThatCode(() -> {
            new PitchesGroup();
        }).doesNotThrowAnyException();
    }

    @DisplayName("투구의 볼링핀 hitCounts를 받아 객체 내부 컬렉션에 추가한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 4, 10})
    public void recordPitch_추가(int hitCounts) {
        PitchesGroup pitchesGroup = new PitchesGroup();
        List<Pitch> pitches = pitchesGroup.getPitches();

        assertThat(pitches.size()).isEqualTo(0);

        pitchesGroup.recordPitch(hitCounts);

        assertThat(pitches.size()).isEqualTo(1);
        assertThat(pitches.get(0).getHitCounts()).isEqualTo(hitCounts);
    }

    @DisplayName("Normal Frame의 경우 다음 프레임으로의 이동 조건 : 스트라이크")
    @Test
    public void isMovableToNextFrame_스트라이크() {
        PitchesGroup pitchesGroup = new PitchesGroup();
        pitchesGroup.recordPitch(10);

        assertThat(pitchesGroup.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("Normal Frame의 경우 다음 프레임으로의 이동 조건 : 2번 투구 했을 경우")
    @Test
    public void isMovableToNextFrame_2번_투구() {
        PitchesGroup pitchesGroup = new PitchesGroup();
        pitchesGroup.recordPitch(3);
        pitchesGroup.recordPitch(5);

        assertThat(pitchesGroup.isMovableToNextFrame()).isTrue();
    }
}
