package bowling.domain.state;

import bowling.exception.NoMoreCountingActionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    @DisplayName("Ready 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        State ready = Ready.initialize();

        // then
        assertThat(ready).isNotNull();
    }

    @DisplayName("Ready 인스턴스가 종료 여부를 알맞게 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // when
        State ready = State.ready();

        // then
        assertThat(ready.isFinish()).isFalse();
    }

    @DisplayName("Ready 인스턴스가 10개 미만의 핀을 맞추면 FirstBowl 반환 여부 테스트")
    @Test
    void 반환_FirstBowl() {
        // when
        State ready = State.ready();

        // then
        assertThat(ready.bowl(PinCount.valueOf(9))).isInstanceOf(FirstBowl.class);

    }

    @DisplayName("Ready 인스턴스가 10개의 핀을 맞추면 Strike 반환 여부 테스트")
    @Test
    void 반환_Strike() {
        // when
        State ready = State.ready();

        // then
        assertThat(ready.bowl(PinCount.valueOf(10))).isInstanceOf(Strike.class);

    }

    @DisplayName("Ready 인스턴스가 모든 핀을 쓰러뜨렸는지 확인하는 테스트")
    @Test
    void 검증_핀_처리_여부() {
        // when
        State ready = State.ready();

        // then
        assertThat(ready.isAllPinClear()).isFalse();
    }

    @DisplayName("Ready 인스턴스가 투구 횟수를 반환하는지 테스트")
    @Test
    void 반환_사이즈() {
        // when
        State ready = State.ready();

        // then
        assertThat(ready.size()).isEqualTo(0);
    }

    @DisplayName("Ready 인스턴스가 첫번째 투구 값을 반환시 예외처리 여부 테스트")
    @Test
    void 검증_첫번째_투구_값() {
        // when
        State ready = State.ready();

        // then
        assertThatThrownBy(() -> ready.firstCount())
                .isInstanceOf(NoMoreCountingActionException.class)
                .hasMessage("현재 상태에서는 떨어진 핀의 횟수를 확인 할 수 없습니다.");
    }

    @DisplayName("Ready 인스턴스가 두번째 투구 값을 반환시 예외처리 여부 테스트")
    @Test
    void 검증_두번째_투구_값() {
        // when
        State ready = State.ready();

        // then
        assertThatThrownBy(() -> ready.secondCount())
                .isInstanceOf(NoMoreCountingActionException.class)
                .hasMessage("현재 상태에서는 떨어진 핀의 횟수를 확인 할 수 없습니다.");
    }

}