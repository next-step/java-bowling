package bowling.domain.score;

import bowling.domain.frame.FrameState;
import bowling.domain.roll.Roll;
import bowling.domain.roll.RollType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameScoreTest {

    @Test
    @DisplayName("프레임 스코어 정상 생성")
    void testCreateFrameScore() {
        // given
        FrameState frameState = FrameState.OPEN;
        int initialScore = 10;
        // when
        FrameScore frameScore = new FrameScore(frameState, initialScore);
        // then
        assertThat(frameScore.getScore().getScore()).isEqualTo(initialScore);
    }

    @Test
    @DisplayName("오픈 프레임 보너스 업데이트")
    void testOpenFrameUpdateScore() {
        // given
        FrameState frameState = FrameState.OPEN;
        int initialScore = 8;
        Roll roll = new Roll(RollType.STRIKE, 10);
        // when
        FrameScore frameScore = new FrameScore(frameState, initialScore);
        // then
        assertThat(frameScore.addable()).isFalse();
        assertThat(frameScore.update(roll)).isFalse();
        assertThat(frameScore.getScore().getScore()).isEqualTo(initialScore);
    }

    @Test
    @DisplayName("스페어 프레임 보너스 업데이트")
    void testSpareFrameUpdateScore() {
        // given
        FrameState frameState = FrameState.SPARE;
        int initialScore = 8;
        int addScore = 10;
        Roll roll = new Roll(RollType.STRIKE, addScore);
        // when
        FrameScore frameScore = new FrameScore(frameState, initialScore);
        // then
        assertThat(frameScore.addable()).isTrue();
        assertThat(frameScore.update(roll)).isTrue();
        assertThat(frameScore.addable()).isFalse();
        assertThat(frameScore.update(roll)).isFalse();
        assertThat(frameScore.getScore().getScore()).isEqualTo(initialScore + addScore);
    }

    @Test
    @DisplayName("스페어 프레임 보너스 업데이트")
    void testStrikeFrameUpdateScore() {
        // given
        FrameState frameState = FrameState.STRIKE;
        int initialScore = 8;
        Roll roll1 = new Roll(RollType.STRIKE, 10);
        Roll roll2 = new Roll(RollType.DEFAULT, 1);
        // when
        FrameScore frameScore = new FrameScore(frameState, initialScore);
        // then
        assertThat(frameScore.addable()).isTrue();
        assertThat(frameScore.update(roll1)).isTrue();
        assertThat(frameScore.addable()).isTrue();
        assertThat(frameScore.update(roll1)).isTrue();
        assertThat(frameScore.addable()).isFalse();
        assertThat(frameScore.update(roll2)).isFalse();
        assertThat(frameScore.getScore().getScore()).isEqualTo(initialScore + 10 + 10);
    }
}
