package bowling.domain;

import bowling.domain.state.PinCount;
import bowling.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FakeFrameTest {

    @DisplayName("FakeFrame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertAll(
                () -> assertThat(fakeFrame).isNotNull(),
                () -> assertThat(fakeFrame).isInstanceOf(FakeFrame.class)
        );
    }

    @DisplayName("FakeFrame 인스턴스가 사이즈 반환시 예외처리 여부 테스트")
    @Test
    void 검증_크기() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertThatThrownBy(() -> assertThat(fakeFrame.size()))
                .isInstanceOf(NoMoreGetSizeActionsException.class)
                .hasMessage("현재 상태에서는 사이즈 값을 알 수 없습니다.");
    }

    @DisplayName("FakeFrame 인스턴스가 볼링 메서드 실행시 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertThatThrownBy(() -> assertThat(fakeFrame.bowl(PinCount.valueOf(10))))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }

    @DisplayName("FakeFrame 인스턴스가 isFinish 실행시 예외처리 여부 테스트")
    @Test
    void 검증_종료_여부() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertThatThrownBy(() -> assertThat(fakeFrame.isFinish()))
                .isInstanceOf(NoMoreFinishActionsException.class)
                .hasMessage("현재 상태에서는 프레임이 완료되었는지 확인 할 수 없습니다.");
    }

    @DisplayName("FakeFrame 인스턴스가 index 실행시 예외처리 여부 테스트")
    @Test
    void 검증_현재_인덱스() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertThatThrownBy(() -> assertThat(fakeFrame.index()))
                .isInstanceOf(NoMoreIndexActionsException.class)
                .hasMessage("현재 상태에서는 인덱스를 확인 할 수 없습니다.");
    }


    @DisplayName("FakeFrame 인스턴스가 첫번째 투구 값을 반환시 예외처리 여부 테스트")
    @Test
    void 검증_첫번째_투구_값() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertThatThrownBy(() -> fakeFrame.firstCount())
                .isInstanceOf(NoMoreCountingActionException.class)
                .hasMessage("현재 상태에서는 떨어진 핀의 횟수를 확인 할 수 없습니다.");
    }

    @DisplayName("FakeFrame 인스턴스가 두번째 투구 값을 반환시 예외처리 여부 테스트")
    @Test
    void 검증_두번째_투구_값() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertThatThrownBy(() -> fakeFrame.secondCount())
                .isInstanceOf(NoMoreCountingActionException.class)
                .hasMessage("현재 상태에서는 떨어진 핀의 횟수를 확인 할 수 없습니다.");
    }

    @DisplayName("FakeFrame 인스턴스가 세번째 투구 값을 반환시 예외처리 여부 테스트")
    @Test
    void 검증_세번째_투구_값() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertThatThrownBy(() -> fakeFrame.thirdCount())
                .isInstanceOf(NoMoreCountingActionException.class)
                .hasMessage("현재 상태에서는 떨어진 핀의 횟수를 확인 할 수 없습니다.");
    }


}