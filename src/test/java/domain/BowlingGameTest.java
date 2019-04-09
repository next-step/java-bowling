package domain;

import domain.base.BaseTest;
import domain.frame.Frame;
import domain.frame.Frames;
import domain.frame.LastFrame;
import domain.frame.NormalFrame;
import domain.pin.Pin;
import domain.status.FirstBowlFinished;
import domain.status.Open;
import domain.status.Spare;
import domain.status.Strike;
import org.junit.Test;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;
import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest extends BaseTest {
    private static final PlayerName PLAYER_NAME = new PlayerName("pdy");

    @Test
    public void play_for_first_frame_and_strike() {
        Pin curPin = Pin.ofStrike();
        BowlingGame game = new BowlingGame(PLAYER_NAME);
        game.play(curPin.getPin());

        assertThat(game.getFramesSize()).isEqualTo(1);
        assertThat(game.getFrame(0)).isInstanceOf(NormalFrame.class);
        assertThat(game.getFrame(0).getNumber()).isEqualTo(START_FRAME);
        assertThat(game.getFrame(0).getPin(0)).isEqualTo(curPin);
        assertThat(game.getFrame(0).getLastStatus()).isInstanceOf(Strike.class);
    }

    @Test
    public void play_for_spare_first_frame() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());
            BowlingGame game = new BowlingGame(PLAYER_NAME);
            game.play(firstBowl.getPin());
            game.play(secondBowl.getPin());

            assertThat(game.getFramesSize()).isEqualTo(1);
            assertThat(game.getFrame(0)).isInstanceOf(NormalFrame.class);
            assertThat(game.getFrame(0).getNumber()).isEqualTo(START_FRAME);
            assertThat(game.getFrame(0).getPinsSize()).isEqualTo(2);
            assertThat(game.getFrame(0).getPin(0)).isEqualTo(firstBowl);
            assertThat(game.getFrame(0).getPin(1)).isEqualTo(secondBowl);
            assertThat(game.getFrame(0).getStatusesSize()).isEqualTo(2);
            assertThat(game.getFrame(0).getStatus(0)).isInstanceOf(FirstBowlFinished.class);
            assertThat(game.getFrame(0).getLastStatus()).isInstanceOf(Spare.class);
        }
    }

    @Test
    public void play_for_open_first_frame() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for(Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                BowlingGame game = new BowlingGame(PLAYER_NAME);
                game.play(firstBowl.getPin());
                game.play(secondBowl.getPin());

                assertThat(game.getFramesSize()).isEqualTo(1);
                assertThat(game.getFrame(0)).isInstanceOf(NormalFrame.class);
                assertThat(game.getFrame(0).getNumber()).isEqualTo(START_FRAME);
                assertThat(game.getFrame(0).getPinsSize()).isEqualTo(2);
                assertThat(game.getFrame(0).getPin(0)).isEqualTo(firstBowl);
                assertThat(game.getFrame(0).getPin(1)).isEqualTo(secondBowl);
                assertThat(game.getFrame(0).getStatusesSize()).isEqualTo(2);
                assertThat(game.getFrame(0).getStatus(0)).isInstanceOf(FirstBowlFinished.class);
                assertThat(game.getFrame(0).getLastStatus()).isInstanceOf(Open.class);
            }
        }
    }

    @Test
    public void play_for_strike_ninth_frame() {
        Frames frames = new Frames();
        fillNormalFrames(frames, START_FRAME, LAST_FRAME - 2, Pin.ofStrike());

        BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
        game.play(MAXIMUM_PINS);

        assertThat(game.getFramesSize()).isEqualTo(9);
        assertThat(game.getRecentFrame()).isInstanceOf(NormalFrame.class);
        assertThat(game.getRecentFrame().getNumber()).isEqualTo(LAST_FRAME - 1);
        assertThat(game.getRecentFrame().getPin(0)).isEqualTo(Pin.ofStrike());
        assertThat(game.getRecentFrame().getLastStatus()).isInstanceOf(Strike.class);
    }

    @Test
    public void play_for_spare_ninth_frame() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());

            Frames frames = new Frames();
            fillNormalFrames(frames, START_FRAME, LAST_FRAME - 2, Pin.ofStrike());

            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
            game.play(firstBowl.getPin());
            game.play(secondBowl.getPin());

            assertThat(game.getFramesSize()).isEqualTo(9);
            assertThat(game.getRecentFrame()).isInstanceOf(NormalFrame.class);
            assertThat(game.getRecentFrame().getNumber()).isEqualTo(LAST_FRAME - 1);
            assertThat(game.getRecentFrame().getPinsSize()).isEqualTo(2);
            assertThat(game.getRecentFrame().getPin(0)).isEqualTo(firstBowl);
            assertThat(game.getRecentFrame().getPin(1)).isEqualTo(secondBowl);
            assertThat(game.getRecentFrame().getStatusesSize()).isEqualTo(2);
            assertThat(game.getRecentFrame().getStatus(0)).isInstanceOf(FirstBowlFinished.class);
            assertThat(game.getRecentFrame().getLastStatus()).isInstanceOf(Spare.class);
        }
    }

    @Test
    public void play_for_open_ninth_frame() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for(Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                Frames frames = new Frames();
                fillNormalFrames(frames, START_FRAME, LAST_FRAME - 2, Pin.ofStrike());

                BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
                game.play(firstBowl.getPin());
                game.play(secondBowl.getPin());

                assertThat(game.getFramesSize()).isEqualTo(9);
                assertThat(game.getRecentFrame()).isInstanceOf(NormalFrame.class);
                assertThat(game.getRecentFrame().getNumber()).isEqualTo(LAST_FRAME - 1);
                assertThat(game.getRecentFrame().getPinsSize()).isEqualTo(2);
                assertThat(game.getRecentFrame().getPin(0)).isEqualTo(firstBowl);
                assertThat(game.getRecentFrame().getPin(1)).isEqualTo(secondBowl);
                assertThat(game.getRecentFrame().getStatusesSize()).isEqualTo(2);
                assertThat(game.getRecentFrame().getStatus(0)).isInstanceOf(FirstBowlFinished.class);
                assertThat(game.getRecentFrame().getLastStatus()).isInstanceOf(Open.class);
            }
        }
    }

    @Test
    public void play_for_strike_and_open_last_frame() {
        for(Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin thirdBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - secondBowl.getPin() - 1)) {
                Pin firstBowl = Pin.ofStrike();

                Frames frames = new Frames();
                fillNormalFrames(frames, START_FRAME, LAST_FRAME - 1, Pin.ofStrike());

                BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
                game.play(firstBowl.getPin());
                game.play(secondBowl.getPin());
                game.play(thirdBowl.getPin());

                assertThat(game.getFramesSize()).isEqualTo(10);
                assertThat(game.getRecentFrame()).isInstanceOf(LastFrame.class);
                assertThat(game.getRecentFrame().getNumber()).isEqualTo(LAST_FRAME);
                assertThat(game.getRecentFrame().getPin(0)).isEqualTo(firstBowl);
                assertThat(game.getRecentFrame().getPin(1)).isEqualTo(secondBowl);
                assertThat(game.getRecentFrame().getPin(2)).isEqualTo(thirdBowl);
                assertThat(game.getRecentFrame().getStatusesSize()).isEqualTo(3);
                assertThat(game.getRecentFrame().getStatus(0)).isInstanceOf(Strike.class);
                assertThat(game.getRecentFrame().getStatus(1)).isInstanceOf(FirstBowlFinished.class);
                assertThat(game.getRecentFrame().getLastStatus()).isInstanceOf(Open.class);
            }
        }
    }

    @Test
    public void play_for_spare_last_frame() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for(Pin thirdBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());

                Frames frames = new Frames();
                fillNormalFrames(frames, START_FRAME, LAST_FRAME - 1, Pin.ofStrike());

                BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
                game.play(firstBowl.getPin());
                game.play(secondBowl.getPin());
                game.play(thirdBowl.getPin());

                assertThat(game.getFramesSize()).isEqualTo(10);
                assertThat(game.getRecentFrame()).isInstanceOf(LastFrame.class);
                assertThat(game.getRecentFrame().getNumber()).isEqualTo(LAST_FRAME);
                assertThat(game.getRecentFrame().getPinsSize()).isEqualTo(3);
                assertThat(game.getRecentFrame().getPin(0)).isEqualTo(firstBowl);
                assertThat(game.getRecentFrame().getPin(1)).isEqualTo(secondBowl);
                assertThat(game.getRecentFrame().getPin(2)).isEqualTo(thirdBowl);
                assertThat(game.getRecentFrame().getStatusesSize()).isEqualTo(3);
                assertThat(game.getRecentFrame().getStatus(0)).isInstanceOf(FirstBowlFinished.class);
                assertThat(game.getRecentFrame().getStatus(1)).isInstanceOf(Spare.class);
                assertThat(game.getRecentFrame().getLastStatus()).isInstanceOf(FirstBowlFinished.class);
            }
        }
    }

    @Test
    public void play_for_spare_last_frame2() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());
            Pin thirdBowl = Pin.ofStrike();

            Frames frames = new Frames();
            fillNormalFrames(frames, START_FRAME, LAST_FRAME - 1, Pin.ofStrike());

            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
            game.play(firstBowl.getPin());
            game.play(secondBowl.getPin());
            game.play(thirdBowl.getPin());

            assertThat(game.getFramesSize()).isEqualTo(10);
            assertThat(game.getRecentFrame()).isInstanceOf(LastFrame.class);
            assertThat(game.getRecentFrame().getNumber()).isEqualTo(LAST_FRAME);
            assertThat(game.getRecentFrame().getPinsSize()).isEqualTo(3);
            assertThat(game.getRecentFrame().getPin(0)).isEqualTo(firstBowl);
            assertThat(game.getRecentFrame().getPin(1)).isEqualTo(secondBowl);
            assertThat(game.getRecentFrame().getPin(2)).isEqualTo(thirdBowl);
            assertThat(game.getRecentFrame().getStatusesSize()).isEqualTo(3);
            assertThat(game.getRecentFrame().getStatus(0)).isInstanceOf(FirstBowlFinished.class);
            assertThat(game.getRecentFrame().getStatus(1)).isInstanceOf(Spare.class);
            assertThat(game.getRecentFrame().getLastStatus()).isInstanceOf(Strike.class);
        }
    }

    @Test
    public void play_for_open_last_frame() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for(Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                Frames frames = new Frames();
                fillNormalFrames(frames, START_FRAME, LAST_FRAME - 1, Pin.ofStrike());

                BowlingGame game = new BowlingGame(PLAYER_NAME, frames);
                game.play(firstBowl.getPin());
                game.play(secondBowl.getPin());

                assertThat(game.getFramesSize()).isEqualTo(10);
                assertThat(game.getRecentFrame()).isInstanceOf(LastFrame.class);
                assertThat(game.getRecentFrame().getNumber()).isEqualTo(LAST_FRAME);
                assertThat(game.getRecentFrame().getPinsSize()).isEqualTo(2);
                assertThat(game.getRecentFrame().getPin(0)).isEqualTo(firstBowl);
                assertThat(game.getRecentFrame().getPin(1)).isEqualTo(secondBowl);
                assertThat(game.getRecentFrame().getStatusesSize()).isEqualTo(2);
                assertThat(game.getRecentFrame().getStatus(0)).isInstanceOf(FirstBowlFinished.class);
                assertThat(game.getRecentFrame().getLastStatus()).isInstanceOf(Open.class);
            }
        }
    }

    @Test
    public void isContinuable_for_empty_frames() {
        BowlingGame game = new BowlingGame(PLAYER_NAME);

        assertThat(game.isContinuable()).isTrue();
    }

    @Test
    public void isContinuable() {
        Frames frames = new Frames();
        for(int frameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            frames.add(new NormalFrame(frameNumber, Pin.ofStrike()));
            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);

            assertThat(game.isContinuable()).isTrue();
        }
    }

    @Test
    public void isContinuable_for_last_frame_after_first_bowl() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            Frame frame = new LastFrame(firstBowl);
            Frames frames = new Frames();
            frames.add(frame);
            BowlingGame game = new BowlingGame(PLAYER_NAME, frames);

            assertThat(game.isContinuable()).isTrue();
        }
    }

    @Test
    public void isContinuable_for_last_frame_after_second_bowl_strike() {
        for(Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            Frame frame = new LastFrame(Pin.ofStrike());
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
            Frame frame = new LastFrame(firstBowl);
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

            Frame frame = new LastFrame(firstBowl);
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

            Frame frame = new LastFrame(firstBowl);
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

    private void fillNormalFrames(Frames frames, int from, int to, Pin pin) {
        for (int frameNumber : getFrameNumbers(from, to)) {
            frames.add(new NormalFrame(frameNumber, pin));
        }
    }
}