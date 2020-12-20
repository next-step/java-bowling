package bowling.model.player;

import bowling.model.frame.FrameResult;

import java.util.List;

public class PlayerResult {
    private static final String RESULT_COUNT_ERROR = "결과는 2개의 타입만 가능합니다.";
    private static final int FRAME_INFO = 0;
    private static final int SCORE_INFO = 1;

    private String user;
    private List<FrameResult> info;

    private PlayerResult(String user, List<FrameResult> info) {
        this.user = user;
        this.info = info;
    }

    public static PlayerResult of(String user, List<FrameResult> info) {
        validPlayerResult(info);
        return new PlayerResult(user, info);
    }

    private static void validPlayerResult(List<FrameResult> info) {
        if (info.size() != 2) {
            throw new IllegalArgumentException(RESULT_COUNT_ERROR);
        }
    }

    public String user() {
        return user;
    }

    public FrameResult frame() {
        return info.get(FRAME_INFO);
    }

    public FrameResult score() {
        return info.get(SCORE_INFO);
    }
}
