package menu.view;

import java.util.List;
import menu.domain.coach.Coaches;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;
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
    public void printResult(String[] categories, Coaches coaches) {
        printer.printMessage("메뉴 추천 결과입니다.");
        printer.printMessage("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        printer.printMessageUsingFormat("[ 카테고리 | %s | %s | %s | %s | %s ]", categories);
        coaches.getCoaches().forEach(coach ->
                printer.printMessageUsingFormat("[ " +coach.getName() + " | %s | %s | %s | %s | %s ]",
                        coach.getSelectedMenus().stream()
                                .map(Menu::getName)
                                .toArray(String[]::new)));

        printer.printMessage("추천을 완료했습니다.");
    }

    /**     */
    private <T> void printListUsingFormat(String format, List<T> list){
        list.forEach(t -> printer.printMessageUsingFormat("format", 1, 2, 3));
    }
}
