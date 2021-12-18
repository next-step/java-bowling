package bowling.service.dto;

import bowling.domain.result.FrameResultTest;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultDtoTest {

    @Test
    void normalFrameTest() {
        FrameResultDto dto = FrameResultDto.of(FrameResultTest.STRIKE_RESULT);
        assertThat(dto.getStates()).isEqualTo(Arrays.asList(new Strike()));
        assertThat(dto.getScore()).isEqualTo(-1);
    }
}
