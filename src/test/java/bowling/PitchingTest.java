package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class PitchingTest {

    @Test
    void getPin() {
        int expected = 2;
        Pitching pitching = Pitching.of(PitchingStatus.Done, Pin.of(expected));

        then(pitching.getPin()).isEqualTo(Pin.of(expected));
    }

    @Test
    @DisplayName("피칭 완료 확인 검증")
    void isDone() {
        Pitching pitching = Pitching.ofReady();

        then(pitching.isDone()).isFalse();
    }

    @Test
    @DisplayName("피칭 수행 검증")
    void bowl() {
        Pitching pitching = Pitching.ofReady();

        pitching.bowl(Pin.of(2));

        then(pitching.isDone()).isTrue();
    }

    @Test
    @DisplayName("피칭 수행 간 예외 처리 검증")
    void bowlThrow() {
        Pitching pitching = Pitching.of(PitchingStatus.Done, Pin.of(2));

        assertThatThrownBy(() -> pitching.bowl(Pin.of(2)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 투구가 완료된 상태 입니다.");
    }

    @Test
    @DisplayName("쓰러뜨린 핀 갯수가 최댓값인지 검증")
    void isClear() {
        Pitching uncleared = Pitching.of(PitchingStatus.Ready, Pin.of(3));
        Pitching cleared = Pitching.of(PitchingStatus.Ready, Pin.of(10));

        then(uncleared.isClear()).isFalse();
        then(cleared.isClear()).isTrue();
    }

    @Test
    @DisplayName("초기화된 인스턴스 팩토리 검증")
    void ofReady() {
        then(Pitching.ofReady()).isEqualTo(Pitching.of(PitchingStatus.Ready, Pin.ofMin()));
    }
}
