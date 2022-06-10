package bowling.view;

import bowling.domain.FramesList;
import bowling.domain.User;
import bowling.domain.Users;

import java.util.List;

import static bowling.view.StringView.*;

public class ResultView {

    public static void printInit(Users users) {
        printRound();
        users.getUsers().forEach(user -> {
            printUser(user);
            StateView.printInitExpression();
            ScoreView.printInitScore();
        });
    }

    public static void print(Users users, FramesList userFrames, int round, List<List<Integer>> userScores) {
        printRound();
        for (int i = 0; i < users.size(); i++) {
            printUser(users.getUsers().get(i));
            StateView.printState(i, userFrames, round);
            ScoreView.printScore(userScores.get(i));
        }
    }


    public static void printUser(User user) {
        System.out.print(PIPE + "  " + user.getLetters() + " " + PIPE);
    }

    public static void printRound() {
        System.out.println(MENU);
    }

}
