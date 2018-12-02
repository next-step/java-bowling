package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;
import bowling.domain.record.Spare;
import bowling.domain.record.Strike;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.utils.BowlingConstants.ONE;

public class Records {

    // 프레임당 레코드의 수
    // 스트라이크인 경우 : 1
    // 그 외의 경우 : 2
    // 마지막 프레임인 경우 최대 : 3
    private List<Record> frameRecord = new ArrayList<>();

    public Record addToRecords(Pin pin) {
        Record record = Record.ofPinCount(pin);

        if(!frameRecord.isEmpty()) {
            Record prevRecord = this.frameRecord.get(this.frameRecord.size() - ONE);
            record = prevRecord.nextRecord(pin);
        }

        this.frameRecord.add(record);
        return record;
    }

    @Override
    public String toString() {
        return this.frameRecord.stream()
                .map(Record::recordToString)
                .collect(Collectors.joining("|"));
    }

    boolean isLastChance(int chance) {
        return this.frameRecord.size() == chance;
    }

    public boolean isLastRecordStrike() {
        if(!frameRecord.isEmpty()) {
            Record record = frameRecord.get(this.frameRecord.size() - ONE);
            return record.equals(Strike.getInstance());
        }
        return false;
    }

    public boolean isLastRecordSpare() {
        if(!frameRecord.isEmpty()) {
            Record record = frameRecord.get(this.frameRecord.size() - ONE);
            return record.equals(Spare.getInstance());
        }
        return false;
    }
}
