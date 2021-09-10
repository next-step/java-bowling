package bowling.view;

import bowling.util.NumberUtils;

import java.util.Scanner;

public class PitchConsoleInput {

    private static final String PITCH_QUESTION = "%d프레임 투구 : ";
    private static final Scanner scanner = new Scanner(System.in);

    public static int askPitch(final int currentFrame) {
        System.out.print(String.format(PITCH_QUESTION, currentFrame));
        return getUntilNumber(currentFrame, scanner.nextLine());
    }

    private static int getUntilNumber(final int currentFrame, final String pitch) {
        if (!NumberUtils.isNumber(pitch)) {
            NumberUtils.printNotNumber(pitch);
            return askPitch(currentFrame);
        }
        return Integer.parseInt(pitch);
    }
}
