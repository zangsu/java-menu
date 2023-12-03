package menu.view;

import java.util.List;
import menu.view.io.Printer;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";
    private final Printer printer = new Printer();

    public void printException(Exception e){
        printer.printMessage(EXCEPTION_PREFIX + e.getMessage());
    }

    public void newLine(){
        printer.printMessage("");
    }

    public void printStartMessage(){
        printer.printMessage("점심 메뉴 추천을 시작합니다.");
        newLine();
    }

    //todo
    public void printResult() {
    }

    /**     */
    private <T> void printListUsingFormat(List<T> list){
        list.forEach(t -> printer.printMessageUsingFormat("FORMAT", 1, 2, 3));
    }
}
