package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeScoreRuleTest {

    @DisplayName("Strike 이후 2번의 투구로 쓰러뜨린 핀 수를 더한다")
    @Test
    void score1(){
        Frame frame = Frame.createNormal(1);
        frame.mark(10);

        frame.createNext().mark(8);
        frame.next().mark(1);

        StrikeScoreRule rule = new StrikeScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.of(19));
    }

    @DisplayName("Strike 이후 2번의 투구로 쓰러뜨린 핀 수를 더한다 ( 더블 )")
    @Test
    void score2(){
        Frame frame = Frame.createNormal(1);
        frame.mark(10);
        frame.createNext().mark(10);
        frame.next().createNext().mark(8);

        StrikeScoreRule rule = new StrikeScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.of(28));
    }

    @DisplayName("Strike 직후에는 점수를 알 수 없다")
    @Test
    void rule3(){
        Frame frame = Frame.createNormal(1);
        frame.mark(10);

        SpareScoreRule rule = new SpareScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.unknown);
    }


}