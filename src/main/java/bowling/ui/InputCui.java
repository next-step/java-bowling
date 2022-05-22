package bowling.ui;

import static java.lang.System.out;

import java.util.Scanner;

public class InputCui {
    private static final int NAME_LENGTH = 3;

    private Scanner scanner = new Scanner(System.in);

    public InputCui() { }

    public String inputPlayerNames() {
        return inputParticipantsImpl();
    }

    private String inputParticipantsImpl() {
        out.printf("플레이어 이름은(%d english letters)?: ", NAME_LENGTH);
        String nameInitial = scanner.nextLine();

        if (!isValidNames(nameInitial)) {
            return inputParticipantsImpl();
        }

        return nameInitial;
    }

    private boolean isValidNames(String nameInitial) {
        return nameInitial.length() == NAME_LENGTH;
    }

    public int inputHitCount(int frameNumber) {
        out.printf("%d 프레임 투구 : ", frameNumber);
        int hitCount = scanner.nextInt();
        scanner.nextLine();

        return hitCount;
    }
}
