package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    protected final int FIRST_POINT = 2;
    protected final int SECOND_POINT = 3;
    protected final int THIRD_POINT = 4;
    protected final int SPARE_POINT = 8;
    protected final int STRIKE_POINT = 10;
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame();
    }

    @Test
    void Miss_일때_점수_계산() {
        frame.doBowling(FIRST_POINT);
        frame.doBowling(SECOND_POINT);
        assertThat(frame.getScore()).isEqualTo(FIRST_POINT + SECOND_POINT);
    }

    @Test
    void Spare_일때_점수_계산() {
        frame.doBowling(FIRST_POINT);
        frame.doBowling(SPARE_POINT);
        assertThat(frame.getScore()).isEqualTo(FIRST_POINT + SPARE_POINT);
        frame.doBowling(THIRD_POINT);
        assertThat(frame.getScore()).isEqualTo(FIRST_POINT + SPARE_POINT + THIRD_POINT);
    }

    @Test
    void Strike_3연속_일때_점수_계산() {
        frame.doBowling(STRIKE_POINT);
        assertThat(frame.getScore()).isEqualTo(STRIKE_POINT);

        Frame nextFrame = frame.doBowling(STRIKE_POINT);
        nextFrame.doBowling(STRIKE_POINT);

        assertThat(frame.getScore()).isEqualTo(STRIKE_POINT * 3);
    }

    @Test
    void _10번_째_프레임_Miss() {
        frame.doBowling(FIRST_POINT);
        frame.doBowling(SPARE_POINT);
        frame.doBowling(THIRD_POINT);
        assertThat(frame.getScore()).isEqualTo(FIRST_POINT + SPARE_POINT + THIRD_POINT);
    }
}
