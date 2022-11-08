package bowling.domain;

import java.util.List;

public class Records {
    private final List<Record> records;

    Records(List<Record> records) {
        this.records = records;
    }

    public boolean isEndRecords() {
        Record notEndRecord = records
                .stream()
                .filter(record -> !record.isEndRecord())
                .findFirst().orElse(null);
        return notEndRecord == null;
    }

    public boolean isEndLastFrame(int laneIndex) {
        return records.get(laneIndex).isEndLastFrame();
    }

    public void record(int laneIndex, int downPinCount) {
        records.get(laneIndex).record(downPinCount);
    }

    public int getRecordCount(int laneIndex) {
        return records.get(laneIndex).getRecordCount();
    }

    public Record getRecord(int laneIndex) {
        return records.get(laneIndex);
    }


}
