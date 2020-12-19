package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers() {

    }

    public List<Answer> add(Answer answer) {
        this.answers.add(answer);
        return Collections.unmodifiableList(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public DeleteHistory deleteHistory(User loginUser) throws CannotDeleteException {
        DeleteHistory deleteHistory = new DeleteHistory();
        for (Answer answer : Collections.unmodifiableList(this.answers)) {
            answer.validateNotMatchAnswerUser(loginUser);
            deleteHistory = answer.delete();
        }
        return deleteHistory;
    }

    public int size() {
        return this.getAnswers().size();
    }
}
