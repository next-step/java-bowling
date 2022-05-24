package qna.domain.answer;

import org.hibernate.annotations.Where;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * 다른 회원이 쓴 답변이 있다면 true, 그렇지 않다면 false를 리턴합니다.
     *
     * @param user 확인할 회원
     * @return 다른 회원이 쓴 답변이 있다면 true, 그렇지 않다면 false
     */
    public boolean hasWrittenByOthers(User user) {
        return this.answers.stream()
                .anyMatch(answer -> !answer.isOwner(user));
    }

    public List<DeleteHistory> delete() {
        return this.answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
