package dto;

import bowling.Frame;

import java.util.Objects;

public class ResultFrameDTO {

    private final ResultPitchingDTO firstResultPitchingDTO;
    private final ResultPitchingDTO secondResultPitchingDTO;
    private final ResultPitchingDTO bonusResultPitchingDTO;

    private ResultFrameDTO(ResultPitchingDTO firstResultPitchingDTO, ResultPitchingDTO secondResultPitchingDTO,
                           ResultPitchingDTO bonusResultPitchingDTO) {
        this.firstResultPitchingDTO = firstResultPitchingDTO;
        this.secondResultPitchingDTO = secondResultPitchingDTO;
        this.bonusResultPitchingDTO = bonusResultPitchingDTO;
    }

    public static ResultFrameDTO from(Frame frame) {
        ResultPitchingDTO firstResultPitchingDTO =
                ResultPitchingDTO.of(PinDTO.from(frame.getFirstPin()), frame.isFirstDone());
        ResultPitchingDTO secondResultPitchingDTO =
                ResultPitchingDTO.of(PinDTO.from(frame.getSecondPin()), frame.isSecondDone());
        ResultPitchingDTO bonusResultPitchingDTO = null;
        if (frame.isLastFrame()) {
            bonusResultPitchingDTO =ResultPitchingDTO.of(PinDTO.from(frame.getBonusPin()), frame.isBonusDone());
        }
        return new ResultFrameDTO(firstResultPitchingDTO, secondResultPitchingDTO, bonusResultPitchingDTO);
    }

    public int getFirstPin() {
        return firstResultPitchingDTO.getPin();
    }

    public int getSecondPin() {
        return secondResultPitchingDTO.getPin();
    }

    public int getBonusPin() {
        return bonusResultPitchingDTO.getPin();
    }

    public boolean isFirstDone() {
        return firstResultPitchingDTO.isDone();
    }

    public boolean isSecondDone() {
        return secondResultPitchingDTO.isDone();
    }

    public boolean isBonusDone() {
        return bonusResultPitchingDTO.isDone();
    }

    public boolean isLastResultFrameDTO() {
        return !Objects.isNull(bonusResultPitchingDTO);
    }
}
