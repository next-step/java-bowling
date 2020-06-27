package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers;

    private Answers() {
        this.answers = new ArrayList<>();
    }

    public static Answers of() {
        return new Answers();
    }

    public void addToAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public boolean hasOtherOwnerAnswers(User user) {
        for (Answer answer : this.answers) {
            if (answer.isNotOwner(user)) {
                return true;
            }
        }
        return false;
    }

    public List<DeleteHistory> generateDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            DeleteHistory deleteHistory = DeleteHistory.of(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
            deleteHistories.add(deleteHistory);
        }
        return deleteHistories;
    }

    public void deleteAnswers() {
        for (Answer answer : this.answers) {
            answer.deleteAnswer();
        }
    }
}
