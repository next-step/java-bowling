package domain.state;

import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

class WaitingTest {

    private Waiting waiting;

    @BeforeEach
    void setUp() {
        waiting = new Waiting();
    }
}