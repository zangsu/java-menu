package menu.view;

import java.util.List;
import menu.view.io.Printer;
import menu.view.io.Reader;

public class InputView {
    private static final String DELIMITER = ",";
    private final Reader reader = new Reader();
    private final Printer printer = new Printer();


    public List<String> getCoachesName() {
        printer.printMessage("코치의 이름을 입력해 주세요. (, 로 구분)");
        return reader.getStringsUsingDelimiter(DELIMITER);
    }

    public List<String> getBanedMenu(String coachName) {
        printer.printMessage(coachName + "(이)가 못 먹는 메뉴를 입력해 주세요.");
        return reader.getStringsUsingDelimiter(DELIMITER);
    }
}
