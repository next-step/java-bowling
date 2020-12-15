package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created : 2020-12-14 오전 9:59
 * Developer : Seo
 */
public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean hasOthers(User loginUser) {
        if (! answers.isEmpty()) {
            return ! answers.stream().allMatch(answer -> answer.isOwner(loginUser));
        }
        return false;
    }

    public List<DeleteHistory> delete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            answer.setDeleted();
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }

    public int size() {
        return this.answers.size();
    }
}
