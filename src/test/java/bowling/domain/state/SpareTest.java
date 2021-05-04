package bowling.domain.state;

import bowling.exception.InputNumberOutOfBoundsException;
import bowling.exception.InsufficientSpareCountException;
import bowling.exception.NoMoreBowlActionsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @DisplayName("Spare 인스턴스 생성 테스트")
    @Test
    void 생성() {
        // given
        State ready = Ready.initialize();

        // when
        State firstBowl = ready.bowl(PinCount.valueOf(0));
        State spare = firstBowl.bowl(PinCount.valueOf(10));

        // then
        assertAll(
                () -> assertThat(spare).isNotNull(),
                () -> assertThat(spare).isInstanceOf(Spare.class)
        );
    }

    @DisplayName("Spare 인스턴스 생성시 들어오는 값들의 합이 10이 되는지 테스트")
    @Test
    void 검증() {
        PinCount firstCount = PinCount.valueOf(9);
        PinCount secondCount = PinCount.valueOf(0);

        // when and then
        assertThatThrownBy(() -> Spare.of(firstCount, secondCount))
                .isInstanceOf(InsufficientSpareCountException.class)
                .hasMessage("( 9 )와 ( 0 )의 합이 10이 아닙니다.");
    }

    @DisplayName("Spare 인스턴스가 알맞은 종료 여부를 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(10);

        // when
        State spare = Spare.of(firstCount, secondCount);

        // then
        assertThat(spare.isFinish()).isTrue();
    }

    @DisplayName("Spare 인스턴스가 bowl() 호출시, 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(10);

        // when
        State spare = Spare.of(firstCount, secondCount);

        // then
        assertThatThrownBy(() -> spare.bowl(PinCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }

    @DisplayName("Spare 인스턴스가 모든 핀을 쓰러뜨렸는지 확인하는 테스트")
    @Test
    void 검증_핀_처리_여부() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(10);

        // when
        State firstBowl = Spare.of(firstCount, secondCount);

        // then
        assertThat(firstBowl.isAllPinClear()).isTrue();
    }

    @DisplayName("Spare 인스턴스가 투구 횟수를 반환하는지 테스트")
    @Test
    void 반환_사이즈() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(10);

        // when
        State spare = Spare.of(firstCount, secondCount);

        // then
        assertThat(spare.size()).isEqualTo(2);
    }

    @DisplayName("Spare 인스턴스가 첫번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_첫번째_투구_값() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(10);

        // when
        State spare = Spare.of(firstCount, secondCount);

        // then
        assertThat(spare.firstCount()).isEqualTo(0);
    }

    @DisplayName("Spare 인스턴스가 두번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_두번째_투구_값() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(10);

        // when
        State spare = Spare.of(firstCount, secondCount);

        // then
        assertThat(spare.secondCount()).isEqualTo(10);

    }

}