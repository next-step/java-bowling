package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest  {

    @Test
    @DisplayName("1~8까지 한 프레임 끝나면 다음 프레임 반환 테스트")
    void whenNotFinishedReturnSameFrameTest(){
        NormalFrame normalFrame = new NormalFrame(1);
        Frame returnFrame = normalFrame.bowl(5);
        assertThat(returnFrame).isSameAs(normalFrame);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,8})
    @DisplayName("1~8까지 한 프레임 끝나면 다음 프레임 반환 테스트")
    void whenFinishedReturnNextNormalFrameTest(int frameNo){
        NormalFrame normalFrame = new NormalFrame(frameNo);
        Frame returnFrame = normalFrame.bowl(10);
        assertThat(returnFrame.getFrameNo()).isEqualTo(frameNo + 1);
        assertTrue(returnFrame.isNormalFrame());
    }

    @Test
    @DisplayName("9프레임까지 끝나면 FinalFrame 반환 테스트")
    void afterNineFrameFinishReturnFinalFrameTest(){
        NormalFrame normalFrame = new NormalFrame(9);
        Frame returnFrame = normalFrame.bowl(10);
        assertTrue(!returnFrame.isNormalFrame());
    }

}
