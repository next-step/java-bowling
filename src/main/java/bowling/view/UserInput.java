package bowling.view;

import java.util.Scanner;

public class UserInput {
    private Scanner scanner;

    public UserInput() {
        this.scanner = new Scanner(System.in);
    }

    public String readPlayerName() {
        return getStringFromStdin("플레이어 이름은(3 english letters)?: ");
    }

    public int readCountOfFallDownPins(int frameNo) {
        return getNumberFromStdin(frameNo + " 프레임 투구 : ");
    }

    private String getStringFromStdin(String displayText) {
        System.out.print(displayText);
        return scanner.nextLine();
    }

    private int getNumberFromStdin(String displayText) {
        System.out.print(displayText);
        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    protected void finalize() throws Throwable {
        this.scanner.close();
        super.finalize();
    }


}
