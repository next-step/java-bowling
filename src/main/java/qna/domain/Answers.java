package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public void delete(User loginUser) throws CannotDeleteException{
        for(Answer answer:answers){
            answer.delete(loginUser);
        }
    }

    public List<DeleteHistory> deleteHistories(){
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for(Answer answer : answers){
            deleteHistories.add(answer.deleteHistory());
        }
        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public int size() {
        return answers.size();
    }
}
