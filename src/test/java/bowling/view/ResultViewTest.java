package bowling.view;

import bowling.domain.bowling.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.frame.LastFrame;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.Gutter;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("결과 출력 테스트")
public class ResultViewTest {
    @DisplayName("참여자별 점수 전체 출력")
    @Test
    public void showFrameList() {
        StringWriter output = new StringWriter();
        ResultView resultView = new ResultView(new PrintWriter(output));

        resultView.showScores(Arrays.asList(
                Bowling.of("PJS", Arrays.asList(
                        Frame.of(new Strike(0, Scores.of(Collections.singletonList(Score.strike())))),
                        Frame.of(new Spare(0, Scores.of(Arrays.asList(Score.ordinary(8), Score.spare(2))))),
                        Frame.of(new Gutter(0, Scores.of(Arrays.asList(Score.ordinary(7), Score.gutter())))),
                        Frame.empty(),
                        Frame.empty(),
                        Frame.empty(),
                        Frame.empty(),
                        Frame.empty(),
                        Frame.empty(),
                        LastFrame.empty())),
                Bowling.of("KYJ", Arrays.asList(
                        Frame.of(new Strike(0, Scores.of(Collections.singletonList(Score.strike())))),
                        Frame.of(new Spare(0, Scores.of(Arrays.asList(Score.ordinary(8), Score.spare(2))))),
                        Frame.of(new Gutter(0, Scores.of(Arrays.asList(Score.ordinary(7), Score.gutter())))),
                        Frame.empty(),
                        Frame.empty(),
                        Frame.empty(),
                        Frame.empty(),
                        Frame.empty(),
                        Frame.empty(),
                        LastFrame.empty()
                ))));

        assertThat(output.toString()).isEqualTo("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n" +
                "|  PJS |  X   |  8|/ |  7|- |      |      |      |      |      |      |      |\n" +
                "|      |  20  |  37  |  44  |      |      |      |      |      |      |      |\n" +
                "|  KYJ |  X   |  8|/ |  7|- |      |      |      |      |      |      |      |\n" +
                "|      |  20  |  37  |  44  |      |      |      |      |      |      |      |\n\n");
    }

}