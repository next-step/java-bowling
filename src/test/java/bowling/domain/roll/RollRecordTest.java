package bowling.domain.roll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RollRecordTest {

    @Test
    @DisplayName("스트라이크")
    void testRollRecordStrike() {
        // given
        RollRecord record = new RollRecord();
        // when
        record.add(new Roll(RollType.STRIKE, 10));
        // then
        assertRecord(record, true, false, false, false, "X");
    }

    @Test
    @DisplayName("스페어")
    void testRollRecordSpare() {
        // given
        RollRecord record = new RollRecord();
        // when
        record.add(new Roll(RollType.DEFAULT, 5));
        record.add(new Roll(RollType.SPARE, 5));
        // then
        assertRecord(record, false, true, true, false, "5|/");
    }

    @Test
    @DisplayName("스페어 실패")
    void testRollRecordSpareFailOpen() {
        // given
        RollRecord record = new RollRecord();
        // when
        record.add(new Roll(RollType.DEFAULT, 5));
        record.add(new Roll(RollType.DEFAULT, 1));
        // then
        assertRecord(record, false, false, true, false, "5|1");
    }

    @Test
    @DisplayName("스페어 실패 (거터)")
    void testRollRecordSpareFailGutter() {
        // given
        RollRecord record = new RollRecord();
        // when
        record.add(new Roll(RollType.DEFAULT, 5));
        record.add(new Roll(RollType.GUTTER, 0));
        // then
        assertRecord(record, false, false, true, false, "5|-");
    }

    @Test
    @DisplayName("10 프레임은 프레임결과를 두지 않음 (마지막이기때문)")
    void testRollRecordFinalFrame() {
        // given
        RollRecord record = new RollRecord();
        // when
        record.add(new Roll(RollType.DEFAULT, 5));
        record.add(new Roll(RollType.SPARE, 5));
        record.add(new Roll(RollType.DEFAULT, 1));
        // then
        assertRecord(record, false, false, false, true, "5|/|1");
    }

    private void assertRecord(RollRecord record,
                              boolean isStrike,
                              boolean isSpare,
                              boolean isRollTwice,
                              boolean isRollThreeTimes,
                              String marking) {
        assertThat(record.isStrike()).isEqualTo(isStrike);
        assertThat(record.isSpare()).isEqualTo(isSpare);
        assertThat(record.isRollTwice()).isEqualTo(isRollTwice);
        assertThat(record.isRollThreeTimes()).isEqualTo(isRollThreeTimes);
        assertThat(record.getMarking()).isEqualTo(marking);
    }
}
