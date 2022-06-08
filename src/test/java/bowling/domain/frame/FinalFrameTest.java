package bowling.domain.frame;

import bowling.exception.NotCreateFrameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    private Frame finalFrame;

    @BeforeEach
    void setUp() throws Exception {
        finalFrame = NormalFrame.initialize();
        for (int i = 1; i < 10; i++) {
            finalFrame.bowling(10);
            finalFrame = finalFrame.next();
        }
    }

    @Test
    @DisplayName("10 Frame 은 finalFrame 이다.")
    void finalFrame() throws Exception {
        Frame finalFrame = NormalFrame.initialize();
        for (int i = 1; i < 10; i++) {
            finalFrame.bowling(10);
            finalFrame = finalFrame.next();
        }

        assertThat(finalFrame.content().frameNo()).isEqualTo(10);
        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
    }


    @Test
    @DisplayName("첫 투구에서 스트라이크 시 프레임이 종료되지 않는다.")
    void finishFrame_strike() {
        finalFrame.bowling(10);

        assertThat(finalFrame.isFinish()).isFalse();
    }

    @Test
    @DisplayName("두번째 투구에서 스페어 시 프레임이 종료되지 않는다.")
    void finishFrame_spare() {
        finalFrame.bowling(5);
        finalFrame.bowling(5);

        assertThat(finalFrame.isFinish()).isFalse();
    }

    @Test
    @DisplayName("두번째 투구에서 MIss 시 프레임이 종료된다.")
    void finishFrame_miss() {
        finalFrame.bowling(5);
        finalFrame.bowling(3);

        assertThat(finalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 시 보너스 찬스가 두번 주어진다.")
    void hasLastBonusChance_strike() {
        finalFrame.bowling(10);

        assertThat(finalFrame.hasLastBonusChance()).isFalse();
    }

    @Test
    @DisplayName("스페어 시 보너스 찬스가 한번 주어진다.")
    void hasLastBonusChance_spare() {
        finalFrame.bowling(5);
        finalFrame.bowling(5);

        assertThat(finalFrame.hasLastBonusChance()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임일 경우 다음 프레임을 생성하면 예외를 반환한다.")
    void invalidNext() {
        assertThatThrownBy(() -> finalFrame.next())
                .isInstanceOf(NotCreateFrameException.class)
                .hasMessage("다음 프레임을 생성할 수 없습니다. 현재 프레임: 10");
    }

}