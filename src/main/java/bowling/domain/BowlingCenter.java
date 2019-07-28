package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 03:24
 */
public class BowlingCenter {
    private Frames frames;
    private final Players players;

    public BowlingCenter(Players players) {
        this.frames = new Frames();
        this.players = players;
    }

    // TODO 이후 2~3 단계에서 Player 별개의 Frames를 할당하여 수행되게 변경해야될 Point (사용자 이름 전달하여 Frame 투구 수정필요)
    public boolean play(int fallCount) {
        return frames.bowl(fallCount);
    }

    // TODO 이후 2~3 단계에서 Player 별개의 Frames를 할당하여 수행되게 변경해야될 Point (사용자 이름 전달 하여 해당 Frame 출력되게 수정필요)
    public List<String> displayState() {
        return frames.displayState();
    }

    // TODO 이후 2~3 단계에서 Player 별개의 Frames를 할당하여 수행되게 변경해야될 Point (Frame 상태 출력 시 Player 매핑되게 가져오게 수정필요)
    public String playerName() {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining());
    }

    public List<Integer> displayScore() {
        return frames.displayScore();
    }

    public int playFrameNumber() {
        return frames.currentFrameNumber();
    }

    @Override
    public String toString() {
        return "BowlingCenter{" +
                "frames=" + frames +
                ", players=" + players +
                '}';
    }
}
