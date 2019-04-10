package com.hyoj.bowling.console;

import java.util.Scanner;

public class InputConsole {
    private static final Scanner scanner = new Scanner(System.in);

    public static String enterPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)? : ");
        return scanner.next();
    }

    public static int enterKnockDownPinsCount(int frameTimes) {
        System.out.print(frameTimes + " 프레임 투구 : ");
        return scanner.nextInt();
    }
}
