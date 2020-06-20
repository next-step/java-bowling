package bowling.domain;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.Pitches;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PitchesTest {

    @DisplayName("Pitches 객체 생성 테스트")
    @Test
    public void makePitches_정상() {
        assertThatCode(() -> {
            new Pitches();
        }).doesNotThrowAnyException();
    }

    @DisplayName("투구의 볼링핀 hitCounts를 받아 객체 내부 컬렉션에 추가한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 4, 10})
    public void recordPitch_추가(int hitCounts) {
        Pitches pitches = new Pitches();
        List<Pitch> pitchesList = pitches.getPitches();

        assertThat(pitchesList.size()).isEqualTo(0);

        pitches.recordPitch(hitCounts);

        assertThat(pitchesList.size()).isEqualTo(1);
        assertThat(pitchesList.get(0).getHitCounts()).isEqualTo(hitCounts);
    }

    @DisplayName("Normal Frame의 경우 다음 프레임으로의 이동 조건 : 스트라이크")
    @Test
    public void isMovableToNextFrame_스트라이크() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(10);

        assertThat(pitches.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("Normal Frame의 경우 다음 프레임으로의 이동 조건 : 2번 투구 했을 경우")
    @Test
    public void isMovableToNextFrame_2번_투구() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(3);
        pitches.recordPitch(5);

        assertThat(pitches.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("Final Frame에서 스트라이크나 스페어가 아닌 경우 false 반환")
    @Test
    public void isMovableToBonusPitch_False() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(8);
        pitches.recordPitch(1);

        assertThat(pitches.isMovableToNextPitch()).isFalse();
    }

    @DisplayName("첫 번째 투구와 두 번째 투구의 합이 10을 넘어가면 예외 발생")
    @Test
    public void pitchSum_10초과_예외() {
        Pitches pitches = new Pitches();
        pitches.recordPitch(5);

        assertThatThrownBy(() -> {
            pitches.recordPitch(9);
        }).isInstanceOf(BowlingBuildingException.class);
    }
}
