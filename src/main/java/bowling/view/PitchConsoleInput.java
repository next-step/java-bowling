package bowling.view;

import bowling.util.NumberUtils;

import java.util.Scanner;

public class PitchConsoleInput {

    private static final String PITCH_QUESTION = "%s's turn : ";
    private static final Scanner scanner = new Scanner(System.in);

    public static int askPitch(final String name) {
        System.out.print(String.format(PITCH_QUESTION, name));
        return getUntilNumber(name, scanner.nextLine());
    }

    private static int getUntilNumber(final String name, final String pitch) {
        if (!NumberUtils.isNumber(pitch)) {
            NumberUtils.printNotNumber(pitch);
            return askPitch(name);
        }
        return Integer.parseInt(pitch);
    }
}
