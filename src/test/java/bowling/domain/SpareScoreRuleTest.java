package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareScoreRuleTest {

    @DisplayName("Spare 처리를 하면 이후 1번의 투구로 쓰러뜨린 핀 수를 점수에 더한다")
    @Test
    void rule1(){
        Frame frame = Frame.createNormal(1);
        frame.mark(9);
        frame.mark(1);
        frame.createNext().mark(9);

        SpareScoreRule rule = new SpareScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.of(19));
    }

    @DisplayName("Spare 처리 직후에는 점수를 알 수 없다")
    @Test
    void rule2(){
        Frame frame = Frame.createNormal(1);
        frame.mark(9);
        frame.mark(1);

        SpareScoreRule rule = new SpareScoreRule();
        assertThat(rule.apply(frame)).isEqualTo(FrameScore.unknown);
    }

}