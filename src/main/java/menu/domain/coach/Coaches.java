package menu.domain.coach;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import menu.exception.MenuException;

public class Coaches {
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

    private void validateDuplicateCoachNames(List<String> coachNames) {
        int distinctSize = (int)coachNames.stream()
                .distinct()
                .count();
        if(distinctSize != coachNames.size()){
            throw MenuException.DUPLICATE_COACH_NAME.makeException();
        }
    }

    private void validateCoachesNumber(int size) {
        if(size < 2){
            throw MenuException.MIN_COACHES_NUMBER.makeException();
        }
        if(size > 5){
            throw MenuException.MAX_COACHES_NUMBER.makeException();
        }
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void consumeCoaches(Consumer<Coach> consumer){
        coaches.forEach(consumer);
    }

}
