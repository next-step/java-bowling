package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class NormalPitchingsTest {

    @Test
    @DisplayName("투구 검증")
    void bowl() {
        Pitchings pitchings = NormalPitchings.of(Pitching.ofReady(), Pitching.ofReady());

        pitchings.bowl(Pin.ofMin());
        then(pitchings.isDone()).isFalse();

        pitchings.bowl(Pin.ofMin());
        then(pitchings.isDone()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 투구 검증")
    void bowlStrike() {
        Pitchings pitchings = NormalPitchings.of(Pitching.ofReady(), Pitching.ofReady());

        pitchings.bowl(Pin.ofMax());

        then(pitchings.isDone()).isTrue();
    }

    @Test
    @DisplayName("투구 종료 시 예외 처리 검증")
    void bowlThrownEnd() {
        Pitchings pitchings = NormalPitchings.of(
                Pitching.of(PitchingStatus.Done, Pin.ofMin()),
                Pitching.of(PitchingStatus.Done, Pin.ofMin())
        );

        assertThatThrownBy(() -> pitchings.bowl(Pin.of(2)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("투구가 모두 완료된 상태 입니다.");
    }

    @Test
    @DisplayName("투구 점수 초과 시 예외 처리 검증")
    void bowlThrownOver() {
        Pitchings pitchings = NormalPitchings.of(
                Pitching.of(PitchingStatus.Done, Pin.of(9)),
                Pitching.of(PitchingStatus.Ready, Pin.ofMin())
        );

        assertThatThrownBy(() -> pitchings.bowl(Pin.of(2)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("첫 번째 투구와 두 번째 투구의 합이 범위를 초과했습니다.");
    }

    @Test
    @DisplayName("첫 번째 투구 점수 검증")
    void getFirstPin() {
        Pin expected = Pin.of(2);
        Pitching pitching = Pitching.of(PitchingStatus.Done, expected);
        Pitchings pitchings = NormalPitchings.of(pitching, pitching);

        then(pitchings.getSecondPin()).isEqualTo(expected);
    }

    @Test
    @DisplayName("두 번째 투구 점수 검증")
    void getSecondPin() {
        Pin expected = Pin.of(2);
        Pitching pitching = Pitching.of(PitchingStatus.Done, expected);
        Pitchings pitchings = NormalPitchings.of(pitching, pitching);

        then(pitchings.getSecondPin()).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫 번째 투구 완료 여부 검증")
    void isFirstDone() {
        Pitchings ready = NormalPitchings.of(Pitching.ofReady(), Pitching.ofReady());
        Pitchings done = NormalPitchings.of(Pitching.of(PitchingStatus.Done, Pin.ofMin()), Pitching.ofReady());

        then(ready.isFirstDone()).isFalse();
        then(done.isFirstDone()).isTrue();
    }

    @Test
    @DisplayName("두 번째 투구 완료 여부 검증")
    void isSecondDone() {
        Pitchings ready = NormalPitchings.of(Pitching.ofReady(), Pitching.ofReady());
        Pitchings done = NormalPitchings.of(
                Pitching.of(PitchingStatus.Done, Pin.ofMin()),
                Pitching.of(PitchingStatus.Done, Pin.ofMin())
        );

        then(ready.isSecondDone()).isFalse();
        then(done.isSecondDone()).isTrue();
    }

    private static Stream<Arguments> provideIsDoneArguments() {
        Pitching done = Pitching.of(PitchingStatus.Done, Pin.ofMin());
        return Stream.of(
                Arguments.of(NormalPitchings.of(Pitching.ofReady(), Pitching.ofReady()), Boolean.FALSE),
                Arguments.of(NormalPitchings.of(done, Pitching.ofReady()), Boolean.FALSE),
                Arguments.of(NormalPitchings.of(done, done), Boolean.TRUE)
        );
    }

    @ParameterizedTest
    @DisplayName("완료 상태 확인 검증")
    @MethodSource("provideIsDoneArguments")
    void isDone(Pitchings pitchings, boolean expected) {
        then(pitchings.isDone()).isEqualTo(expected);
    }

    @Test
    @DisplayName("초기화된 인스턴스 팩토리 검증")
    void ofReady() {
        Pitchings expected = NormalPitchings.of(Pitching.ofReady(), Pitching.ofReady());

        then(NormalPitchings.ofReady()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideIsCleareArguments() {
        Pitching min = Pitching.of(PitchingStatus.Done, Pin.ofMin());
        Pitching max = Pitching.of(PitchingStatus.Done, Pin.ofMax());
        return Stream.of(
                Arguments.of(NormalPitchings.of(max, min), Boolean.TRUE),
                Arguments.of(NormalPitchings.of(min, max), Boolean.FALSE)
        );
    }

    @ParameterizedTest
    @DisplayName("쓰러뜨린 첫 번째 핀 확인 검증")
    @MethodSource("provideIsCleareArguments")
    void isFirstPitchingClear(NormalPitchings normalPitchings, boolean expected) {
        then(normalPitchings.isFirstPitchingClear()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("쓰러뜨린 두 번째 핀 확인 검증")
    @MethodSource("provideIsCleareArguments")
    void isSecondPitchingClear(NormalPitchings normalPitchings, boolean expected) {
        then(normalPitchings.isFirstPitchingClear()).isEqualTo(expected);
    }

    @Test
    @DisplayName("핀 갯수가 범위를 초과하는지 확인 검증")
    void isOverMaxPins() {
        Pitching pitching = Pitching.of(PitchingStatus.Done, Pin.ofMax());
        NormalPitchings normalPitchings = NormalPitchings.of(pitching, Pitching.ofReady());

        then(normalPitchings.isOverMaxPins(Pin.ofMin())).isFalse();
        then(normalPitchings.isOverMaxPins(Pin.of(1))).isTrue();
    }

    @Test
    @DisplayName("스페어 여부 확인 검증")
    void isSpare() {
        Pitching min = Pitching.of(PitchingStatus.Done, Pin.ofMin());
        Pitching max = Pitching.of(PitchingStatus.Done, Pin.ofMax());
        Pitching one = Pitching.of(PitchingStatus.Done, Pin.of(1));
        NormalPitchings pitchingForTrue = NormalPitchings.of(min, max);
        NormalPitchings pitchingForFalse = NormalPitchings.of(min, one);

        then(pitchingForTrue.isSpare()).isTrue();
        then(pitchingForFalse.isSpare()).isFalse();
    }
}
