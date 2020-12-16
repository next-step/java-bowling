package bowling.domain.dto;

import bowling.domain.Frame;
import bowling.domain.User;

import java.util.List;

public class ResultDTO {
    private final String username;
    private final List<Frame> frames;

    public ResultDTO(final User user, final Frame firstFrame) {
        this.username = user.getName();
        this.frames = firstFrame.toFrames();
    }

    public String getUsername() {
        return username;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
