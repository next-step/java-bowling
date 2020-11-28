package bowling.domain;

import bowling.dto.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Map.Entry;

public class Game {
    private static final int MAX_FRAME_NO = 10;

    private final Map<Player, Board> boardMap = new HashMap<>();
    private final Map<Player, RollSubject> subjectMap = new HashMap<>();

    public void addPlayer(Player player, Function<Integer, Roll> rollGenerator) {
        Board board = new Board();
        RollSubject subject = new RollSubject(() -> rollGenerator.apply(board.frameNo()));
        subject.register(new BoardObserver(board));
        boardMap.put(player, board);
        subjectMap.put(player, subject);
    }

    public ScoreBoardDto exportScoreBoardDto() {
        Map<PlayerDto, RollsAndBoardDto> scoreBoard = new HashMap<>();
        subjectMap.entrySet()
                .stream()
                .map(Entry::getKey)
                .forEach(player -> scoreBoard.put(
                        player.exportPlayerDto(),
                        exportRollsAndBoardDto(player)
                ));
        return new ScoreBoardDto(scoreBoard);
    }

    private RollsAndBoardDto exportRollsAndBoardDto(Player player) {
        RollsDto rollsDto = subjectMap.get(player).exportRollsDto();
        BoardDto boardDto = boardMap.get(player).exportBoardDto();
        return new RollsAndBoardDto(rollsDto, boardDto);
    }

    public void play() {
        IntStream.range(0, MAX_FRAME_NO)
                .forEach(i -> play(this::playFrame));
        play(this::playBonus);
    }

    private void play(Consumer<Entry> consumer) {
        subjectMap.entrySet()
                .stream()
                .forEach(consumer);
    }

    private void playFrame(Entry<Player, RollSubject> entry) {
        Player player = entry.getKey();
        RollSubject subject = entry.getValue();
        subject.execute();
        boolean isFrameFinished = boardMap.get(player)
                .isFrameFinished();
        if (!isFrameFinished) {
            subject.execute();
        }
    }

    private void playBonus(Entry<Player, RollSubject> entry) {
        Player player = entry.getKey();
        RollSubject subject = entry.getValue();
        boolean isBonus = boardMap.get(player)
                .isBonus();
        if (isBonus) {
            subject.execute();
            subject.executeZero();
        }
    }
}
