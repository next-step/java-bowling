package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Name;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//@Disabled("로컬 확인용")
class OutputViewTest {

    @Test
    void 생성() {
        Frames frames = Frames.init();
        OutputView outputView = OutputView.init();
        //1
        frames.bowling(5);
        frames.bowling(5);
        //2
        frames.bowling(2);
        frames.bowling(3);
        //3
        frames.bowling(5);
        frames.bowling(5);
        //4
        frames.bowling(10);
        //5
        frames.bowling(5);
        frames.bowling(3);
        //6
        frames.bowling(0);
        frames.bowling(10);
        //7
        frames.bowling(0);
        frames.bowling(10);
        //8
        frames.bowling(10);
        //9
        frames.bowling(10);
        //10
        frames.bowling(5);
        frames.bowling(5);
        frames.bowling(10);

        outputView.print(Name.of("abc"), frames);
    }

    @Test
    void 생성2() {
        Frames frames = Frames.init();
        OutputView outputView = OutputView.init();

        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        //10
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);

        outputView.print(Name.of("abc"), frames);
    }
}
