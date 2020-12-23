package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> histories = new ArrayList<>();

    public void add(DeleteHistory deleteHistory){
        histories.add(deleteHistory);
    }

    public List<DeleteHistory> getHistories() {
        return histories;
    }
}
