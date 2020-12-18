package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistoriesList;

    public DeleteHistories() {
        deleteHistoriesList = new ArrayList<>();
    }

    private DeleteHistories(List<DeleteHistory> deleteHistoriesList) {
        this.deleteHistoriesList = deleteHistoriesList;
    }

    public DeleteHistories add(DeleteHistory deleteHistory) {
        deleteHistoriesList.add(deleteHistory);
        return new DeleteHistories(deleteHistoriesList);
    }

    public int getSize() {
        return deleteHistoriesList.size();
    }

    public List<DeleteHistory> getDeleteHistoriesList() {
        return deleteHistoriesList;
    }
}
