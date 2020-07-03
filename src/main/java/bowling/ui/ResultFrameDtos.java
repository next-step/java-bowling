package bowling.ui;

import bowling.domain.MultiUserFrames;

import java.util.List;
import java.util.stream.Collectors;

public class ResultFrameDtos {

    private final List<ResultFrameDto> resultFrameDto;

    public ResultFrameDtos(MultiUserFrames multiUserFrames) {
        this.resultFrameDto = multiUserFrames
                .forEachFrames()
                .map(ResultFrameDto::new)
                .collect(Collectors.toList());
    }

    public List<ResultFrameDto> getResultFrameDto() {
        return resultFrameDto;
    }
}
