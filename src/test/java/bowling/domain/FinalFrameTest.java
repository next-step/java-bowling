package bowling.domain;

import bowling.exception.NoMoreBowlActionsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @DisplayName("Frame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        Frame finalFrame = FinalFrame.newInstance();

        assertAll(
                () -> assertThat(finalFrame).isNotNull(),
                () -> assertThat(finalFrame).isInstanceOf(FinalFrame.class)
        );
    }

//    @DisplayName("Frame 인스턴스가 종료 여부를 알맞게 반환하는지 테스트")
//    @Test
//    void 반환_종료_여부() {
//        // given
//        Frame finalFrame = FinalFrame.newInstance();
//
//        assertAll(
//                () -> assertThat(finalFrame).isNotNull(),
//                () -> assertThat(finalFrame).isInstanceOf(FinalFrame.class)
//        );
//    }

    @DisplayName("Frame 인스턴스가 bowl 로직을 알맞게 수행하는지 테스트")
    @Test
    void 검증_bowl() {
        Frame finalFrame = FinalFrame.newInstance();

        finalFrame.bowl(HitCount.valueOf(0));
        finalFrame.bowl(HitCount.valueOf(10));
        finalFrame.bowl(HitCount.valueOf(10));

        assertThatThrownBy(() -> finalFrame.bowl(HitCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }
}