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
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        if (answers == null) {
            throw new NullPointerException("주어진 질문 리스트가 Null 입니다.");
        }
        this.answers = answers;
    }

    public Answers add(Answer answer) {
        if (answer == null) {
            throw new NullPointerException("주어진 질문이 Null 입니다.");
        }
        answers.add(answer);
        return this;
    }

    public Answer get(int index) {
        if (index < 0 || index > answers.size()) {
            throw new IndexOutOfBoundsException("리스트의 범위 밖입니다.");
        }
        return answers.get(index);
    }

    public int size() {
        return answers.size();
    }

    public List<DeleteHistory> deleteByUser(User user) throws CannotDeleteException {
        if (user == null) {
            throw new NullPointerException("주어진 유저가 Null 입니다.");
        }

        if (!isAllAnswersOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteByUser(user));
        }
        return deleteHistories;
    }

    private boolean isAllAnswersOwner(User user) {
        return answers.stream().allMatch(answer -> answer.isOwner(user));
    }

    public boolean isAllDeleted() {
        return answers.stream().allMatch(Answer::isDeleted);
    }
}
