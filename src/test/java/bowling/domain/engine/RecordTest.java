package bowling.domain.engine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RecordTest {

    @Test
    @DisplayName("일반 기록")
    void normalRecord() {
        assertThat(Record.of(PitchResult.wrap(7)).getRecordType()).isEqualTo(RecordType.NUMBER);
    }

    @Test
    @DisplayName("스트라이크 기록")
    void strikeRecord() {
        assertThat(Record.of(PitchResult.wrap(10)).getRecordType()).isEqualTo(RecordType.STRIKE);
    }

    @Test
    @DisplayName("스페어 기록")
    void spareRecord() {
        Record normalRecord = Record.of(PitchResult.wrap(7));
        Record spareRecord = normalRecord.nextRecord(PitchResult.wrap(3));

        assertAll(
            () -> assertThat(normalRecord.getRecordType()).isEqualTo(RecordType.NUMBER),
            () -> assertThat(spareRecord.getRecordType()).isEqualTo(RecordType.SPARE)
        );
    }

    @Test
    @DisplayName("거터 기록")
    void gutterRecord() {
        assertThat(Record.of(PitchResult.wrap(0)).getRecordType()).isEqualTo(RecordType.GUTTER);
    }
        
}
