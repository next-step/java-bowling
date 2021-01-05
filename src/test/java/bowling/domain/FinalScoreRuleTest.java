package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalScoreRuleTest {

    @DisplayName("투구로 쓰러뜨린 모든핀수를 더한다 ( Miss )")
    @Test
    void rule1(){
        Frame frame = Frame.createFinal(10);
        frame.mark(8);
        frame.mark(1);

        FinalScoreRule rule = new FinalScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.of(9));
    }

    @DisplayName("투구로 쓰러뜨린 모든핀수를 더한다 ( Spare + Bonus )")
    @Test
    void rule2(){
        Frame frame = Frame.createFinal(10);
        frame.mark(9);
        frame.mark(1);
        frame.mark(9);

        FinalScoreRule rule = new FinalScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.of(19));
    }

    @DisplayName("투구로 쓰러뜨린 모든핀수를 더한다 ( 터키 )")
    @Test
    void rule3(){
        Frame frame = Frame.createFinal(10);
        frame.mark(10);
        frame.mark(10);
        frame.mark(10);

        FinalScoreRule rule = new FinalScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.of(30));
    }

    @DisplayName("투구로 쓰러뜨린 모든핀수를 더한다 ( Strike + Spare )")
    @Test
    void rule4(){
        Frame frame = Frame.createFinal(10);
        frame.mark(10);
        frame.mark(9);
        frame.mark(1);

        FinalScoreRule rule = new FinalScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.of(20));
    }

}