package bowling.game;

import bowling.domain.*;
import bowling.exception.CannotCreateException;

import java.util.Scanner;

public class BowlingGame {

    private Frames frames;

    public void start() {
        frames = new Frames();
    }

    public void play() throws CannotCreateException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("플레이어 이름은?");
        String name = scanner.nextLine();


        while (!frames.isEnd()) {
            System.out.println(String.format("%d 프레임 투구:", frames.lastIndex()));

            Frame curFrame = new NormalFrame();
            if (frames.lastIndex() == 10) {
                curFrame = new FinalFrame();
            }
            PinScore score = new PinScore(Integer.valueOf(scanner.nextLine()));
            curFrame.firstShot(score);

            if (curFrame.status() != FrameStatus.STRIKE) {
                System.out.println(String.format("%d 프레임 투구:", frames.lastIndex()));
                PinScore secondScore = new PinScore(Integer.valueOf(scanner.nextLine()));
                curFrame.secondShot(secondScore);
            }


            frames.add(curFrame);

            if(frames.lastIndex() == 14){
                break;
//                frames.endGame();
            }

        }
        System.out.println(frames);

    }
}
