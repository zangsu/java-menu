package menu.view;

import java.util.List;
import java.util.stream.Collectors;
import menu.domain.coach.Coaches;
import menu.domain.menu.Category;
import menu.view.io.Printer;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";
    private final Printer printer = new Printer();

    public void printException(Exception e) {
        printer.printMessage(EXCEPTION_PREFIX + e.getMessage());
    }

    public void newLine() {
        printer.printMessage("");
    }

    public void printStartMessage() {
        printer.printMessage("점심 메뉴 추천을 시작합니다.");
        newLine();
    }

    public void printResult(List<Category> categories, Coaches coaches) {
        printer.printMessage("메뉴 추천 결과입니다.");
        printer.printMessage("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        printer.printMessageUsingFormat("[ 카테고리 | %s ]", categories.stream()
                .map(Category::getName)
                .collect(Collectors.joining(" | ")));
        coaches.getCoaches().forEach(coach ->
                printer.printMessageUsingFormat("[ %s | %s ]", coach.getName(),
                        String.join(" | ", coach.getSelectedMenuNames())
                ));

        printer.printMessage("추천을 완료했습니다.");
    }
}
