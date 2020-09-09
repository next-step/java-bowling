package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    public static final int STRIKE = 10;
    public static final int GUTTER = 0;

    @Test
    void from() {
        assertThat(Frame.from(Frame.BEGIN_STAGE)).isNotNull();
    }

    @Test
    void fromThrowException() {
        assertThatThrownBy(() -> Frame.from(Frame.BEGIN_STAGE - 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Frame.from(Frame.END_STAGE + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void hasNextTurn() {
        Frame frame = Frame.from(Frame.BEGIN_STAGE);
        int count = 0;

        while (frame.hasNextTurn()) {
            frame.record(GUTTER);
            count++;
        }

        assertThat(count).isEqualTo(2);
    }

    @Test
    void hasNextTurnWithBonusStep() {
        Frame frame = Frame.from(Frame.END_STAGE);
        int count = 0;

        while (frame.hasNextTurn()) {
            frame.record(count == 0 ? GUTTER : STRIKE);
            count++;
        }

        assertThat(count).isEqualTo(3);
    }

    @Test
    void record() {
        assertThat(Frame.from(Frame.END_STAGE).record(GUTTER)).isInstanceOf(Frame.class);
        assertThat(Frame.from(Frame.END_STAGE).record(1)).isInstanceOf(Frame.class);
    }

    @Test
    void getStage() {
        Frame frame = Frame.from(Frame.BEGIN_STAGE);

        assertThat(frame.getStage()).isEqualTo(1);
        assertThat(frame.record(GUTTER).getStage()).isEqualTo(1);
    }

    @Test
    void getResults() {
        Frame frame = Frame.from(Frame.BEGIN_STAGE);

        frame.record(GUTTER);
        frame.record(STRIKE);

        assertThat(frame.getResults().getHistory().size()).isEqualTo(2);
    }
}
