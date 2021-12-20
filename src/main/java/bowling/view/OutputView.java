package bowling.view;

import bowling.domain.state.State;
import bowling.dto.BoardDto;
import bowling.dto.FrameResultDto;
import bowling.dto.GameResultDto;
import bowling.view.enums.StateView;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String EMPTY_FRAME_RESULT_STRING = "";
    private static final String EMPTY_SCORE_RESULT_STRING = "";
    private static final String BOARD_ROUND_FORMAT = "| NAME |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |";
    private static final String SCORE_FORMAT = "|      |  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|";
    private static final String RESULT_FORMAT = "  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|";
    private static final String NAME_FORMAT = "|  %-3s |";
    private static final int ROUND_MAX_SIZE = 10;
    private static final int NO_SCORE_VALUE = -1;

    public void renderBoard(BoardDto boardDto) {
        System.out.println(String.format(BOARD_ROUND_FORMAT, allRoundToIntArray(boardDto)));
        for (GameResultDto gameResultDto : boardDto.getGameResultsDto().getValues()) {
            System.out.println(String.format(NAME_FORMAT, userName(gameResultDto)) + String.format(RESULT_FORMAT, gameResultToStringArray(gameResultDto)));
            System.out.println(String.format(SCORE_FORMAT, scoresToStringArray(gameResultDto)));
        }
    }

    private String[] scoresToStringArray(GameResultDto gameResultDto) {
        int totalScore = 0;
        String[] results = new String[ROUND_MAX_SIZE];
        List<Integer> scoreResults = createScoreResults(gameResultDto);
        for (int i = 0; i < scoreResults.size(); i++) {
            totalScore += scoreResults.get(i);
            results[i] = String.valueOf(totalScore);
        }
        for (int i = scoreResults.size(); i < ROUND_MAX_SIZE; i++) {
            results[i] = EMPTY_SCORE_RESULT_STRING;
        }
        return results;
    }

    private List<Integer> createScoreResults(GameResultDto gameResultDto) {
        List<FrameResultDto> frameResultDtos = gameResultDto.getFrameResultsDto().getFrameResultDtos();
        List<Integer> scoreResults = frameResultDtos.stream()
                .map(FrameResultDto::getScore)
                .filter(score -> score != NO_SCORE_VALUE)
                .collect(Collectors.toList());
        return scoreResults;
    }

    private String[] gameResultToStringArray(GameResultDto gameResultDto) {
        String[] results = new String[ROUND_MAX_SIZE];
        List<String> frameViewResults = createFrameResults(gameResultDto);
        for (int i = 0; i < ROUND_MAX_SIZE; i++) {
            results[i] = findFrameViewResultByIndex(frameViewResults, i);
        }
        return results;
    }

    private List<String> createFrameResults(GameResultDto gameResultDto) {
        List<FrameResultDto> frameResultDtos = gameResultDto.getFrameResultsDto().getFrameResultDtos();
        List<String> frameViewResults = frameResultDtos
                .stream()
                .map(FrameResultDto::getStates)
                .map(this::statesToViewFormat)
                .collect(Collectors.toList());
        return frameViewResults;
    }

    private String findFrameViewResultByIndex(List<String> frameViewResults, int index) {
        if (0 <= index && index < frameViewResults.size()) {
            return frameViewResults.get(index);
        }

        return EMPTY_FRAME_RESULT_STRING;
    }

    private String statesToViewFormat(List<State> states) {
        return states.stream()
                .map(state -> {
                    StateView stateView = StateView.valueOf(state);
                    return stateView.convert(state);
                }).collect(Collectors.joining("|"));

    }

    private String userName(GameResultDto gameResultDto) {
        return gameResultDto.getName();
    }

    private Integer[] allRoundToIntArray(BoardDto boardDto) {
        return boardDto.getAllRounds().toArray(new Integer[ROUND_MAX_SIZE]);
    }
}
