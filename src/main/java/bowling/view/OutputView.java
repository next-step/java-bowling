package bowling.view;

import bowling.domain.*;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {

    private static final String USERNAME_ASK_QST = "플레이어 이름은(3 english letters)?:";

    private static final String KNOWN_DOWN_PIN_NUMBER_ASK_QST = "\n %d프레임 투구 : ";

    private OutputView(){

    }

    public static void printUsernameAskQst(){
        System.out.println(USERNAME_ASK_QST);
    }
    public static void printPinAskQst(BallingRound round){
        System.out.printf(KNOWN_DOWN_PIN_NUMBER_ASK_QST,round.getRoundNumber());
    }


    public static void printScore(Balling balling , Username username) {
        System.out.print("\n | NAME |");
        IntStream.range(0,BallingRound.LAST_ROUND_NUM)
                .mapToObj((roundNum)->{
                    if (roundNum + 1< 10){
                        return "0" + roundNum + 1 + "  |";
                    }
                    return roundNum + 1 + "  |";
                })
                .forEach(System.out::print);
        System.out.print("\n | " + username.getName() + " |");
        IntStream.range(0,BallingRound.LAST_ROUND_NUM)
                .mapToObj((roundNum)->{
                    BallingRound round = balling.getRounds().get(new Position(roundNum));
                    if (round ==null){
                        return "    |";
                    }
                    Scores scores = round.getScores();
                    if (scores.isStrike()){
                       return  "  " + "X" + "  |";
                    }
                    if (scores.isSpare()){
                        return "  " + scores.getScores().get(0).getScore() +"  /  |";
                    }else{
                        return "  " + scores.getScores().get(0)  +"  |";
                    }
                })
                .forEach(System.out::print);

    }

}
