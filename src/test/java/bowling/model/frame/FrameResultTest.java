package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameResultTest {

    @Test
    @DisplayName("결과 값이 항상 최대 프레임 수와 일치")
    void from() {
        List<String> testResults = new LinkedList<>();
        testResults.add("X");
        FrameResult frameResult = FrameResult.from(testResults);

        assertThat(frameResult.stream().count()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
    }

    @Test
    @DisplayName("빈 결과 생성")
    void empty() {
        assertThat(FrameResult.empty.stream().count()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);

        boolean allDummyResult = FrameResult.empty.stream()
                .map(result -> result.length() == 0)
                .reduce((x,y) -> x && y)
                .orElse(false);

        assertThat(allDummyResult).isTrue();
    }
}