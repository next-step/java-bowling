package bowling.service.dto;

import bowling.domain.frame.Pin;
import bowling.domain.result.FrameResultTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultDtoTest {

    @Test
    void normalFrameTest() {
        FrameResultDto dto = FrameResultDto.of(FrameResultTest.STRIKE_RESULT);
        assertThat(dto.getPins()).isEqualTo(Arrays.asList(Pin.TEN));
        assertThat(dto.getScore()).isEqualTo(-1);
    }
}
