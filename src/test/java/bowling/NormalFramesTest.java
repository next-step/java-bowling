package bowling;

import bowling.domain.Frame;
import bowling.domain.NormalFrames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFramesTest {

    private NormalFrames normalFrames;

    @BeforeEach
    void setUp() {
        normalFrames = NormalFrames.of(new ArrayList<>());
    }

    @Test
    @DisplayName("일반 프레임 테스트")
    void checkNormalFrame() {
        normalFrames.next(5);
        normalFrames.next(4);
        normalFrames.next(3);
        assertThat(normalFrames.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("다음 프레임 넘어가기전 현재 프레임 체크")
    void checkCurrentFrameIndex() {
        normalFrames.next(5);
        normalFrames.next(4);
        normalFrames.next(3);
        assertThat(normalFrames.getCurrentFrameNumber()).isEqualTo(1);
        // 현재 프레임 종료
        normalFrames.next(2);
        assertThat(normalFrames.getCurrentFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("점수 계산을 위한 현재 프레임 체크")
    void checkCurrentFrameIndexForScoreCalculate() {
        NormalFrames normalFrames = NormalFrames.of(new ArrayList<>());
        normalFrames.next(10);
        assertThat(normalFrames.getCurrentFrameNumber()).isEqualTo(1);

        NormalFrames normalFrames1 = NormalFrames.of(new ArrayList<>());
        normalFrames1.next(5);
        assertThat(normalFrames1.getCurrentFrameNumber()).isEqualTo(0);

        NormalFrames normalFrames2 = NormalFrames.of(new ArrayList<>());
        normalFrames2.next(3);
        normalFrames2.next(4);
        assertThat(normalFrames2.getCurrentFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("현재 점수 계산")
    void calculateByCurrentFrame() {
        normalFrames.next(5);
        normalFrames.next(4);
        normalFrames.next(3);
        assertThat(normalFrames.getScore()).isEqualTo(12);
    }
}