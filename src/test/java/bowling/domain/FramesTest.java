package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-17 오전 8:06
 * Developer : Seo
 */
class FramesTest {

    @Test
    void name() {
        Frames frames = new Frames();
        frames.getFrames().stream().forEach(System.out::println);
        System.out.println(frames.size());
    }
}