package dto;

public class FrameDTO implements DTO {

    private final int frame;

    private FrameDTO(int frame) {
        this.frame = frame;
    }

    public int getFrame() {
        return frame;
    }

    public static FrameDTO of(int frame) {
        return new FrameDTO(frame);
    }

}
