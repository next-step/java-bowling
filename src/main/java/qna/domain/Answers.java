package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(Answer answer) {
        return new Answers(add(answer));
    }

    public static List<Answer> add(Answer answer) {
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
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

}
