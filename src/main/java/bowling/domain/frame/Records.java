package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.utils.BowlingConstants.ONE;
import static bowling.utils.BowlingConstants.ZERO;

public class Records {

    // 프레임당 레코드의 수
    // 스트라이크인 경우 : 1
    // 그 외의 경우 : 2
    // 마지막 프레임인 경우 최대 : 3
    private List<Record> frameRecord = new ArrayList<>();

    public Record addToRecords(Pin pin) {
        Record record = Record.ofPinCount(pin);

        if(this.frameRecord.size() > ZERO) {
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

    Record findLastRecord() {
        return this.frameRecord.get(this.frameRecord.size() - ONE);
    }

    Record findFirstRecord() {
        return this.frameRecord.get(ZERO);
    }
}
