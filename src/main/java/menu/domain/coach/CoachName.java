package menu.domain.coach;

import menu.exception.MenuException;

public class CoachName {
    private static final int MIN_COACH_NAME = 2;
    private static final int MAX_COACH_NAME = 4;
    private final String name;

    public CoachName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        validateNameLength(name);
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_COACH_NAME || name.length() > MAX_COACH_NAME) {
            throw MenuException.INVALID_COACH_NAME_LENGTH.makeException();
        }
    }

    public String getName() {
        return name;
    }
}
