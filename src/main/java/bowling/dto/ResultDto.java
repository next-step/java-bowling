package bowling.dto;

public class ResultDto {
    private final int frameNumber;
    private final int score;

    public ResultDto(int frameNumber, int score) {
        this.frameNumber = frameNumber;
        this.score = score;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public int getScore() {
        return score;
    }
}
