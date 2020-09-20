package dto;

import bowling.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ScoresDTO {

    private final List<ScoreDTO> scoreDTOs;

    private ScoresDTO(List<ScoreDTO> scoreDTOs) {
        this.scoreDTOs = new ArrayList<>(scoreDTOs);
    }

    public static ScoresDTO of(List<Frame> frames) {
        AtomicInteger accumulator = new AtomicInteger(0);
        List<ScoreDTO> scoreDTOs = frames.stream()
                .map(frame -> {
                    ScoreDTO scoreDTO = ScoreDTO.from(frame, accumulator.get());
                    accumulator.set(scoreDTO.getScore());
                    return scoreDTO;
                })
                .collect(Collectors.toList());
        return new ScoresDTO(scoreDTOs);
    }

    public List<ScoreDTO> getScoreDTOs() {
        return Collections.unmodifiableList(scoreDTOs);
    }
}
