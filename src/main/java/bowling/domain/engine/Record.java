package bowling.domain.engine;

import java.util.Objects;

public class Record {

    private final PitchResult pitchResult;
    private final RecordType recordType;

    private Record(PitchResult pitchResult, RecordType recordType) {
        this.pitchResult = pitchResult;
        this.recordType = recordType;
    }

    public static Record of(PitchResult pitchResult) {
        if (pitchResult.getValue() == Pins.MAXIMUM_PINS) {
            return new Record(pitchResult, RecordType.STRIKE);
        } else {
            return new Record(pitchResult, pitchResult.getValue() == 0 ? RecordType.GUTTER : RecordType.NUMBER);
        }
    }

    public Record nextRecord(PitchResult pitchResult) {
        if (pitchResult.getValue() + this.pitchResult.getValue() == Pins.MAXIMUM_PINS) {
            return new Record(pitchResult, RecordType.SPARE);
        } else {
            return Record.of(pitchResult);
        }
    }

    public PitchResult getPitchResult() {
        return pitchResult;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public String export() {
        return recordType.export(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;
        Record record = (Record) o;
        return Objects.equals(getPitchResult(), record.getPitchResult()) && getRecordType() == record
            .getRecordType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPitchResult(), getRecordType());
    }
}
