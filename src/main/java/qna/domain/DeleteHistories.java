package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();
    public DeleteHistories(){}
    public DeleteHistories(List<DeleteHistory> deleteHistories){
        this.deleteHistories = deleteHistories;
    }
    public static DeleteHistories from(DeleteHistory deleteHistory){
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(deleteHistory);
        return deleteHistories;
    }
    public void add(DeleteHistory deleteHistory){
        deleteHistories.add(deleteHistory);
    }
    public void addAll(DeleteHistories deleteHistories){
        this.deleteHistories.addAll(deleteHistories.deleteHistories());
    }
    public List<DeleteHistory> deleteHistories(){
        return Collections.unmodifiableList(deleteHistories);
    }
}
