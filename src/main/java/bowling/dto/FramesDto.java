package bowling.dto;

import java.util.List;

public class FramesDto {

    private final List<RecordsDto> recordsDtoList;

    public FramesDto(List<RecordsDto> recordsDtoList) {
        this.recordsDtoList = recordsDtoList;
    }

    public List<RecordsDto> getRecords() {
        return recordsDtoList;
    }
}
