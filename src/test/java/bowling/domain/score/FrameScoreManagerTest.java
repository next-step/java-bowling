package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.roll.Roll;
import bowling.domain.roll.RollType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameScoreManagerTest {

    @Test
    @DisplayName("스트라이크 프레임 전달 시 observer 등록")
    void testStrikeFrameNotRegisterManager() {
        // given
        FrameScoreManager scoreManager = new FrameScoreManager();
        Frame frame = new NormalFrame(1);
        frame.addRoll(new Roll(RollType.STRIKE, 10));
        // when
        scoreManager.registerEndFrame(frame.getFrameScore());
        // then
        assertThat(scoreManager.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("오픈 프레임 전달 시 observer 등록 안됨")
    void testOpenFrameNotRegisterManager() {
        // given
        FrameScoreManager scoreManager = new FrameScoreManager();
        Frame frame = new NormalFrame(1);
        frame.addRoll(new Roll(RollType.DEFAULT, 3));
        frame.addRoll(new Roll(RollType.DEFAULT, 3));
        // when
        scoreManager.registerEndFrame(frame.getFrameScore());
        // then
        assertThat(scoreManager.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("스페어 프레임 전달 시 observer 등록")
    void testSpareFrameNotRegisterManager() {
        // given
        FrameScoreManager scoreManager = new FrameScoreManager();
        Frame frame = new NormalFrame(1);
        frame.addRoll(new Roll(RollType.DEFAULT, 3));
        frame.addRoll(new Roll(RollType.SPARE, 7));
        // when
        scoreManager.registerEndFrame(frame.getFrameScore());
        // then
        assertThat(scoreManager.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크 프레임 보너스 점수 2번 획득")
    void testStrikeFrameGetBonusTwice() {
        // given
        FrameScoreManager scoreManager = new FrameScoreManager();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.addRoll(new Roll(RollType.STRIKE, 10));

        // when
        scoreManager.registerEndFrame(firstFrame.getFrameScore());
        scoreManager.notify(new Roll(RollType.DEFAULT, 3));
        scoreManager.notify(new Roll(RollType.DEFAULT, 6));
        scoreManager.notify(new Roll(RollType.DEFAULT, 3));

        // then
        assertThat(scoreManager.size()).isEqualTo(0);
        assertThat(scoreManager.scoreIterator().next()).isEqualTo(19);
    }

    @Test
    @DisplayName("스페어 프레임 보너스 점수 1번 획득")
    void testSpareFrameGetBonusTwice() {
        // given
        FrameScoreManager scoreManager = new FrameScoreManager();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.addRoll(new Roll(RollType.DEFAULT, 4));
        firstFrame.addRoll(new Roll(RollType.SPARE, 6));

        // when
        scoreManager.registerEndFrame(firstFrame.getFrameScore());
        scoreManager.notify(new Roll(RollType.DEFAULT, 3));
        scoreManager.notify(new Roll(RollType.DEFAULT, 6));
        scoreManager.notify(new Roll(RollType.DEFAULT, 3));

        // then
        assertThat(scoreManager.size()).isEqualTo(0);
        assertThat(scoreManager.scoreIterator().next()).isEqualTo(13);
    }

    @Test
    @DisplayName("오픈 프레임 보너스 점수 미획득")
    void testOpenFrameGetBonusTwice() {
        // given
        FrameScoreManager scoreManager = new FrameScoreManager();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.addRoll(new Roll(RollType.DEFAULT, 4));
        firstFrame.addRoll(new Roll(RollType.GUTTER, 0));

        // when
        scoreManager.registerEndFrame(firstFrame.getFrameScore());
        scoreManager.notify(new Roll(RollType.DEFAULT, 3));
        scoreManager.notify(new Roll(RollType.DEFAULT, 6));
        scoreManager.notify(new Roll(RollType.DEFAULT, 3));

        // then
        assertThat(scoreManager.size()).isEqualTo(0);
        assertThat(scoreManager.scoreIterator().next()).isEqualTo(4);
    }
}
