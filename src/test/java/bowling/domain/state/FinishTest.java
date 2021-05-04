package bowling.domain.state;

import bowling.exception.NoMoreBowlActionsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishTest {

    @DisplayName("Finish 인스턴스가 끝났는지 반환 테스트")
    @Test
    void 검증() {
        // when
        Finish finish = new Finish() {
            @Override
            public boolean isAllPinClear() {
                return true;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public int firstCount() {
                return 0;
            }

            @Override
            public int secondCount() {
                return 0;
            }
        };

        // then
        assertThat(finish.isFinish()).isTrue();
    }

    @DisplayName("Finish 인스턴스가 bowl() 호출시, 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {

        // when
        Finish finish = new TestFinish();

        // then
        assertThatThrownBy(() -> finish.bowl(PinCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }
}