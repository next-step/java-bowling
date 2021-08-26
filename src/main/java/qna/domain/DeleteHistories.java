package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    public DeleteHistories(){
        deleteHistories = new ArrayList<>();
    }

    public void addQuestionHistory(Question question){
        this.deleteHistories.add(new DeleteHistory(question, question.getWriter()));
    }

    public void addAnswerHistory(Question question){
        question.getAnswers().forEach(answer -> {
            this.deleteHistories.add(new DeleteHistory(answer, answer.getWriter()));
        });
    }

    public List<DeleteHistory> getDeleteHistories(){
        return this.deleteHistories;
    }
}
