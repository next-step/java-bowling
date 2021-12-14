package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author han
 */

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public Answers delete() {
        answers = answers.stream()
            .map(Answer::toDeleted)
            .collect(Collectors.toList());
        return this;
    }

    public boolean hasOwnAnswer(User loginUser) {
        return answers.stream()
            .anyMatch(answer -> answer.isOwner(loginUser));
    }

    public List<DeleteHistory> getDeleteHistory() {
        return answers.stream()
            .filter(Answer::isDeleted)
            .map(DeleteHistory::of)
            .collect(Collectors.toList());
    }
}
