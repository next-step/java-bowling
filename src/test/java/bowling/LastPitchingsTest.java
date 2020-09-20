package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class LastPitchingsTest {

    @Test
    @DisplayName("투구 검증")
    void bowl() {
        Pitchings pitchings = LastPitchings.ofReady();

        pitchings.bowl(Pin.ofMin());
        then(pitchings.isDone()).isFalse();

        pitchings.bowl(Pin.ofMin());
        then(pitchings.isDone()).isTrue();

    }

    @Test
    @DisplayName("스페어 투구 검증")
    void bowlSpare() {
        Pitchings pitchings = LastPitchings.ofReady();

        pitchings.bowl(Pin.ofMin());
        then(pitchings.isDone()).isFalse();

        pitchings.bowl(Pin.ofMax());
        then(pitchings.isDone()).isFalse();

        pitchings.bowl(Pin.ofMax());
        then(pitchings.isDone()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 투구 검증")
    void bowlStrike() {
        Pitchings pitchings = LastPitchings.ofReady();

        pitchings.bowl(Pin.ofMax());
        then(pitchings.isDone()).isFalse();

        pitchings.bowl(Pin.ofMax());
        then(pitchings.isDone()).isFalse();

        pitchings.bowl(Pin.ofMax());
        then(pitchings.isDone()).isTrue();
    }

    @Test
    @DisplayName("투구 종료 시 예외 처리 검증")
    void bowlThrownEnd() {
        Pitchings pitchings = LastPitchings.ofReady();

        pitchings.bowl(Pin.ofMin());
        pitchings.bowl(Pin.ofMin());

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
        Pitchings lastPitchings = LastPitchings.ofReady();

        then(lastPitchings.getFirstPin()).isEqualTo(Pin.ofMin());
    }

    @Test
    @DisplayName("두 번째 투구 점수 검증")
    void getSecondPin() {
        Pitchings lastPitchings = LastPitchings.ofReady();

        then(lastPitchings.getSecondPin()).isEqualTo(Pin.ofMin());
    }

    @Test
    @DisplayName("보너스 투구 점수 검증")
    void getBonusPin() {
        Pitchings lastPitchings = LastPitchings.ofReady();

        then(lastPitchings.getBonusPin()).isEqualTo(Pin.ofMin());
    }

    @Test
    @DisplayName("첫 번째 투구 완료 여부 검증")
    void isFirstDone() {
        Pitchings lastPitchings = LastPitchings.ofReady();

        then(lastPitchings.isFirstDone()).isFalse();

        lastPitchings.bowl(Pin.of(10));
        then(lastPitchings.isFirstDone()).isTrue();
    }

    @Test
    @DisplayName("두 번째 투구 완료 여부 검증")
    void isSecondDone() {
        Pitchings lastPitchings = LastPitchings.ofReady();

        lastPitchings.bowl(Pin.of(10));
        then(lastPitchings.isSecondDone()).isFalse();

        lastPitchings.bowl(Pin.of(10));
        then(lastPitchings.isSecondDone()).isTrue();
    }

    @Test
    @DisplayName("보너스 투구 완료 여부 검증")
    void isBonusDone() {
        Pitchings lastPitchings = LastPitchings.ofReady();

        lastPitchings.bowl(Pin.of(10));
        lastPitchings.bowl(Pin.of(10));
        lastPitchings.bowl(Pin.of(10));

        then(lastPitchings.isBonusDone()).isTrue();
    }

    @Test
    @DisplayName("완료 상태 확인 검증")
    void isDone() {
        Pitchings lastPitchings = LastPitchings.ofReady();

        lastPitchings.bowl(Pin.of(10));
        then(lastPitchings.isDone()).isFalse();

        lastPitchings.bowl(Pin.of(10));
        then(lastPitchings.isDone()).isFalse();

        lastPitchings.bowl(Pin.of(10));
        then(lastPitchings.isDone()).isTrue();
    }

    @Test
    @DisplayName("첫 번째 투구 점수 계산 검증")
    void getFirstScore() {
        int expected = 3;
        Pitching pitching = Pitching.of(PitchingStatus.Done, Pin.of(expected));
        Pitching min = Pitching.of(PitchingStatus.Done, Pin.ofMin());
        NormalPitchings normalPitchings = NormalPitchings.of(pitching, min);
        LastPitchings lastPitchings = LastPitchings.of(normalPitchings, min);

        then(lastPitchings.getFirstScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("점수 계산 검증")
    void calculateScore() {
        int expected1 = 2, expected2 = 8, expected3 = 5;
        Pitching pitching1 = Pitching.of(PitchingStatus.Done, Pin.of(expected1));
        Pitching pitching2 = Pitching.of(PitchingStatus.Done, Pin.of(expected2));
        Pitching pitching3 = Pitching.of(PitchingStatus.Done, Pin.of(expected3));
        NormalPitchings normalPitchings = NormalPitchings.of(pitching1, pitching2);
        LastPitchings lastPitchings = LastPitchings.of(normalPitchings, pitching3);

        then(lastPitchings.calculateScore()).isEqualTo(expected1 + expected2 + expected3);
    }
}
