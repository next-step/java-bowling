package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private final int FRAME_NUMBER = 0;
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame(FRAME_NUMBER);
    }

    @Test
    void 다음프레임번호_아직_안_던짐() {
        assertThat(normalFrame.getNextFrameNumber()).isEqualTo(0);
    }

    @Test
    void 다음프레임번호_이미_던진_상태() {
        normalFrame.doBowling(5);
        normalFrame.doBowling(5);
        assertThat(normalFrame.getNextFrameNumber()).isEqualTo(1);
    }

    @DisplayName("프레임이 현재 게임 가능한 상태인지 확인")
    @Test
    void nowBowlableTest() {
        assertThat(normalFrame.nowBowlable()).isTrue();
    }

    @DisplayName("상태에 따른 다음에 칠 프레임번호 출력")
    @Test
    void getNextFrameNumberTest() {
        assertThat(normalFrame.getNextFrameNumber()).isEqualTo(FRAME_NUMBER);
        normalFrame.doBowling(5);
        normalFrame.doBowling(5);
        assertThat(normalFrame.getNextFrameNumber()).isEqualTo(FRAME_NUMBER + 1);
    }
}
