package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FramesTest {

    @Test
    @DisplayName("Frame 추가")
    void add_frame() {
        // given
        Frames frames = new Frames();
        // when
        frames.pitch(8);
        // then
        assertThat(frames.frames().get(0)).isEqualTo(Frame.of(8));
    }

    @Test
    @DisplayName("MaxFrame 초과 검증")
    void validate_max_number_of_frames() {
        // given
        Frames frames = new Frames();
        // when
        for (int i = 0; i < 10; i++) {
            frames.pitch(7);
            frames.pitch(2);
        }
        // then
        assertThatThrownBy(() -> frames.pitch(7))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("마지막 Frame strike 시 bonus Frame 추가 투구")
    void pass_perfect_game() {
        // given
        Frames frames = new Frames();
        // when
        for (int i = 0; i < 12; i++) {
            frames.pitch(10);
        }

        // then
        assertThat(frames.frames().get(9).score()).isEqualTo(30);
    }

    @Test
    @DisplayName("다음 Pitching Frame Number 반환")
    void next_pitching_frame_number() {
        // given
        Frames frames = new Frames();
        // when
        for (int i = 0; i < 3; i++) {
            frames.pitch(7);
            frames.pitch(2);
        }
        // then
        assertThat(frames.nextPitchingFrameNumber()).isEqualTo(4);
    }

    @Test
    @DisplayName("다음 Pitching Frame Number 반환 : strike")
    void next_pitching_frame_number_strike() {
        // given
        Frames frames = new Frames();
        // when
        for (int i = 0; i < 3; i++) {
            frames.pitch(10);
        }
        // then
        assertThat(frames.nextPitchingFrameNumber()).isEqualTo(4);
    }
}
