package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameStatusesTests {
    @DisplayName("FrameStatus 리스트를 전달받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        List<FrameStatus> frameStatusList = Arrays.asList(FrameStatus.ONE, FrameStatus.TWO);
        assertThat(new FrameStatuses(frameStatusList)).isEqualTo(new FrameStatuses(frameStatusList));
    }
}
