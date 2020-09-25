package bowling.domain.frame.dto;

import bowling.domain.frame.Frame;
import bowling.domain.user.dto.UserDTO;

import java.util.List;

public class ScoreBoardDTO {
    private final UserDTO user;
    private final List<Frame> frames;

    public ScoreBoardDTO(UserDTO user, List<Frame> frames) {
        this.user = user;
        this.frames = frames;
    }

    public UserDTO getUser() {
        return user;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
