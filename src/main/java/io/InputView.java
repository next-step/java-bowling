package io;

import domain.Pins;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static Pins askKnockDownedPins(Integer currentIndex) {
        System.out.println((currentIndex + 1) + " 프레임 투구 : ");
        int downPins = scanner.nextInt();
        scanner.nextLine();
        return Pins.of(downPins);
    }

    public static String askPlayerInitial() {
        System.out.println("플레이어 이름은(3 english letters)?");
        return scanner.nextLine();
    }
}
