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
            print(frames);
        }
    }

    private static void print(List<Frame> frames) {
        StringBuilder sb = new StringBuilder();
        sb.append("| NAME |");
        for (int i = 1; i <= frames.size(); i++) {
            sb.append("  ").append(String.format("%02d", i)).append("  |");
        }
        sb.append(System.lineSeparator());

        sb.append("|  JSY |");
        for (Frame frame : frames) {
            sb.append(centerString(6, frame.getStatus())).append("|");
        }

        System.out.println(sb.toString());
    }

    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + ((width - s.length()) / 2) + 1) + "s", s));
    }
}
