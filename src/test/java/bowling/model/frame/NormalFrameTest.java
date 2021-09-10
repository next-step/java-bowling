package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("마지막 프레임을 제외한 볼링 프레임 테스트")
public class NormalFrameTest {

    @DisplayName("프레임 번호가 10 이상이면 예외가 발생한다.")
    @Test
    void outOfRangeNormalFrameNumberTest() {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new NormalFrame(10, 5, 5, 10, 1))
                .withMessage("노말 프레임 번호는 1 이상 9 이하 이어야 합니다.");
    }

    @DisplayName("현재 프레임 번호가 9이고 스트라이크거나 두 번 플레이를 모두 마친 상태면, 다음 프레임은 FinalFrame 이다.")
    @Test
    void nextFinalFrameTest() {
        // given
        Frame strikeFrame = new NormalFrame(9, 10, 10, 2);
        Frame pitchTwiceFrame = new NormalFrame(9, 5, 5, 10, 1);

        // when, then
        assertThat(strikeFrame.next(5)).isInstanceOf(FinalFrame.class);
        assertThat(pitchTwiceFrame.next(5)).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("현재 프레임 번호가 9가 아니거나 9이면서 스트라이크거나 두 번 플레이를 모두 마친 상태가 아니라면, 다음 프레임은 NormalFrame 이다.")
    @Test
    void nextNormalFrameTest() {
        // given
        List<Frame> frames = new ArrayList<>();
        frames.add(new NormalFrame(1, 10, 10, 2)); // 1번 프레임 && 스트라이크
        frames.add(new NormalFrame(1, 5, 5, 0)); // 1번 프레임 && 공을 한 번 던짐
        frames.add(new NormalFrame(1, 5, 3, 8, 0)); // 1번 프레임 && 공을 두 번 던짐
        frames.add(new NormalFrame(8, 10, 10, 2)); // 8번 프레임 && 스트라이크
        frames.add(new NormalFrame(8, 5, 5, 0)); // 8번 프레임 && 공을 한 번 던짐
        frames.add(new NormalFrame(8, 5, 3, 8, 0)); // 8번 프레임 && 공을 두 번 던짐
        frames.add(new NormalFrame(9, 5, 5, 0)); // 9번 프레임 && 공을 한 번 던짐

        // when, then
        for (Frame frame : frames) {
            assertThat(frame.next(5)).isInstanceOf(NormalFrame.class);
        }
    }
}
