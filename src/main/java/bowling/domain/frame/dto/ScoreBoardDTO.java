package bowling.domain.frame.dto;

import bowling.domain.frame.Frames;
import bowling.domain.user.dto.UserDTO;

public class ScoreBoardDTO {
    private final UserDTO user;
    private final Frames frames;

    public ScoreBoardDTO(UserDTO user, Frames frames) {
        this.user = user;
        this.frames = frames;
    }

    public UserDTO getUser() {
        return user;
    }

    public Frames getFrames() {
        return frames;
    }
}
