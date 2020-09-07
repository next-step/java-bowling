package camp.nextstep.edu.rebellion.bowling.domain.game;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frames;
import camp.nextstep.edu.rebellion.bowling.domain.score.ScoreBoard;

public class BowlingGame {
    private final Frames frames;
    private final Player player;

    private Round gameRound;

    private BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.clear();
        this.gameRound = Round.reset();
    }

    public static BowlingGame start(Player player) {
        return new BowlingGame(player);
    }

    public void record(int hits) {
        frames.markScoreOnRound(gameRound, hits);
        frames.markBonusOnPrevious(gameRound, hits);

        if (frames.meetEnd(gameRound)) {
            frames.makeBonusChance(gameRound);
            gameRound.next();
        }
    }

    public int currentRound() {
        return gameRound.getCurrent() + 1;
    }

    public boolean hasNext() {
        // TODO
        // 마지막 라운드가 Strike 나 Spare 일 경우 보너스 프레임 생성
        // 그 다음 투구에서 마지막 라운드가 Strike 일 경우 투구 후 Exception 발생
        // 그 다음 투구에서 마지막 라운드가 Spare 일 경우 정상 종료

        // 원인은 Attempt 에 있음

        // Case1. 마지막 프레임 Strike 일 경우, 보너스 프레임에서 투구 할 때
        // frames.meetEnd 가 false
        // 따라서 gameRound.hasNext 는 true (gameRound.next 가 호출되지 않았으므로)
        // 그래서 hasBonusFrameChance 를 한번더 호출 해 보너스 프레임이 중복생성

        // Case2. 마지막 프레임 Spare 일 경우, 보너스 프레임에서 투구 할 때
        // frames.meetEnd 가 true (왜냐하면 attempt 가 1이므로 한번 투구하면 프레임 종료)
        // 따라서 gameRound.hasNext 는 false (gameRound.next 호출 되므로) 그래서 hasBonusFrameChance 가 호출 되지 않음
        // 종료는 되지만 정상적인 구동은 아님
        return gameRound.hasNext() || hasBonusFrameChance();
    }

    private boolean hasBonusFrameChance() {
        if (gameRound.meetLast() && frames.isFinalFrameStrikeOrSpare()) {
            frames.makeBonusFrame();
            return true;
        }


        return false;
    }

    public ScoreBoard getScoreBoard() {
        return new ScoreBoard(player, frames);
    }
}
