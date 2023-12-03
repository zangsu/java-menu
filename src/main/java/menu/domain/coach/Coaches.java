package menu.domain.coach;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import menu.exception.MenuException;

public class Coaches {
    private static final int MIN_COACHES_NUMBER = 2;
    private static final int MAX_COACHES_NUMBER = 5;
    private final List<Coach> coaches;

    public Coaches(List<String> coachNames) {
        validateCoachNames(coachNames);
        coaches = coachNames.stream()
                .map(Coach::new)
                .collect(Collectors.toList());
    }

    private void validateCoachNames(List<String> coachNames) {
        validateCoachesNumber(coachNames.size());
        validateDuplicateCoachNames(coachNames);
    }

    private void validateCoachesNumber(int size) {
        if (size < MIN_COACHES_NUMBER) {
            throw MenuException.MIN_COACHES_NUMBER.makeException();
        }
        if (size > MAX_COACHES_NUMBER) {
            throw MenuException.MAX_COACHES_NUMBER.makeException();
        }
    }

    private void validateDuplicateCoachNames(List<String> coachNames) {
        int distinctSize = (int) coachNames.stream()
                .distinct()
                .count();
        if (distinctSize != coachNames.size()) {
            throw MenuException.DUPLICATE_COACH_NAME.makeException();
        }
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void consumeCoaches(Consumer<Coach> consumer) {
        coaches.forEach(consumer);
    }

}
