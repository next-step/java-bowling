package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public DeleteHistories delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for(Answer answer: this.answers){
            deleteHistories.add(answer.delete(loginUser));
        }
        return new DeleteHistories(deleteHistories);
    }

}
