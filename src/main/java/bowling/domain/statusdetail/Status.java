package bowling.domain.statusdetail;

import bowling.domain.frame.DownedPin;

import java.util.List;

public interface Status {

    String interpretFrame(List<DownedPin> pins);

}
