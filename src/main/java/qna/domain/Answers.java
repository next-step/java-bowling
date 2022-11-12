package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> values = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return this.values.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

    public List<Answer> getValues() {
        return Collections.unmodifiableList(values);
    }

    public List<DeleteHistory> delete() {
        return values.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }
}
