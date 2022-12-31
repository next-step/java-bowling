package bowling.domain.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultsDtoTest {

    @Test
    void frame_최대개수만큼_생성() {
        assertThat(new FrameResultsDto(List.of("X")).results()).hasSize(10);
        assertThat(new FrameResultsDto(List.of("X", "X", "X")).results()).hasSize(10);
    }
}