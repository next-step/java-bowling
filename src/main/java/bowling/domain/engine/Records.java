package bowling.domain.engine;

import bowling.dto.RecordsDto;
import bowling.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.engine.RecordType.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Records {

    private static final int REGULAR_CHANCES = 2;

    private final List<Record> recordList = new ArrayList<>();

    public void save(PitchResult pitchResult) {
        if (recordList.isEmpty()) {
            recordList.add(Record.of(pitchResult));
        } else {
            Record lastRecord = ListUtils.getLastElement(recordList);
            recordList.add(lastRecord.nextRecord(pitchResult));
        }
    }

    public RecordsDto export() {
        return recordList.stream()
                         .map(Record::export)
                         .collect(collectingAndThen(toList(), RecordsDto::new));
    }

    public boolean isStrike() {
        return throwCounts() != 0 && recordList.get(0).getRecordType() == STRIKE;
    }

    public boolean isMissed() {
        long normalRecordCounts =  recordList.stream()
                                             .limit(REGULAR_CHANCES)
                                             .map(Record::getRecordType)
                                             .filter(recordType -> recordType == NUMBER || recordType == GUTTER)
                                             .count();
        return throwCounts() >= REGULAR_CHANCES && normalRecordCounts == REGULAR_CHANCES;
    }

    public boolean isSpare() {
        return throwCounts() >= REGULAR_CHANCES && recordList.get(1).getRecordType() == SPARE;
    }

    public int throwCounts() {
        return recordList.size();
    }
}
