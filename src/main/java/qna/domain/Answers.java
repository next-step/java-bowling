package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void addToAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public boolean hasOtherOwnerAnswers(User user) {
        return this.answers.stream().anyMatch(answer -> !answer.isOwner(user));
    }

    public DeleteHistories deleteAnswers(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            DeleteHistory deleteHistory = answer.deleteAnswer(loginUser);
            deleteHistories.add(deleteHistory);
        }
        return DeleteHistories.of(deleteHistories);
    }
}
