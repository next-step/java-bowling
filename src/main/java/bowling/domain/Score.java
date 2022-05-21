package bowling.domain;

import bowling.view.Output;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class Score {
    private final ScoreType scoreType;
    private final int hit;
//
//    public Score() {
//        this.scoreType = ScoreType.INIT;
//        this.hit = 0;
//    }

    public Score(int hit, Optional<Integer> prevHit) {
        this.hit = hit;
        this.scoreType = ScoreType.of(hit, prevHit);
    }

//    public static void plays(List<Optional<Score>> scores) {
//        ListIterator<Optional<Score>> iterator = scores.listIterator();
//        while(iterator.hasNext()) {
////            int index = iterator.nextIndex();
//            Optional<Score> score = iterator.next();
//            if (score.isPresent()) {
//                //
//
//            }
//            score.play();
////            Score score = this.play(index, Optional.ofNullable(this.scores.get(index).hit));
//            if (score.hasNext()) {
//                continue;
//            }
//            iterator.next();
//        }
//    }

    public static Score play(Optional<Score> prevScore) {
        int hit = Player.pitch();
        if (prevScore.isPresent()) {
            return new Score(hit, Optional.of(prevScore.get().hit));
        }
        return new Score(hit, Optional.empty());
//        this.save(index, score);
//        Output.print("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
//        Output.print(Score.payload(Optional.ofNullable(this)));
//        return score;
    }

    public static String payload(Optional<Score> score) {
        if (score.isEmpty()) {
            return String.format("%-4s", " ");
        }
        if (score.get().scoreType == ScoreType.STRIKE) {
            return String.format("%-4s", "X");
        }
        if (score.get().scoreType == ScoreType.SECOND) {
            return String.format("%-4s", score.get().hit);
        }
        if (score.get().scoreType == ScoreType.GUTTER) {
            return String.format("%-4s", score.get().hit);
        }
        if (score.get().scoreType == ScoreType.MISS) {
            return String.format("%-4s", score.get().hit);
        }
        if (score.get().scoreType == ScoreType.SPARE) {
            return String.format("%-4s", score.get().hit);
        }
        throw new RuntimeException("unreachable " + score.get());
    }

    public boolean hasNext() {
        return !(this.scoreType == ScoreType.STRIKE || this.scoreType == ScoreType.SECOND);
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreType=" + scoreType +
                ", hit=" + hit +
                '}';
    }
}
