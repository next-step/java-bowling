package bowling.domain.state;

import bowling.domain.HitCount;
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
        // when
        Miss miss = Miss.of(0, 9);

        // then
        assertThat(miss).isNotNull();
    }

    @DisplayName("Miss 인스턴스에 알맞은 값이 주입되었는지 테스트")
    @Test
    void 검증() {
        // given
        int firstCount = 0;
        int secondCount = 10;

        // when and then
        assertThatThrownBy(() -> Miss.of(firstCount, secondCount))
                .isInstanceOf(InsufficientMissCountException.class)
                .hasMessage("( " + firstCount + " )와 ( " + secondCount + " )의 합인 " +
                        "( " + Math.addExact(firstCount, secondCount) + " )는, 9이하 값을 충족하지 않습니다.");

    }

    @DisplayName("Miss 인스턴스가 알맞은 종료 여부를 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // when
        State miss = Miss.of(0, 9);

        // then
        assertThat(miss.isFinish()).isTrue();
    }


    @DisplayName("Miss 인스턴스가 bowl() 호출시, 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {
        // when
        State miss = Miss.of(0, 9);

        // then
        assertThatThrownBy(() -> miss.bowl(HitCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }

    @DisplayName("Miss 인스턴스에 알맞지 않은 범위의 수 입력시 예외처리 여부 테스트")
    @Test
    void 검증_범위의_수() {
        // given
        int firstCount = -1;
        int secondCount = 10;

        // when and then
        assertThatThrownBy(() -> Miss.of(firstCount, secondCount))
                .isInstanceOf(InputNumberOutOfBoundsException.class)
                .hasMessage("맞은 갯수 ( " + firstCount + " ) 는 사용할 수 없는 갯수 입니다.");


        assertThatThrownBy(() -> Miss.of(firstCount + 1, secondCount + 1))
                .isInstanceOf(InputNumberOutOfBoundsException.class)
                .hasMessage("맞은 갯수 ( " + (secondCount + 1) + " ) 는 사용할 수 없는 갯수 입니다.");

    }

    @DisplayName("Miss 인스턴스가 모든 핀을 쓰러뜨렸는지 확인하는 테스트")
    @Test
    void 검증_핀_처리_여부() {
        // given
        int firstCount = 0;
        int secondCount = 9;

        // when
        State firstBowl = Miss.of(firstCount, secondCount);

        // then
        assertThat(firstBowl.isAllPinClear()).isFalse();
    }

}