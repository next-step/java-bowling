package bowling.dto;

import java.util.List;

public class BowlingDto {

    private final List<RecordsDto> frameRecords;

    public BowlingDto(List<RecordsDto> frameRecords) {
        this.frameRecords = frameRecords;
    }

    public List<RecordsDto> getFrameRecords() {
        return frameRecords;
    }
}
