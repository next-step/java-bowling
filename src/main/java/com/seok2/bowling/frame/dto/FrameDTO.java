package com.seok2.bowling.frame.dto;

public class FrameDTO {

    private String record;
    private FrameScoreDTO frameScoreDTO;

    public FrameDTO(String record, FrameScoreDTO frameScoreDTO) {
        this.record = record;
        this.frameScoreDTO = frameScoreDTO;
    }

    public String getRecord() {
        return record;
    }

    public FrameScoreDTO getFrameScoreDTO() {
        return frameScoreDTO;
    }


}
