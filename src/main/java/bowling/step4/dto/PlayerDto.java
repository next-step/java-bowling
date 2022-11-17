package bowling.step4.dto;


public class PlayerDto {
    public final String name;
    public final FramesDto framesDto;


    public PlayerDto(String name, FramesDto framesDto) {
        this.name = name;
        this.framesDto = framesDto;
    }
}
