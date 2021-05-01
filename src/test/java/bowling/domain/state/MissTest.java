package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.InputNegativeNumberException;
import bowling.exception.InsufficientMissCountException;
import bowling.exception.NoMoreBowlActionsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @DisplayName("Miss 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Miss miss = Miss.from(0, 9);

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
        assertThatThrownBy(() -> Miss.from(firstCount, secondCount))
                .isInstanceOf(InsufficientMissCountException.class)
                .hasMessage("( " + firstCount + " )와 ( " + secondCount + " )의 합인 " +
                        "( " + Math.addExact(firstCount, secondCount) + " )는, 9이하 값을 충족하지 않습니다.");

    }

    @DisplayName("Miss 인스턴스가 알맞은 종료 여부를 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // when
        State miss = Miss.from(0, 9);

        // then
        assertThat(miss.isFinish()).isTrue();
    }


    @DisplayName("Miss 인스턴스가 bowl() 호출시, 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {
        // when
        State miss = Miss.from(0, 9);

        // then
        assertThatThrownBy(() -> miss.bowl(HitCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }

    @DisplayName("Miss 인스턴스에 음수 입력시 예외처리 여부 테스트")
    @Test
    void 검증_음수() {
        // given
        int firstCount = -1;
        int secondCount = 10;

        // when
        assertThatThrownBy(() -> Miss.from(firstCount, secondCount))
                .isInstanceOf(InputNegativeNumberException.class)
                .hasMessage("( " + firstCount + " ) 는 음수 값이여서 입력 할 수 없습니다.");
    }
}