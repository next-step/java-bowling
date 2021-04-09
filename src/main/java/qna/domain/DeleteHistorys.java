package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistorys {

    private final List<DeleteHistory> deleteHistoryList;

    private DeleteHistorys(List<DeleteHistory> deleteHistoryList) {
        this.deleteHistoryList = deleteHistoryList;
    }

    private DeleteHistorys() {
        this(new ArrayList<>());
    }

    public static DeleteHistorys of(List<DeleteHistory> deleteHistoryList){
        return new DeleteHistorys(deleteHistoryList);
    }

    public static DeleteHistorys of(){
        return new DeleteHistorys();
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistoryList.add(deleteHistory);
    }

    public List<DeleteHistory> toList() {
        return deleteHistoryList;
    }

    public int size() {
        return deleteHistoryList.size();
    }


}
