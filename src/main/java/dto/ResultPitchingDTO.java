package dto;

public class ResultPitchingDTO {

    private final PinDTO pinDTO;
    private final boolean isDone;

    private ResultPitchingDTO(PinDTO pinDTO, boolean isDone) {
        this.pinDTO = pinDTO;
        this.isDone = isDone;
    }

    public int getPin() {
        return pinDTO.getPin();
    }

    public boolean isDone() {
        return isDone;
    }

    public static ResultPitchingDTO of(PinDTO pinDTO, boolean isDone) {
        return new ResultPitchingDTO(pinDTO, isDone);
    }
}
