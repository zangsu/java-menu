package menu.exception;

public enum MenuException {
    INVALID_NUMBER_FORMAT("숫자를 입력해 주세요"),
    INVALID_INPUT_FORMAT("입력 형식이 잘못되었습니다."),

    BLANK_INPUT("입력값이 비어 있습니다."),
    NO_SUCH_MENU("존재하지 않는 메뉴입니다."),
    DUPLICATE_MENU("중복된 메뉴가 있습니다."),
    MIN_COACHES_NUMBER("코치는 최소 2명 이상이어야 합니다."),
    MAX_COACHES_NUMBER("코치는 최대 5명 이하이어야 합니다."),

    DUPLICATE_COACH_NAME("중복된 코치 이름이 있습니다."),
    INVALID_COACH_NAME_LENGTH("코치 이름은 2글자 이상 4글자 이하이어야 합니다."),

    MAX_BANNED_MENU("못 먹는 메뉴는 최대 2개까지만 가능합니다."),
    CANT_EAT_MENU("못 먹는 메뉴는 선택할 수 없습니다."),
    INVALID_CATEGORY_NUMBER("존재하지 않는 카테고리입니다."),
    CANT_SELECT_MORE_MENU("더 이상 선택할 수 없습니다."),
    ALREADY_BANNED_MENU("이미 못 먹는 메뉴를 선택했습니다.");


    private final String message;

    MenuException(String message) {
        this.message = message;
    }

    public IllegalArgumentException makeException() {
        return new IllegalArgumentException(this.message);
    }

    public String getMessage() {
        return message;
    }
}
