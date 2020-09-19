package dto;

import bowling.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResultFramesDTO {

    private final List<ResultFrameDTO> resultFrameDTOs;

    private ResultFramesDTO(List<ResultFrameDTO> resultFrameDTOs) {
        this.resultFrameDTOs = new ArrayList<>(resultFrameDTOs);
    }

    public List<ResultFrameDTO> getResultFrameDTOs() {
        return Collections.unmodifiableList(resultFrameDTOs);
    }

    public static ResultFramesDTO of(List<Frame> frames) {
        List<ResultFrameDTO> resultFrameDTOs = frames.stream()
                .map(ResultFrameDTO::from)
                .collect(Collectors.toList());
        return new ResultFramesDTO(resultFrameDTOs);
    }
}
