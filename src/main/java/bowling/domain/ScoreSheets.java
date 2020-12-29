package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreSheets {

    private List<ScoreSheet> sheets;

    public ScoreSheets(List<Player> players) {
        this.sheets = players.stream().map(DefaultScoreSheet::new).collect(Collectors.toList());
    }

    public boolean isAllMarked(){
        return !sheets.stream()
                .anyMatch(sheet -> !sheet.isAllMarked());

    }

    public FrameSet nextFrameSet() {
        return FrameSet.of(sheets.stream()
                .map(ScoreSheet::nextFrame)
                .collect(Collectors.toList())
        );
    }

    public List<ScoreSheetReader> getReaders() {
        return sheets.stream()
                .map(sheet -> sheet.getReader())
                .collect(Collectors.toList());
    }
}
