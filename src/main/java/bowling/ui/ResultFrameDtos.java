package bowling.ui;

import bowling.domain.MultiUserFrames;

import java.util.List;
import java.util.stream.Collectors;

public class ResultFrameDtos {

    private final int firstFrameNo;
    private final int finalFrameNo;
    private final List<ResultFrameDto> resultFrameDto;

    public ResultFrameDtos(int firstFrame, int finalFrameNo, MultiUserFrames multiUserFrames) {
        this.firstFrameNo = firstFrame;
        this.finalFrameNo = finalFrameNo;
        this.resultFrameDto = multiUserFrames
                .forEachFrames()
                .map(ResultFrameDto::new)
                .collect(Collectors.toList());
    }

    public int getFirstFrameNo() {
        return firstFrameNo;
    }

    public int getFinalFrameNo() {
        return finalFrameNo;
    }

    public List<ResultFrameDto> getResultFrameDto() {
        return resultFrameDto;
    }
}
