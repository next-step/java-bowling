package bowling;

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
}
