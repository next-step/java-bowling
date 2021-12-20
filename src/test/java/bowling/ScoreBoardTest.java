package bowling;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {

    @Test
    void createTest() {
        List<Frame> emptyFrames = new ArrayList<>();
        Assertions.assertThatCode(() -> new ScoreBoard(emptyFrames)).doesNotThrowAnyException();
    }
}
