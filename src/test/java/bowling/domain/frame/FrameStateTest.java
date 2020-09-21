package bowling.domain.frame;

import bowling.domain.roll.Roll;
import bowling.domain.roll.RollRecord;
import bowling.domain.roll.RollType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStateTest {

    @Test
    @DisplayName("스트라이크 프레임")
    void testFrameStateStrike() {
        // given
        RollRecord record = new RollRecord();
        record.add(new Roll(RollType.STRIKE, 10));
        // when
        FrameState frameState = FrameState.valueOf(record);
        // then
        assertThat(frameState).isEqualTo(FrameState.STRIKE);
    }

    @Test
    @DisplayName("오픈 프레임")
    void testFrameStateOpen() {
        // given
        RollRecord record = new RollRecord();
        record.add(new Roll(RollType.DEFAULT, 2));
        record.add(new Roll(RollType.DEFAULT, 4));
        // when
        FrameState frameState = FrameState.valueOf(record);
        // then
        assertThat(frameState).isEqualTo(FrameState.OPEN);
    }

    @Test
    @DisplayName("스페어 프레임")
    void testFrameStateSpare() {
        // given
        RollRecord record = new RollRecord();
        record.add(new Roll(RollType.DEFAULT, 2));
        record.add(new Roll(RollType.SPARE, 8));
        // when
        FrameState frameState = FrameState.valueOf(record);
        // then
        assertThat(frameState).isEqualTo(FrameState.SPARE);
    }
}
