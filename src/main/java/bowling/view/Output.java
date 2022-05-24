package bowling.view;

import bowling.domain.PlayFrames;

import static bowling.util.Const.HEADER_STR;

public class Output {
    public static void print(String payload) {
        System.out.println(payload);
    }

    public static void printFrames(PlayFrames playFrames) {
        playFrames.playFrames()
                .stream()
                .forEach(playFrame -> {
                    System.out.println("Frame: " + playFrame.number());
                    System.out.println(HEADER_STR);
                    System.out.println(playFrame.value());
                });
    }
}
