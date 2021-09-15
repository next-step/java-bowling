package bowling.view;

import bowling.domain.Frame;
import bowling.domain.UserName;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static UserName getUserName() {
        while (true) {
            try {
                return makeUserName();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static UserName makeUserName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        String name = SCANNER.next();
        UserName userName = new UserName(name);
        return userName;
    }

    public static void enterBowlPins(Frame frame) {
        while (true) {
            try {
                downPins(frame);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void downPins(Frame frame) {
        System.out.print(frame.findCurrentIndex() + "프레임 투구: ");
        int downPin = SCANNER.nextInt();
        frame.bowl(downPin);
    }

}
