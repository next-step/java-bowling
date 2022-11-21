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
        outputView.print(Name.of("abc"), frames);
        frames.bowling(5);
        outputView.print(Name.of("abc"), frames);
        //2
        frames.bowling(2);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(3);
        outputView.print(Name.of("abc"), frames);
        //3
        frames.bowling(5);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(5);
        outputView.print(Name.of("abc"), frames);
        //4
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        //5
        frames.bowling(5);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(3);
        outputView.print(Name.of("abc"), frames);
        //6
        frames.bowling(0);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        //7
        frames.bowling(0);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        //8
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        //9
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        //10
        frames.bowling(5);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(5);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
    }

    @Disabled
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

    @Disabled
    @Test
    void 생성3() {
        Frames frames = Frames.init();
        OutputView outputView = OutputView.init();
        frames.bowling(10);
//        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
//        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        //10
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);
        outputView.print(Name.of("abc"), frames);
        frames.bowling(10);

        outputView.print(Name.of("abc"), frames);
    }
}
