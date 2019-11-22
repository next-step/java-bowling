package com.seok2.bowling.frame.dto;

public class FrameDTO {

    private final String record;
    private final ScoreDTO scoreDTO;

    public FrameDTO(String record, ScoreDTO scoreDTO) {
        this.record = record;
        this.scoreDTO = scoreDTO;
    }

    public String getRecord() {
        return record;
    }

    public ScoreDTO getScoreDTO() {
        return scoreDTO;
    }
}
