import bowling.frame.Frames;
import bowling.pin.Pin;

import java.util.Scanner;

public class BowlingConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("플레이어 이름은(3 english letters)?: ");
        String name = sc.nextLine();

        Frames frames = Frames.init();
        while (!frames.isEnd()) {
            System.out.print("투구: ");
            Pin hitCount = Pin.from(Integer.parseInt(sc.nextLine()));
            frames.pitch(hitCount);

            System.out.println(frames);
        }
    }
}
