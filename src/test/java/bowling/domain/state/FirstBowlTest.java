package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.InputNumberOutOfBoundsException;
import bowling.exception.NoMoreCountingActionException;
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

    @DisplayName("FirstBowl 인스턴스에 알맞지 않은 범위의 수 입력시 예외처리 여부 테스트")
    @Test
    void 검증_범위의_수() {
        // given
        int firstCount = -1;
        int secondCount = 11;

        // when and then
        assertThatThrownBy(() -> FirstBowl.from(firstCount))
                .isInstanceOf(InputNumberOutOfBoundsException.class)
                .hasMessage("맞은 갯수 ( " + firstCount + " ) 는 사용할 수 없는 갯수 입니다.");

        assertThatThrownBy(() -> FirstBowl.from(secondCount))
                .isInstanceOf(InputNumberOutOfBoundsException.class)
                .hasMessage("맞은 갯수 ( " + secondCount + " ) 는 사용할 수 없는 갯수 입니다.");
    }

    @DisplayName("FirstBowl 인스턴스가 모든 핀을 쓰러뜨렸는지 확인하는 테스트")
    @Test
    void 검증_핀_처리_여부() {
        // given
        int hitCount = 9;

        // when
        State firstBowl = FirstBowl.from(hitCount);

        // then
        assertThat(firstBowl.isAllPinClear()).isFalse();
    }

    @DisplayName("FirstBowl 인스턴스가 투구 횟수를 반환하는지 테스트")
    @Test
    void 반환_사이즈() {
        // given
        int hitCount = 9;

        // when
        State firstBowl = FirstBowl.from(hitCount);

        // then
        assertThat(firstBowl.size()).isEqualTo(1);
    }

    @DisplayName("FirstBowl 인스턴스가 첫번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_첫번째_투구_값() {
        // given
        int hitCount = 9;

        // when
        State firstBowl = FirstBowl.from(hitCount);

        // then
        assertThat(firstBowl.firstCount()).isEqualTo(9);
    }

    @DisplayName("FirstBowl 인스턴스가 두번째 투구 값을 반환시 예외처리 여부 테스트")
    @Test
    void 검증_두번째_투구_값() {
        // given
        int hitCount = 9;

        // when
        State firstBowl = FirstBowl.from(hitCount);

        // then
        assertThatThrownBy(() -> firstBowl.secondCount())
                .isInstanceOf(NoMoreCountingActionException.class)
                .hasMessage("현재 상태에서는 떨어진 핀의 횟수를 확인 할 수 없습니다.");
    }

}