package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-28 오후 3:48
 * Developer : Seo
 */
class GridStrikeTest {
    private Frames frames;
    private Player player;
    private int playerIndex;
    private int frameIndex;

    @BeforeEach
    void setUp() {
        frames = new Frames();
        player = new Player("aaa");
        playerIndex = 0;
        frameIndex = 0;
    }
}