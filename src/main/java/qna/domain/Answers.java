package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public int size() {
        return answers.size();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean delete(User loginUser) throws CannotDeleteException {
        for(Answer answer:answers){
            answer.delete(loginUser);
        }
        return true;
    }

    public List<DeleteHistory> deleteHistories(DeleteHistory questionDeleteHistory) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(questionDeleteHistory);
        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteHistory());
        }
        return deleteHistories;
    }
}
