package bowling.service.dto;

import bowling.domain.result.FrameResultsTest;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultsDtoTest {

    @Test
    void createTest() {
        FrameResultsDto dto = FrameResultsDto.of(FrameResultsTest.THREE_STRIKE);
        List<FrameResultDto> frameResultDtos = dto.getFrameResultDtos();

        assertThat(frameResultDtos.get(0).getScore()).isEqualTo(30);
        assertThat(frameResultDtos.get(0).getStates()).isEqualTo(Arrays.asList(new Strike()));
        assertThat(frameResultDtos.get(1).getScore()).isEqualTo(-1);
        assertThat(frameResultDtos.get(1).getStates()).isEqualTo(Arrays.asList(new Strike()));
        assertThat(frameResultDtos.get(2).getScore()).isEqualTo(-1);
        assertThat(frameResultDtos.get(2).getStates()).isEqualTo(Arrays.asList(new Strike()));
    }
}
