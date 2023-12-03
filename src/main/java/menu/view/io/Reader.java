package menu.view.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import menu.exception.MenuException;

public class Reader {
    public int getInteger() {
        String input = Console.readLine();
        return parseInt(input);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw MenuException.INVALID_NUMBER_FORMAT.makeException();
        }
    }

    public String getString() {
        return Console.readLine();
    }

    public List<String> getStringsUsingDelimiter(String delimiter) {
        String input = Console.readLine();
        validateNotEndDelimiter(input, delimiter);
        List<String> inputs = Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
        inputs.forEach(this::validateNotBlank);
        return inputs;
    }

    private void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw MenuException.BLANK_INPUT.makeException();
        }
    }

    private void validateNotEndDelimiter(String input, String delimiter) {
        if (input.endsWith(delimiter)) {
            throw MenuException.INVALID_INPUT_FORMAT.makeException();
        }
    }
}
