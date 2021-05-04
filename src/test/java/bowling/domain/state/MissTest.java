package bowling.domain.state;

import bowling.exception.InputNumberOutOfBoundsException;
import bowling.exception.InsufficientMissCountException;
import bowling.exception.NoMoreBowlActionsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    @DisplayName("Miss 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(9);

        // when
        State miss = Miss.of(firstCount, secondCount);

        // then
        assertThat(miss).isNotNull();
    }

    @DisplayName("Miss 인스턴스에 알맞은 값이 주입되었는지 테스트")
    @Test
    void 검증() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(10);

        // when and then
        assertThatThrownBy(() -> Miss.of(firstCount, secondCount))
                .isInstanceOf(InsufficientMissCountException.class)
                .hasMessage("( 0 )와 ( 10 )의 합이 9보다 작지 않습니다.");

    }

    @DisplayName("Miss 인스턴스가 알맞은 종료 여부를 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(9);

        // when
        State miss = Miss.of(firstCount, secondCount);

        // then
        assertThat(miss.isFinish()).isTrue();
    }


    @DisplayName("Miss 인스턴스가 bowl() 호출시, 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(9);

        // when
        State miss = Miss.of(firstCount, secondCount);

        // then
        assertThatThrownBy(() -> miss.bowl(PinCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }

    @DisplayName("Miss 인스턴스가 모든 핀을 쓰러뜨렸는지 확인하는 테스트")
    @Test
    void 검증_핀_처리_여부() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(9);

        // when
        State miss = Miss.of(firstCount, secondCount);

        // then
        assertThat(miss.isAllPinClear()).isFalse();
    }

    @DisplayName("Miss 인스턴스가 투구 횟수를 반환하는지 테스트")
    @Test
    void 반환_사이즈() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(9);

        // when
        State miss = Miss.of(firstCount, secondCount);

        // then
        assertThat(miss.size()).isEqualTo(2);
    }

    @DisplayName("Miss 인스턴스가 첫번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_첫번째_투구_값() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(9);

        // when
        State miss = Miss.of(firstCount, secondCount);

        // then
        assertThat(miss.firstCount()).isEqualTo(0);
    }

    @DisplayName("Miss 인스턴스가 두번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_두번째_투구_값() {
        // given
        PinCount firstCount = PinCount.valueOf(0);
        PinCount secondCount = PinCount.valueOf(9);

        // when
        State miss = Miss.of(firstCount, secondCount);

        // then
        assertThat(miss.secondCount()).isEqualTo(9);

    }

}