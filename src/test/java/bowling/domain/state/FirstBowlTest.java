package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.InputNegativeNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FirstBowlTest {

    @DisplayName("FirstBowl 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        State ready = Ready.initialize();

        // when
        State firstBowl = ready.bowl(HitCount.valueOf(9));

        // then
        assertThat(firstBowl).isInstanceOf(FirstBowl.class);
        assertThat(firstBowl).isNotNull();

    }

    @DisplayName("FirstBowl 인스턴스가 종료 여부를 알맞게 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // given
        State ready = Ready.initialize();

        // when
        State firstBowl = ready.bowl(HitCount.valueOf(9));

        // then
        assertThat(firstBowl.isFinish()).isFalse();
    }

    @DisplayName("FirstBowl 인스턴스가 Spare 인스턴스를 반환하는지 테스트")
    @Test
    void 반환_Spare() {
        // given
        State ready = Ready.initialize();

        // when
        State firstBowl = ready.bowl(HitCount.valueOf(0));
        State spare = firstBowl.bowl(HitCount.valueOf(10));

        // then
        assertAll(
                () -> assertThat(spare).isNotNull(),
                () -> assertThat(spare).isInstanceOf(Spare.class)
        );

    }

    @DisplayName("FirstBowl 인스턴스가 Miss 인스턴스를 반환하는지 테스트")
    @Test
    void 반환_Miss() {
        // given
        State ready = Ready.initialize();

        // when
        State firstBowl = ready.bowl(HitCount.valueOf(0));
        State spare = firstBowl.bowl(HitCount.valueOf(9));

        // then
        assertAll(
                () -> assertThat(spare).isNotNull(),
                () -> assertThat(spare).isInstanceOf(Miss.class)
        );

    }

    @DisplayName("FirstBowl 인스턴스에 음수 입력시 예외처리 여부 테스트")
    @Test
    void 검증_음수() {
        // given
        int firstCount = -1;

        // when
        assertThatThrownBy(() -> FirstBowl.from(firstCount))
                .isInstanceOf(InputNegativeNumberException.class)
                .hasMessage("( " + firstCount + " ) 는 음수 값이여서 입력 할 수 없습니다.");
    }

}