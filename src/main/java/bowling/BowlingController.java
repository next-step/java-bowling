package bowling;

import bowling.domain.member.Member;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.io.PrintWriter;
import java.util.Scanner;

public class BowlingController {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(System.out, true);
        InputView inputView = new InputView(new Scanner(System.in), output);
        ResultView resultView = new ResultView(output);

        Member member = Member.of(inputView.enterMemberName());
        showFrames(resultView, member);
        startBowling(inputView, resultView, member);

        output.close();
    }

    private static void startBowling(InputView inputView, ResultView resultView, Member member) {
        while (!member.isFinished()) {
            member.throwBall(inputView.enterScore(member.getCurrentFrameNumber()));
            resultView.showFrames(member.getName(), member.getFrames());
        }
    }

    private static void showFrames(ResultView resultView, Member member) {
        resultView.showFrames(member.getName(), member.getFrames());
    }
}
