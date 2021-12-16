package bowling.service.dto;

import bowling.domain.result.FrameResult;
import bowling.domain.result.FrameResults;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FrameResultsDto {
    private final List<FrameResultDto> frameResultDtos;

    private FrameResultsDto(List<FrameResultDto> frameResultDtos) {
        this.frameResultDtos = frameResultDtos;
    }

    public static FrameResultsDto of(FrameResults frameResults) {
        List<FrameResult> values = frameResults.getValues();
        List<FrameResultDto> frameResultDtos = values.stream()
                .map(FrameResultDto::of)
                .collect(toList());
//
//        List<String> frameDescriptions = frameResultDtos.stream()
//                .map(FrameResultDto::getDescription)
//                .collect(toList());
//        List<Integer> scores = frameResultDtos.stream()
//                .map(FrameResultDto::getScore)
//                .filter(score -> score != NO_SCORE_VALUE)
//                .collect(toList());

        return new FrameResultsDto(frameResultDtos);
    }

    public List<FrameResultDto> getFrameResultDtos() {
        return frameResultDtos;
    }

    @Override
    public String toString() {
        return "FrameResultsDto{" +
                "frameResultDtos=" + frameResultDtos +
                '}';
    }
}
