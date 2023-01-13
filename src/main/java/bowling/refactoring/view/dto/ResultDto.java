package bowling.refactoring.view.dto;

import bowling.refactoring.domain.*;
import bowling.refactoring.domain.frame.*;

import java.util.*;
import java.util.stream.*;

public class ResultDto {

    public String name;
    public List<ScoreDto> scores;

    public static ResultDto of(Player player) {
        ResultDto resultDto = new ResultDto();
        resultDto.name = player.name();
        Frames frames = player.frames();
        List<ScoreDto> scores = new ArrayList<>();
        for (int i = 0; i < frames.nowFrameNum(); i++) {
            ScoreDto scoreDto = new ScoreDto();

            scoreDto.totalScore = frames.frameList().stream()
                    .filter(it->it.state().isEndedCalculateScore())
                    .mapToInt(it -> it.state().totalScore())
                    .sum();
            scoreDto.values = frames.frameList()
                    .get(i).score().pinsList().stream()
                    .map(Pins::count)
                    .collect(Collectors.toList());
            scores.add(scoreDto);
        }
        resultDto.scores = scores;
        return resultDto;
    }
}
