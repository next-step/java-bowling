package domain;

import domain.base.BaseTest;
import domain.frame.Frame;
import domain.frame.Frames;
import domain.frame.LastFrame;
import domain.frame.NormalFrame;
import domain.pin.Pin;
import org.junit.Test;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;
import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest extends BaseTest {
    private static final PlayerName PLAYER_NAME = new PlayerName("pdy");

    @Test
    public void isContinuable_for_empty_frames() {
        BowlingGame game = new BowlingGame(PLAYER_NAME);

        assertThat(game.isContinuable()).isTrue();
    }

    @Test
    public void isContinuable() {
        Frames frames = new Frames();
        for(int frameNumber : getFrameNumbers(START_FRAME, LAST_FRAME-1)) {
            frames.add(new NormalFrame(frameNumber, Pin.ofStrike()));
            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);

            assertThat(game.isContinuable()).isTrue();
        }
    }

    @Test
    public void isContinuable_for_last_frame_after_first_bowl() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            Frame frame = new LastFrame(LAST_FRAME, firstBowl);
            Frames frames = new Frames();
            frames.add(frame);
            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);

            assertThat(game.isContinuable()).isTrue();
        }
    }

    @Test
    public void isContinuable_for_last_frame_after_second_bowl_strike() {
        for(Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            Frame frame = new LastFrame(LAST_FRAME, Pin.ofStrike());
            frame.bowl(secondBowl);
            Frames frames = new Frames();
            frames.add(frame);
            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);

            assertThat(game.isContinuable()).isTrue();
        }
    }

    @Test
    public void isContinuable_for_last_frame_after_second_bowl_spare() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());
            Frame frame = new LastFrame(LAST_FRAME, firstBowl);
            frame.bowl(secondBowl);
            Frames frames = new Frames();
            frames.add(frame);

            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
            assertThat(game.isContinuable()).isTrue();
        }
    }

    @Test
    public void isContinuable_for_last_frame_after_second_bowl_open() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Frames frames = new Frames();
            fillNormalFrames(frames, Pin.ofStrike());

            Frame frame = new LastFrame(LAST_FRAME, firstBowl);
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin() - 1);
            frame.bowl(secondBowl);

            frames.add(frame);

            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
            assertThat(game.isContinuable()).isFalse();
        }
    }

    @Test
    public void isContinuable_for_last_frame_after_third_bowl() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            Frames frames = new Frames();
            fillNormalFrames(frames, Pin.ofStrike());

            Frame frame = new LastFrame(LAST_FRAME, firstBowl);
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());
            frame.bowl(secondBowl);
            frame.bowl(Pin.of(5));
            frames.add(frame);

            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
            assertThat(game.isContinuable()).isFalse();
        }
    }

    private void fillNormalFrames(Frames frames, Pin pin) {
        for (int frameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            frames.add(new NormalFrame(frameNumber, pin));
        }
    }

}