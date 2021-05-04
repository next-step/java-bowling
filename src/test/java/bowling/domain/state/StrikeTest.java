package bowling.domain.state;

import bowling.exception.NoMoreBowlActionsException;
import bowling.exception.NoMoreCountingActionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class StrikeTest {

    @DisplayName("Strike 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        State strike = Strike.initialize();

        // then
        assertAll(
                () -> assertThat(strike).isNotNull(),
                () -> assertThat(strike).isInstanceOf(Strike.class)
        );

    }

    @DisplayName("Strike 인스턴스가 알맞은 종료 여부를 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // when
        State strike = Strike.initialize();

        // then
        assertThat(strike.isFinish()).isTrue();
    }


    @DisplayName("Strike 인스턴스가 bowl() 호출시, 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {
        // when
        State strike = Strike.initialize();

        // then
        assertThatThrownBy(() -> strike.bowl(PinCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }

    @DisplayName("Strike 인스턴스가 모든 핀을 쓰러뜨렸는지 확인하는 테스트")
    @Test
    void 검증_핀_처리_여부() {
        // when
        State strike = Strike.initialize();

        // then
        assertThat(strike.isAllPinClear()).isTrue();
    }

    @DisplayName("Strike 인스턴스가 투구 횟수를 반환하는지 테스트")
    @Test
    void 반환_사이즈() {
        // when
        State strike = Strike.initialize();

        // then
        assertThat(strike.size()).isEqualTo(1);
    }

    @DisplayName("Strike 인스턴스가 첫번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_첫번째_투구_값() {
        // when
        State strike = Strike.initialize();

        // then
        assertThat(strike.firstCount()).isEqualTo(10);
    }

    @DisplayName("Strike 인스턴스가 두번째 투구 값을 반환시 예외처리 여부 테스트")
    @Test
    void 검증_두번째_투구_값() {
        // when
        State strike = Strike.initialize();

        // then
        assertThatThrownBy(() -> strike.secondCount())
                .isInstanceOf(NoMoreCountingActionException.class)
                .hasMessage("현재 상태에서는 떨어진 핀의 횟수를 확인 할 수 없습니다.");
    }

}