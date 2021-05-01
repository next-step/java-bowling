package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.NoMoreBowlActionsException;
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
        assertThatThrownBy(() -> strike.bowl(HitCount.valueOf(10)))
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


}