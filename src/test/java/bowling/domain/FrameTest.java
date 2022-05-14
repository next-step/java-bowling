package bowling.domain;

import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {
    private final Frame given_frame_2 = new Frame();
    private final Frame given_frame_3 = new Frame();
    private final Record given_record = new Record();

    @Test
    void 게임_실행_정상() {
        assertThat(given_frame_2.play(2, 3)).isEqualTo(7);
        assertThat(given_frame_2.play(2, 6)).isEqualTo(1);

        assertThat(given_frame_3.play(3, 9)).isEqualTo(1);
        assertThat(given_frame_3.play(3, 0)).isEqualTo(1);
    }

    @Test
    void 게임_실행_비정상_핀갯수_초과() {
        assertThatThrownBy(() -> given_frame_2.play(2, 11))
                .isInstanceOf(InvalidNumberOfFallenPinsException.class);
    }

    @Test
    void 게임_실행_비정상_게임_종료() {
        assertThatThrownBy(() -> {
            given_frame_2.play(2, 9);
            given_frame_2.play(2, 0);
            given_frame_2.play(2, 1);
        }).isInstanceOf(EndedFrameException.class);
    }

    @Test
    void 게임_기록_저장() {
        assertThat(given_frame_2.play(2, 3)).isEqualTo(7);
        given_frame_2.record(given_record);
        assertThat(given_record.getRecords().get(0)).isEqualTo(3);

        assertThat(given_frame_2.play(2, 6)).isEqualTo(1);
        given_frame_2.record(given_record);
        assertThat(given_record.getRecords().get(1)).isEqualTo(9);
    }
}