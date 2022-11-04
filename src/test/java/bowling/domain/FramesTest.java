package bowling.domain;

import bowling.dto.BowlingRecord;
import bowling.dto.FrameRecord;
import bowling.domain.frame.Frames;
import bowling.domain.state.StateType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FramesTest {

    @Test
    @DisplayName("퍼펙트 게임")
    void perfect_game() {
        //given
        Frames frames = new Frames();
        //when
        IntStream.range(0, 11)
                .forEach(i -> frames.bowl(range -> new Pin(10)));
        List<FrameRecord> gameFrameRecord = BowlingRecord.of(frames, new Pin(10), new PlayerName("aaa")).getFrameRecords();
        //then
        assertAll(
                () -> assertThat(frames.isFinished()).isTrue(),
                () -> assertThat(frames.getFrameNumber()).isEqualTo(10),
                () -> assertThat(gameFrameRecord).hasSize(10),
                () -> assertThat(gameFrameRecord.get(gameFrameRecord.size()-1).getPoint()).isEqualTo(300)
        );

    }

    @Test
    @DisplayName("1번 던짐")
    void bowl_one() {
        //given
        Frames frames = new Frames();
        //when
        Pin now = frames.bowl(range -> new Pin(5));
        List<FrameRecord> gameFrameRecord = BowlingRecord.of(frames, now, new PlayerName("aaa")).getFrameRecords();
        //then
        assertAll(
                () -> assertThat(frames.isFinished()).isFalse(),
                () -> assertThat(frames.getFrameNumber()).isEqualTo(1),
                () -> assertThat(gameFrameRecord.get(0).getState()).isEqualTo(StateType.RUNNING),
                () -> assertThat(gameFrameRecord.get(0).getScores()).isEqualTo(List.of(5))
        );

    }
}