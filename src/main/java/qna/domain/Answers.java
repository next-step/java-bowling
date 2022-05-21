package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void delete(List<DeleteHistory> deleteHistories) {
        this.answers.forEach(answer -> {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        });
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
