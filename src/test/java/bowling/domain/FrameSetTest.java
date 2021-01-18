package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FrameSetTest {

    @DisplayName("프래임 셋의 모든 프래임을 mark 하면 프래임 셋은 끝난다")
    @Test
    void isEnd(){
        FrameSet frameSet = FrameSet.of(Arrays.asList(FrameFactory.createFirstFrame(), FrameFactory.createFirstFrame()));
        frameSet.mark( (frame) -> 5 );
        assertThat(frameSet.isEnd()).isTrue();
    }

}