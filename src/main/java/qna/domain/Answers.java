package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {
    private boolean deleted = false;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> deleteAllBy(User user) throws CannotDeleteException {
        if (!answers.stream().allMatch(answer -> answer.isOwner(user))) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            DeleteHistory deleteHistory = answer.deleteBy(user);
            deleteHistories.add(deleteHistory);
        }

        deleted = true;

        return deleteHistories;
    }
}
