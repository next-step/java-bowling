package bowling.domain;

import bowling.NotPlayableException;
import bowling.domain.dto.ResultDTO;

public class BowlingGame {
    private final User user;
    private final Frame firstFrame;
    private Frame nowFrame;

    private BowlingGame(final User user, final Frame firstFrame) {
        this.user = user;
        this.firstFrame = firstFrame;
        this.nowFrame = firstFrame;
    }

    public static BowlingGame of(final User user) {
        return new BowlingGame(user, FrameFactory.creates());
    }

    public boolean isNotFinished() {
        return !isFinished();
    }
    
    public boolean isFinished() {
        return nowFrame.isFinishedAll();
    }

    public int nowFrameNo() {
        return nowFrame.getIndex() + 1;
    }

    public ResultDTO pitch(final Pins pins) {
        if (isFinished()) {
            throw new NotPlayableException();
        }
        
        if (nowFrame.isNotPlayable()) {
            nowFrame = nowFrame.next();
        }
        
        nowFrame.pitch(pins);
        return getResultDTO();
    }
    
    public ResultDTO getResultDTO() {
        return new ResultDTO(user, firstFrame);
    }
}
