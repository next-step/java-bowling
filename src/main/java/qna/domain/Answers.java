package qna.domain;

import org.hibernate.annotations.Where;

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
    private List<Answer> values = new ArrayList<>();

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public Answers() {
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public int size() {
        return values.size();
    }

    public void delete(User loginUser) {
        values.forEach(answer -> answer.delete(loginUser));
    }

    public List<DeleteHistory> deleteHistories() {
        return values.stream()
                .filter(Answer::isDeleted)
                .map(Answer::createDeleteHistory)
                .collect(Collectors.toUnmodifiableList());
    }
}
