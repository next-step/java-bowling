package bowling.view;

import bowling.domain.Frame;
import bowling.domain.UserName;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static UserName getUserName() {
        UserName userName = null;
        try {
            System.out.print("플레이어 이름은(3 english letters)?:");
            String name = SCANNER.next();
            userName = new UserName(name);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return getUserName();
        }
        return userName;
    }

    public static void enterBowlPins(Frame frame) {
        try {
            System.out.print(frame.findCurrentIndex() + "프레임 투구: ");
            int downPin = SCANNER.nextInt();
            frame.bowl(downPin);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            enterBowlPins(frame);
        }
    }
}
