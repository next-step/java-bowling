package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.user.User;
import bowling.view.ResultView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GridTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("aaa");
    }

    @Test
    void init() {
    }
}
