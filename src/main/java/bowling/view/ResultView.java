package bowling.view;

import bowling.domain.Game;
import bowling.domain.Name;
import bowling.domain.Point;
import bowling.domain.frame.Frame;

public class ResultView {

    private static final String BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String USER_SCORE = "| %s  |  %s |  %s |  %s |  %s |  %s |  %s |  %s |  %s |  %s |  %s |";

    private static final String STRIKE_STRING = "x  ";
    private static final String EMPTY_SPACE = "   ";

    public void printScoreBoard(Game game, Name name) {
        System.out.println(BOARD);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = nameStringBuilder(name, stringBuilder);
        stringBuilder = pointStringBuilder(game, stringBuilder);
        System.out.println(stringBuilder);
    }

    private StringBuilder nameStringBuilder(Name name, StringBuilder stringBuilder){
        stringBuilder.append("| ");
        stringBuilder.append(name.name());
        stringBuilder.append("  |");
        return stringBuilder;
    }

    private StringBuilder pointStringBuilder(Game game, StringBuilder stringBuilder){
        for(int count = 1;count<=10;count++){
            stringBuilder.append("  ");
            stringBuilder.append(stringFramePoint(game, count));
            stringBuilder.append(" |");
        }
        return stringBuilder;
    }

    private String stringFramePoint(Game game, int count){
        if (count>game.frameCount()){
            return EMPTY_SPACE;
        }
        if(count==10){
            return stringFinalFramePoint(game);
        }
        return stringNormalFramePoint(game, count);
    }

    private String stringFinalFramePoint(Game game){
        Frame frame = game.gameFrames()
                .finalFrame()
                .frame();
        if(frame.firstPoint().played()== Point.NOT_PLAYED){
            return "   ";
        }
        return "***";
    }

    private String stringNormalFramePoint(Game game, int count){
        Frame frame = game.gameFrames()
                .normalFrames()
                .frames()
                .get(count-1);
        if(frame.firstPoint().played()==Point.NOT_PLAYED){
            return EMPTY_SPACE;
        }
        if(frame.isStrike()){
            return STRIKE_STRING;
        }
        if(frame.isSpare()){
            return stringSpare(frame.firstPoint().point());
        }
        if(frame.secondPoint().played()==Point.NOT_PLAYED){
            return stringSinglePoint(frame.firstPoint().point());
        }
        return frame.firstPoint().point() + "/" + frame.secondPoint().point();
    }

    private String stringSpare(int firstPoint){
        return firstPoint + "|/";
    }

    private String stringSinglePoint(int firstPoint){
        return firstPoint + "  ";
    }


}
