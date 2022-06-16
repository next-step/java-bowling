package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameNoTest {

    @Test
    @DisplayName("두개의 FrameNo를 비교했을때 파리미터가 더 클 경우 음수를 반환한다.")
    void compareToLower() {
        FrameNo frameNo = FrameNo.initialize();
        FrameNo upperFrameNo = frameNo.next();

        assertThat(frameNo.compareTo(upperFrameNo) < 0).isTrue();
    }

    @Test
    @DisplayName("두개의 FrameNo를 비교했을때 파리미터가 더 작은 경우 양수를 반환한다.")
    void compareToUpper() {
        FrameNo frameNo = FrameNo.initialize();
        FrameNo upperFrameNo = frameNo.next();

        assertThat(upperFrameNo.compareTo(frameNo) > 0).isTrue();
    }

    @Test
    @DisplayName("두개의 FrameNo를 비교했을때 파리미터가 같은 경우 0 을 반환한다.")
    void compareToSame() {
        FrameNo frameNo = FrameNo.initialize();
        FrameNo frameNo2 = FrameNo.initialize();

        assertThat(frameNo.compareTo(frameNo2) == 0).isTrue();
    }
}