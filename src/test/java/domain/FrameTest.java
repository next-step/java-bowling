package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.Bowling.TOTAL_FRAME_COUNT;
import static domain.Frame.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    private final int FIRST_POINT = 2;
    private final int SECOND_POINT = 3;
    private final int THIRD_POINT = 4;
    private final int SPARE_POINT = 8;
    private final int STRIKE_POINT = 10;
    private Frame frame;

    @BeforeEach
    void setUp() {
        new FrameCounter(ZERO);
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
        Frame nextFrame = frame.createNext();
        nextFrame.doBowling(THIRD_POINT);
        assertThat(frame.getScore()).isEqualTo(FIRST_POINT + SPARE_POINT + THIRD_POINT);
    }

    @Test
    void Strike_3연속_일때_점수_계산() {
        frame.doBowling(STRIKE_POINT);
        Frame nextFrame = frame.createNext();

        nextFrame.doBowling(STRIKE_POINT);
        nextFrame = nextFrame.createNext();

        nextFrame.doBowling(STRIKE_POINT);

        assertThat(frame.getScore()).isEqualTo(STRIKE_POINT * 3);
    }

    @Test
    void _10번_째_프레임_Miss() {
        new FrameCounter(TOTAL_FRAME_COUNT);
        frame.doBowling(FIRST_POINT);
        frame.doBowling(SPARE_POINT);
        frame.doBowling(THIRD_POINT);
        assertThat(frame.getScore()).isEqualTo(FIRST_POINT + SPARE_POINT + THIRD_POINT);
    }

    @DisplayName("넘어뜨린 핀 개수 포맷되어 출력")
    @Test
    void getPointTest() {
        frame.doBowling(FIRST_POINT);
        frame.doBowling(SECOND_POINT);
        assertThat(frame.getPoint()).isEqualTo(String.format("%-4s", FIRST_POINT + "|" + SECOND_POINT));
    }

    @DisplayName("프레임 점수 출력")
    @Test
    void getScoreTest() {
        frame.doBowling(FIRST_POINT);
        frame.doBowling(SECOND_POINT);
        assertThat(frame.getScore()).isEqualTo(FIRST_POINT + SECOND_POINT);
    }

    @DisplayName("프레임 종료여부 확인")
    @Test
    void isFrameEndTest() {
        frame.doBowling(FIRST_POINT);
        assertThat(frame.isFrameEnd()).isFalse();
        frame.doBowling(SECOND_POINT);
        assertThat(frame.isFrameEnd()).isTrue();
    }

    @DisplayName("마지막 프레임 종료 여부 확인")
    @Test
    void isFinalFrameTest() {
        assertThat(frame.isFinalFrame()).isFalse();
        new FrameCounter(TOTAL_FRAME_COUNT);
        frame.doBowling(FIRST_POINT);
        frame.doBowling(SECOND_POINT);
        assertThat(frame.isFinalFrame()).isTrue();
    }

    @DisplayName("다음 프레임 생성 확인")
    @Test
    void createNextTest() {
        assertThat(FrameCounter.getFrameCounter()).isEqualTo(1);
        frame.createNext();
        assertThat(FrameCounter.getFrameCounter()).isEqualTo(2);

    }
}
