package bowling;

import bowling.domain.Frames;
import bowling.domain.Pitch;
import bowling.domain.Player;
import bowling.view.FramesConsoleOutput;
import bowling.view.PitchConsoleInput;
import bowling.view.PlayerConsoleInput;

public class BowlingGame {

    public static void main(String[] args) {
        Player player = new Player(PlayerConsoleInput.askName());
        Frames frames = new Frames();
        FramesConsoleOutput.print(player.name(), frames.allFramePitchValues());
        do {
            frames.addPitch(new Pitch(PitchConsoleInput.askPitch(frames.currentFrameNumber())));
            FramesConsoleOutput.print(player.name(), frames.allFramePitchValues());
        } while (!frames.isEnd());
    }

}
