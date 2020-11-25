package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("결과 출력 테스트")
public class ResultViewTest {
    @DisplayName("참여자별 프레임 리스트 출력")
    @Test
    public void showFrameList() {
        StringWriter output = new StringWriter();
        ResultView resultView = new ResultView(new PrintWriter(output));

        resultView.showFrames("PJS", Arrays.asList(
                Frame.of(1, Arrays.asList(Score.strike())),
                Frame.of(2, Arrays.asList(Score.ordinary(8), Score.spare(2))),
                Frame.of(3, Arrays.asList(Score.ordinary(7), Score.gutter())),
                Frame.of(4, Collections.emptyList()),
                Frame.of(5, Collections.emptyList()),
                Frame.of(6, Collections.emptyList()),
                Frame.of(7, Collections.emptyList()),
                Frame.of(8, Collections.emptyList()),
                Frame.of(9, Collections.emptyList()),
                Frame.of(10, Collections.emptyList())
        ));

        assertThat(output.toString()).isEqualTo("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n" +
                "|  PJS |  X   |  8|/ |  7|- |      |      |      |      |      |      |      |\n");
    }
}