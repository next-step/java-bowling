package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BoslingMainTest {

    @BeforeEach
    void init() {
        String input = "2\n" +
                "YGB\n" +
                "AAA\n" +
                "10\n" +
                "7 3\n" +
                "10\n" +
                "8 1\n" +
                "10\n" +
                "10\n" +
                "10\n" +
                "0 0\n" +
                "10\n" +
                "1 2\n" +
                "10\n" +
                "10\n" +
                "10\n" +
                "10\n" +
                "10\n" +
                "9 1\n" +
                "10\n" +
                "5 3\n" +
                "10 10 10\n" +
                "6 4 7\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @DisplayName("통합 테스트 (볼링 점수판을 실행한다.)")
    @Test
    public void playBowling() {
        BowlingGame.play();
    }

}
