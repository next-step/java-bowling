package bowling.domain.frame;

import bowling.domain.state.Finished;
import bowling.domain.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalFrameTest {
    private Frame normalFrame;
    private Frame finishedFrame;

    @BeforeEach
    void setUp() {
        normalFrame = NormalFrame.init(1);
        finishedFrame = new NormalFrame(1, new Finished() {
            @Override
            public String print() {
                return null;
            }
        });
    }

    @Test
    @DisplayName("NormalFrame 초기화")
    void init() {
        assertThat(normalFrame).isInstanceOf(Frame.class);
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
        assertThat(normalFrame.isFinished()).isFalse();
        assertThat(finishedFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("NormalFrame bowl & print")
    void bowlAndPrint() {
        assertThat(normalFrame.bowl(() -> 0).print().trim()).isEqualTo("-");                // Gutter
        assertThat(normalFrame.bowl(() -> 10).print().trim()).isEqualTo("X");               // Strike
        assertThat(normalFrame.bowl(() -> 5).print().trim()).isEqualTo("5");                // Trying
        assertThat(normalFrame.bowl(() -> 5).bowl(() -> 3).print()).isEqualTo("5|3");        // Miss
        assertThat(normalFrame.bowl(() -> 5).bowl(() -> 5).print()).isEqualTo("5|/");        // Spare
        assertThat(normalFrame.bowl(() -> 0).bowl(() -> 0).print()).isEqualTo("-|-");        // Gutter
    }

    @Test
    @DisplayName("완료된 프레임에 볼을 굴릴 경우 예외 발생 ")
    void bowlException() {
        assertThrows(IllegalStateException.class, () -> finishedFrame.bowl(() -> 1));
    }

    @ParameterizedTest
    @DisplayName("NormalFrame 프레임 숫자 체크")
    @ValueSource(ints = {1, 5, 9})
    void number(final int number) {
        assertThat(NormalFrame.init(number).number()).isEqualTo(number);
    }

    @ParameterizedTest
    @DisplayName("NormalFrame 에서 프레임이 끝났다면 다음 프레임을 만들 수 있다.")
    @ValueSource(ints = {1, 5, 8})
    void nextFrame(final int number) {
        NormalFrame finishedFrame = new NormalFrame(number, new Finished() {
            @Override
            public String print() {
                return null;
            }
        });
        assertThat(finishedFrame.next()).isEqualTo(NormalFrame.init(number + 1));
    }

    @ParameterizedTest
    @DisplayName("NormalFrame 에서 현재 프레임이 끝나지 않았다면 다음 프레임을 만들 수 없다.")
    @ValueSource(ints = {1, 5, 8})
    void exceptNextFrame(final int number) {
        assertThrows(IllegalStateException.class, () -> NormalFrame.init(number).next());
    }

    @Test
    @DisplayName("NormalFrame 9 프레임을 다음 프레임은 FinalFrame이다.")
    void exceptNextFrame() {
        NormalFrame finishedFrame = new NormalFrame(9, new Finished() {
            @Override
            public String print() {
                return null;
            }
        });
        assertThat(finishedFrame.next() instanceof FinalFrame).isTrue();
    }
}
