package bowling;

import bowling.entity.frame.NormalFrameInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NormalFrameInfoTest {
    @Test
    @DisplayName("마지막 일반 프레임")
    public void createStrikeFrame(){
        int frameNo = 9;

        NormalFrameInfo normalFrameInfo = new NormalFrameInfo(frameNo);

        assertThat(normalFrameInfo.nextFrameIsLastFrame()).isTrue();
    }

}
