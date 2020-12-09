package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BowlingGame {
    public static void main(String[] args) {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            frames.add(new NormalFrame(i + 1));
        }

        frames.add(new LastFrame(10));

        Scanner scanner = new Scanner(System.in);
        int frameIndex = 0;
        while (true) {
            if (frameIndex >= frames.size()) {
                break;
            }
            Frame frame = frames.get(frameIndex);
            if (frame.isEnd()) {
                frameIndex++;
                continue;
            }

            int knockDownPins = Integer.parseInt(scanner.nextLine());
            frame.setKnockDownPins(knockDownPins);

            System.out.println("frame" + (frameIndex + 1));
            System.out.println(frame.getStatus());
        }
    }
}
