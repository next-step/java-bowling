package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Embeddable
public class Answers implements Iterable<Answer> {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer, Question question) {
        answer.toQuestion(question);
        answers.add(answer);
    }

    public int count() {
        return answers.size();
    }

    public DeleteHistories deleteAll(User loginUser) {
        return new DeleteHistories(createDeleteHistories(loginUser));
    }

    private List<DeleteHistory> createDeleteHistories(User loginUser) {
        return answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }

    @Override
    public void forEach(Consumer<? super Answer> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Answer> spliterator() {
        return Iterable.super.spliterator();
    }
}
