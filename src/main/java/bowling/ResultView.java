package bowling;

import java.util.Iterator;
import java.util.List;

public class ResultView {

    public static void printResult(String name, List<NormalFrame> frames) {
        printRoundTemplate();
        printUserName(name);
        printFrames(frames);
    }
    private static void printRoundTemplate() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printUserName(String name) {
        System.out.print("|");
        System.out.printf("%5s ",name);
    }

    private static void printFrames(List<NormalFrame> frames) {
        Iterator<NormalFrame> iterator = frames.iterator();
        while (iterator.hasNext()) {
            NormalFrame frame = iterator.next();
            System.out.print("|");
            if (frame.getValues().size() == 2) {
                System.out.printf("%2s",frame.getValues().get(0).getFalledPins());
                System.out.print("|");
                if (frame.getValues().get(1).getFalledPins() == 10) {
                    System.out.printf("%-3s", "/");
                } else {
                    System.out.printf("%-3s",frame.getValues().get(1).getFalledPins());
                }
            } else {
                frame.getValues().forEach(pins -> System.out.printf("  %-3s ", pins.getResult()));
            }
        }

        int other = 11 - frames.size();
        for (int i = 0; i < other; i++) {
            System.out.print("|      ");
        }
        System.out.println();
    }
}
