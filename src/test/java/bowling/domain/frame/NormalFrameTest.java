package bowling.domain.frame;

import bowling.domain.state.Finished;
import bowling.domain.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalFrameTest {

    @Test
    @DisplayName("NormalFrame 초기화")
    void init() {
        assertThat(NormalFrame.init(1)).isInstanceOf(Frame.class);
    }

    @Test
    @DisplayName("NormalFrame frameNumber(1~9 허용) 예외 처리")
    void frameNumberException() {
        assertThat(NormalFrame.init(1)).isInstanceOf(Frame.class);
        assertThat(NormalFrame.init(9)).isInstanceOf(Frame.class);
        assertThrows(IllegalArgumentException.class, () -> new NormalFrame(0, new Ready()));
        assertThrows(IllegalArgumentException.class, () -> new NormalFrame(10, new Ready()));
    }

    @Test
    @DisplayName("NormalFrame 프레임의 종료 여부 = 볼을 굴릴 수 있느냐")
    void isFinished() {
        NormalFrame readyFrame = new NormalFrame(1, new Ready());
        NormalFrame finishedFrame = new NormalFrame(2, new Finished() {
            @Override
            public String print() {
                return "";
            }
        });

        assertThat(readyFrame.isFinished()).isFalse();
        assertThat(finishedFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("NormalFrame bowl & print")
    void bowlAndPrint() {
        Frame frame = NormalFrame.init(1);
        assertThat(frame.bowl(() -> 0).print().trim()).isEqualTo("-");                // Gutter
        assertThat(frame.bowl(() -> 10).print().trim()).isEqualTo("X");               // Strike
        assertThat(frame.bowl(() -> 5).print().trim()).isEqualTo("5");                // Trying
        assertThat(frame.bowl(() -> 5).bowl(() -> 3).print()).isEqualTo("5|3");        // Miss
        assertThat(frame.bowl(() -> 5).bowl(() -> 5).print()).isEqualTo("5|/");        // Spare
        assertThat(frame.bowl(() -> 0).bowl(() -> 0).print()).isEqualTo("-|-");        // Gutter
    }

    @Test
    @DisplayName("완료된 프레임에 볼을 굴릴 경우 예외 발생 ")
    void bowlException() {
        NormalFrame finishedFrame = new NormalFrame(1, new Finished() {
            @Override
            public String print() {
                return "";
            }
        });

        assertThrows(IllegalStateException.class, () -> finishedFrame.bowl(() -> 1));
    }
}
