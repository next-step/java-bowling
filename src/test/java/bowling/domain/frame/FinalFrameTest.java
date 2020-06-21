/*
package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @DisplayName("스트라이크를 치면 2번 더 칠 수 있도록 현재 Frame 반환")
    @Test
    public void nextFinalFrame_스트라이크_보너스() {
        Frame finalFrame = FinalFrame.last(10);

        finalFrame.bowl(10);

        assertThat(finalFrame.next()).isEqualTo(finalFrame);

        finalFrame.bowl(10);

        assertThat(finalFrame.next()).isEqualTo(finalFrame);

        finalFrame.bowl(10);

        assertThat(finalFrame.next()).isEqualTo(null);
    }

    */
/*
    FinalPitch의 종료 조건
    1) 2번 투구시 미스인 경우 -> 종료
    2) 스트라이크 인 경우 -> 2번 더 칠 수 있음. (그 2번이 스트라이크여도됨.)
    3) 스페어인 경우 -> 1번 더 칠 수 있음. (그 1번은 스트라이크여도됨.)
     *//*


    @DisplayName("스페어의 경우 1번 더 칠 수 있도록 현재 Frame 반환")
    @Test
    public void nextFinalFrame_스페어() {
        Frame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(2);
        finalFrame.bowl(8);

        assertThat(finalFrame.next()).isEqualTo(finalFrame);
    }

    @DisplayName("2번 투구시 10점 미만의 Miss인 경우 null을 반환하여 경기가 종료된다")
    @Test
    public void nextFinalFrame_경기종료() {
        Frame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(2);
        finalFrame.bowl(4);

        assertThat(finalFrame.next()).isEqualTo(null);
    }
}
*/
