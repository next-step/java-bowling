package bowling.domain;

import bowling.domain.state.PinCount;
import bowling.exception.NoMoreBowlActionsException;
import bowling.exception.NoMoreCountingActionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @DisplayName("Frame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frame finalFrame = FinalFrame.initialize();

        // then
        assertAll(
                () -> assertThat(finalFrame).isNotNull(),
                () -> assertThat(finalFrame).isInstanceOf(FinalFrame.class)
        );
    }

    @DisplayName("Frame 인스턴스가 종료 여부를 알맞게 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(10));
        finalFrame.bowl(PinCount.valueOf(10));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThat(finalFrame.isFinish()).isTrue();
    }

    @DisplayName("Frame 인스턴스가 인덱스를 알맞게 반환하는지 테스트")
    @Test
    void 반환_인덱스() {
        // given
        int expected = 10;

        // when
        Frame finalFrame = FinalFrame.initialize();
        int actual = finalFrame.index();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Frame 인스턴스가 bowl()가 추가 투구 없이 로직을 알맞게 수행하는지 테스트")
    @Test
    void 검증_bowl_추가_투구_없음() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(0));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThatThrownBy(() -> finalFrame.bowl(PinCount.valueOf(9)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }

    @DisplayName("Frame 인스턴스가 bowl 로직을 알맞게 수행하는지 테스트")
    @Test
    void 검증_bowl_추가_투구_있음() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(0));
        finalFrame.bowl(PinCount.valueOf(10));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThatThrownBy(() -> finalFrame.bowl(PinCount.valueOf(9)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }

    @DisplayName("FinalFrame 인스턴스가 투구 횟수를 반환하는지 테스트(추가 투구 없음)")
    @Test
    void 반환_사이즈_추가_투구_없음() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(0));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThat(finalFrame.size()).isEqualTo(2);
    }

    @DisplayName("FinalFrame 인스턴스가 투구 횟수를 반환하는지 테스트(추가 투구 있음)")
    @Test
    void 반환_사이즈_추가_투구_있음() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(0));
        finalFrame.bowl(PinCount.valueOf(10));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThat(finalFrame.size()).isEqualTo(3);
    }

    @DisplayName("FinalFrame 인스턴스가 첫번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_첫번째_투구_값() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(0));
        finalFrame.bowl(PinCount.valueOf(10));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThat(finalFrame.firstCount()).isEqualTo(0);
    }

    @DisplayName("FinalFrame 인스턴스가 두번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_두번째_투구_값() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(0));
        finalFrame.bowl(PinCount.valueOf(10));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThat(finalFrame.secondCount()).isEqualTo(10);
    }

    @DisplayName("FinalFrame 인스턴스가 세번째 투구 값을 반환하는지 테스트")
    @Test
    void 반환_세번째_투구_값() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(0));
        finalFrame.bowl(PinCount.valueOf(10));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThat(finalFrame.thirdCount()).isEqualTo(9);
    }

    @DisplayName("FinalFrame 인스턴스가 세번째 투구 값을 반환시 예외처리 여부 테스트(보너스_없음)")
    @Test
    void 검증_세번째_투구_값_보너스_없음() {
        // given
        Frame finalFrame = FinalFrame.initialize();

        // when
        finalFrame.bowl(PinCount.valueOf(0));
        finalFrame.bowl(PinCount.valueOf(9));

        // then
        assertThatThrownBy(() -> finalFrame.thirdCount())
                .isInstanceOf(NoMoreCountingActionException.class)
                .hasMessage("현재 상태에서는 떨어진 핀의 횟수를 확인 할 수 없습니다.");
    }
}