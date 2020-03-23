package bowling.domain.framestatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class FrameManagementTest {

    @Test
    @DisplayName("스트라이크 프레임 상태 확인")
    void checkFrameStatus() {
        FrameManagement strikeFrameManagement = new FrameManagement(10);
        FrameStatus frameStatus = strikeFrameManagement.getFrameStatus();
        assertThat(frameStatus).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("스페어 프레임 상태 확인")
    void checkSpareFrameStatus() {
        FrameManagement spareFrameManagement = new FrameManagement(8, 2);
        FrameStatus frameStatus = spareFrameManagement.getFrameStatus();
        assertThat(frameStatus).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("거터 프레임 상태 확인")
    void checkGutterFrameStatus() {
        FrameManagement gutterFrameManagement = new FrameManagement(0);
        FrameStatus frameStatus = gutterFrameManagement.getFrameStatus();
        assertThat(frameStatus).isInstanceOf(Gutter.class);

        FrameManagement gutterFrameManagement2 = new FrameManagement(0, 0);
        FrameStatus frameStatus2 = gutterFrameManagement2.getFrameStatus();
        assertThat(frameStatus2).isInstanceOf(Gutter.class);
    }

    @Test
    @DisplayName("미스 프레임 상태 확인")
    void checkMissFrameStatus() {
        FrameManagement missFrameManagement = new FrameManagement(1);
        FrameStatus frameStatus = missFrameManagement.getFrameStatus();
        assertThat(frameStatus).isInstanceOf(Miss.class);

        FrameManagement missFrameManagement2 = new FrameManagement(1, 3);
        FrameStatus frameStatus2 = missFrameManagement2.getFrameStatus();
        assertThat(frameStatus2).isInstanceOf(Miss.class);
    }
}
