package bowling;

import bowling.domain.NormalFrames;
import bowling.domain.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NormalFrameTest {
    @Test
    @DisplayName("1~9프레임테스트")
    public void frameTest() {
        NormalFrame normalFrame = new NormalFrame("01");
        List<NormalFrame> normalFrameList = new ArrayList<>();
        normalFrameList.add(normalFrame);
        NormalFrames normalFrames = new NormalFrames(normalFrameList);
        assertThat(normalFrames.size()).isEqualTo(1);
    }
}
