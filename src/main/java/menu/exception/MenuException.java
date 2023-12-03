package menu.exception;

public enum MenuException {
    INVALID_NUMBER_FORMAT("숫자를 입력해 주세요"),
    INVALID_INPUT_FORMAT("입력 형식이 잘못되었습니다."),

    BLANK_INPUT("입력값이 비어 있습니다."),
    NO_SUCH_MENU("존재하지 않는 메뉴입니다."),
    ;

    private final String message;

    MenuException(String message) {
        this.message = message;
    }

    public IllegalArgumentException makeException(){
        return new IllegalArgumentException(this.message);
    }

}
