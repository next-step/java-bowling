package bowling.model;

import bowling.model.frame.FrameNumber;
import bowling.model.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("볼링")
class BowlingTest {

    private static final Participant PARTICIPANT = Participant.from("hyh");

    @Test
    @DisplayName("참가자와 프레임들로 생성")
    void instance() {
        assertAll(
                () -> assertThatNoException().isThrownBy(() -> Bowling.from(PARTICIPANT)),
                () -> assertThatNoException().isThrownBy(() -> Bowling.of(PARTICIPANT, Frames.init()))
        );
    }

    @Test
    @DisplayName("참가자와 프레임들은 필수")
    void instance_nullArguments_thrownIllegalArgumentException() {
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> Bowling.from(null)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> Bowling.of(PARTICIPANT, null)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> Bowling.of(null, Frames.init()))
        );
    }

    @ParameterizedTest(name = "[{index}] 1점 점수 {0} 번 공을 던지면 종료되지 않음")
    @DisplayName("모든 프레임이 종료되지 않으면 종료되지 않음")
    @ValueSource(ints = {0, 5, 10})
    void isFinished_framesNotEnd(int pitchCount) {
        //given
        Bowling hyhBowling = Bowling.from(PARTICIPANT);
        IntStream.range(0, pitchCount)
                .mapToObj(i -> Pins.from(1))
                .forEach(hyhBowling::pitch);
        //when, then
        assertThat(hyhBowling.isNotFinished()).isTrue();
    }

    @ParameterizedTest(name = "[{index}] {0}점 점수 {1} 번 공을 던지면 종료")
    @DisplayName("모든 프레임이 종료되면 종료")
    @CsvSource({"1,20", "5,21", "10,12"})
    void isFinished_framesEnd(int hitCount, int pitchCount) {
        //given
        Bowling hyhBowling = IntStream.range(0, pitchCount)
                .mapToObj(i -> Pins.from(hitCount))
                .reduce(Bowling.from(PARTICIPANT), Bowling::pitch, (bowling1, bowling2) -> bowling2);
        //when, then
        assertThat(hyhBowling.isNotFinished()).isFalse();
    }

    @Test
    @DisplayName("한번 던지면 프레임들에 추가된 상태")
    void pitch() {
        //given
        Bowling hyhBowling = Bowling.from(PARTICIPANT);
        Frames thrownMaxFrames = Frames.init().bowling(Pins.MAX);
        //when, then
        assertThat(hyhBowling.pitch(Pins.MAX)).isEqualTo(Bowling.of(PARTICIPANT, thrownMaxFrames));
    }

    @Test
    @DisplayName("모든 프레임이 종료된 상태에서 더이상 던질 수 없음")
    void pitch_tooManyPitch_thrownIllegalStateException() {
        //given
        Bowling hyhBowling = Bowling.from(PARTICIPANT);
        //when, then
        assertThatIllegalStateException().isThrownBy(() -> IntStream.range(0, Integer.MAX_VALUE)
                .mapToObj(i -> Pins.from(1))
                .reduce(hyhBowling, Bowling::pitch, (bowling1, bowling2) -> bowling2));
    }

    @Test
    @DisplayName("초기 상태의 다음 프레임 번호는 1")
    void nextFrameNumber() {
        assertThat(Bowling.from(PARTICIPANT).nextFrameNumber()).isEqualTo(FrameNumber.FIRST);
    }

    @Test
    @DisplayName("주어진 참가자 그대로 반환")
    void participant() {
        assertThat(Bowling.from(PARTICIPANT).participant()).isEqualTo(PARTICIPANT);
    }

    @Test
    @DisplayName("초기 프레임은 그대로 반환")
    void frames() {
        assertThat(Bowling.from(PARTICIPANT).frames()).isEqualTo(Frames.init());
    }
}
