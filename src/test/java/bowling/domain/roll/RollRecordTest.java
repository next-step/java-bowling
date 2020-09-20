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
        assertThat(record.isStrike()).isTrue();
        assertThat(record.isSpare()).isFalse();
        assertThat(record.isRollTwice()).isFalse();
        assertThat(record.isRollThreeTimes()).isFalse();
        assertThat(record.getMarking()).isEqualTo("X");
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
        assertThat(record.isStrike()).isFalse();
        assertThat(record.isSpare()).isTrue();
        assertThat(record.isRollTwice()).isTrue();
        assertThat(record.isRollThreeTimes()).isFalse();
        assertThat(record.getMarking()).isEqualTo("5|/");
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
        assertThat(record.isStrike()).isFalse();
        assertThat(record.isSpare()).isFalse();
        assertThat(record.isRollTwice()).isTrue();
        assertThat(record.isRollThreeTimes()).isFalse();
        assertThat(record.getMarking()).isEqualTo("5|1");
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
        assertThat(record.isStrike()).isFalse();
        assertThat(record.isSpare()).isFalse();
        assertThat(record.isRollTwice()).isTrue();
        assertThat(record.isRollThreeTimes()).isFalse();
        assertThat(record.getMarking()).isEqualTo("5|-");
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
        assertThat(record.isStrike()).isFalse();
        assertThat(record.isSpare()).isFalse();
        assertThat(record.isRollTwice()).isFalse();
        assertThat(record.isRollThreeTimes()).isTrue();
        assertThat(record.getMarking()).isEqualTo("5|/|1");
    }
}
