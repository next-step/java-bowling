package bowling.domain;

import bowling.exception.NoMoreBowlActionsException;
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

    @DisplayName("FakeFrame 인스턴스가 볼링 메서드 실행시 예외처리 여부 테스트")
    @Test
    void 반환() {
        // when
        Frame fakeFrame = FakeFrame.initialize();

        // then
        assertThatThrownBy(() -> assertThat(fakeFrame.bowl(HitCount.valueOf(10))))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }
}