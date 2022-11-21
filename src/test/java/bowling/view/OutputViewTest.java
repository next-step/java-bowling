package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Name;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("로컬 확인용")
class OutputViewTest {

    @Test
    void 생성() {
        Frames frames = Frames.init();
        OutputView outputView = OutputView.init();
        //1
        bowlingAndPrint(frames, outputView, 5);
        bowlingAndPrint(frames, outputView, 5);
        //2
        bowlingAndPrint(frames, outputView, 2);
        bowlingAndPrint(frames, outputView, 3);
        //3
        bowlingAndPrint(frames, outputView, 5);
        bowlingAndPrint(frames, outputView, 5);
        //4
        bowlingAndPrint(frames, outputView, 10);
        //5
        bowlingAndPrint(frames, outputView, 5);
        bowlingAndPrint(frames, outputView, 3);
        //6
        bowlingAndPrint(frames, outputView, 0);
        bowlingAndPrint(frames, outputView, 10);
        //7
        bowlingAndPrint(frames, outputView, 0);
        bowlingAndPrint(frames, outputView, 10);
        //8
        bowlingAndPrint(frames, outputView, 10);
        //9
        bowlingAndPrint(frames, outputView, 10);
        //10
        bowlingAndPrint(frames, outputView, 5);
        bowlingAndPrint(frames, outputView, 5);
        bowlingAndPrint(frames, outputView, 10);
    }

    private void bowlingAndPrint(Frames frames, OutputView outputView, int count) {
        frames.bowling(count);
        outputView.print(Name.of("abc"), frames);
    }
}
