package bowling.domain.frame;

import bowling.domain.state.Finished;
import bowling.domain.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = FinalFrame.init();
    }

    @Test
    @DisplayName("FinalFrame 초기화")
    void init() {
        assertThat(finalFrame).isInstanceOf(Frame.class);
    }

    @Test
    @DisplayName("FinalFrame frameNumber(10만 허용) 예외 처리")
    void frameNumberException() {
        assertThat(finalFrame).isInstanceOf(Frame.class);
        assertThrows(IllegalArgumentException.class, () -> new FinalFrame(0, new Ready()));
        assertThrows(IllegalArgumentException.class, () -> new FinalFrame(9, new Ready()));
    }

    @Test
    @DisplayName("FinalFrame 프레임의 종료 여부 = 볼을 굴릴 수 있느냐")
    void isFinished() {
        FinalFrame finishedFrame = new FinalFrame(10, new Finished() {
            @Override
            public String print() {
                return "";
            }
        });

        assertThat(finalFrame.isFinished()).isFalse();
        assertThat(finishedFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("FinalFrame bowl & print")
    void bowlAndPrint() {
        Frame frame = FinalFrame.init();
        assertThat(frame.bowl(() -> 0).print().trim()).isEqualTo("-");                          // Gutter
        assertThat(frame.bowl(() -> 10).print().trim()).isEqualTo("X");                         // Strike
        assertThat(frame.bowl(() -> 5).print().trim()).isEqualTo("5");                          // Trying
        assertThat(frame.bowl(() -> 5).bowl(() -> 3).print()).isEqualTo("5|3");                 // Miss
        assertThat(frame.bowl(() -> 5).bowl(() -> 5).print()).isEqualTo("5|/");                 // Spare
        assertThat(frame.bowl(() -> 0).bowl(() -> 0).print()).isEqualTo("-|-");                 // Gutter

        assertThat(frame.bowl(() -> 10).bowl(() -> 0).print()).isEqualTo("X|-");                // Strike Gutter
        assertThat(frame.bowl(() -> 10).bowl(() -> 10).print()).isEqualTo("X|X");               // Strike Strike
        assertThat(frame.bowl(() -> 10).bowl(() -> 5).print()).isEqualTo("X|5");                // Strike Number
        assertThat(frame.bowl(() -> 5).bowl(() -> 5).bowl(() -> 0).print()).isEqualTo("5|/|-");         // Spare Gutter
        assertThat(frame.bowl(() -> 5).bowl(() -> 5).bowl(() -> 10).print()).isEqualTo("5|/|X");        // Spare Strike
        assertThat(frame.bowl(() -> 5).bowl(() -> 5).bowl(() -> 5).print()).isEqualTo("5|/|5");         // Spare Number
        System.out.println(frame.bowl(() -> 0).print());
        System.out.println(frame.bowl(() -> 10).print());
        System.out.println(frame.bowl(() -> 5).print());
        System.out.println(frame.bowl(() -> 5).bowl(() -> 3).print());
        System.out.println(frame.bowl(() -> 5).bowl(() -> 5).print());
        System.out.println(frame.bowl(() -> 0).bowl(() -> 0).print());
        System.out.println(frame.bowl(() -> 5).bowl(() -> 5).bowl(() -> 0).print());
        System.out.println(frame.bowl(() -> 5).bowl(() -> 5).bowl(() -> 10).print());
        System.out.println(frame.bowl(() -> 5).bowl(() -> 5).bowl(() -> 5).print());
    }

    @Test
    @DisplayName("완료된 프레임에 볼을 굴릴 경우 예외 발생")
    void bowlException() {
        FinalFrame finishedFrame = new FinalFrame(10, new Finished() {
            @Override
            public String print() {
                return "";
            }
        });

        assertThrows(IllegalStateException.class, () -> finishedFrame.bowl(() -> 1));
    }

    @Test
    @DisplayName("FinalFrame의 frameNumber는 10 이다.")
    void number() {
        assertThat(finalFrame.number()).isEqualTo(10);
    }

    @Test
    @DisplayName("FinalFrame 은 다음 프레임을 만들 수 없다. 예외 발생")
    void exceptNextFrame() {
        assertThrows(IllegalStateException.class, () -> finalFrame.next());
    }
}
