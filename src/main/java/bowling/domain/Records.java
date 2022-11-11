package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Records {
    private final List<Record> records;

    public Records(List<String> names) {
        List<Record> recordList = new ArrayList<>();
        names.forEach((name) -> recordList.add(new Record(new Player(name))));
        this.records = recordList;
    }

    public List<Record> getRecords() {
        return records;
    }

    public String getPlayerName(int laneIndex) {
        return records.get(laneIndex).getPlayerName();
    }

    public boolean isEndPlayerFrame(int frameIndex, int playerIndex) {
        if (playerIndex >= records.size()) {
            return true;
        }
        return records.get(playerIndex).isEndFrame(frameIndex);
    }

    public boolean isEndFrames(int frameIndex) {
        return records
                .stream()
                .allMatch(record -> record.isEndFrame(frameIndex));
    }

    public boolean isEndRecords() {
        return records
                .stream()
                .allMatch(Record::isEndRecord);
    }

    public void record(int laneIndex, int downPinCount) {
        records.get(laneIndex).record(downPinCount);
    }


}
