package bowling.service.dto;

import bowling.domain.result.FrameResultsTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultsDtoTest {

    @Test
    void createTest() {
        FrameResultsDto dto = FrameResultsDto.of(FrameResultsTest.THREE_STRIKE);
        assertThat(dto.getScores()).isEqualTo(Arrays.asList(30));
        assertThat(dto.getFrameDescriptions()).isEqualTo(Arrays.asList("X", "X", "X"));
    }
}
