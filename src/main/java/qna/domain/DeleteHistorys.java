package qna.domain;


import java.util.List;
import java.util.Objects;

public class DeleteHistorys {

    private final List<DeleteHistory> deleteHistoryList;

    private DeleteHistorys(List<DeleteHistory> deleteHistoryList) {
        this.deleteHistoryList = deleteHistoryList;
    }


    public static DeleteHistorys of(List<DeleteHistory> deleteHistoryList){
        return new DeleteHistorys(deleteHistoryList);
    }

    public List<DeleteHistory> toList() {
        return deleteHistoryList;
    }

    public int size() {
        return deleteHistoryList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistorys that = (DeleteHistorys) o;
        return Objects.equals(deleteHistoryList, that.deleteHistoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistoryList);
    }
}
