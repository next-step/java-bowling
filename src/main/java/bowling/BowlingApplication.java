package bowling;

import bowling.domain.*;
import bowling.exception.NameException;
import bowling.exception.PitchException;

import static bowling.view.InputView.pitch;
import static bowling.view.InputView.playerName;
import static bowling.view.ResultView.print;

public class BowlingApplication {
    public static void main(String[] args) {
        Name name = getName();
        Player player = new Player(name);

        Frames frames = getFrames();
        print(player);

        while (frames.nonOver()) {
            Pitch pitch = getPitch(frames.currentInning().asInt());
            frames.recodePitch(pitch);

            print(player, frames);
        }
    }

    private static Pitch getPitch(int inning) {
        try {
            String pitch = pitch(inning);
            return new Pitch(Integer.parseInt(pitch));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return getPitch(inning);
        } catch (PitchException e) {
            System.out.println("0 ~ 10 사이 숫자를 입력해 주세요.  ");
            return getPitch(inning);
        }
    }

    private static Frames getFrames() {
        return Frames.init();
    }

    private static Name getName() {
        String name = playerName();
        try {
            return new Name(name);
        } catch (NameException e) {
            System.out.printf("이름(%s)을 잘못 입력했습니다. \n", name);
            return getName();
        }
    }

}
