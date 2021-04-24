package bowling.dto;

import java.util.List;

public class RecordsDto {

    private final List<String> records;

    public RecordsDto(List<String> records) {
        this.records = records;
    }

    public List<String> getRecords() {
        return records;
    }
}
