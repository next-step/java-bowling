package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistory deleteHistory){
        deleteHistories.add(deleteHistory);
    }

    public void addAll(List<DeleteHistory> list){
        deleteHistories.addAll(list);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
