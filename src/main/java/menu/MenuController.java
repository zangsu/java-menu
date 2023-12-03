package menu;

import java.util.List;
import menu.domain.coach.Coach;
import menu.domain.coach.Coaches;
import menu.domain.menu.Menu;
import menu.exception.handler.ExceptionHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private final ExceptionHandler handler;

    public MenuController(ExceptionHandler handler) {
        this.handler = handler;
    }

    public void run(){
        outputView.printStartMessage();
        Coaches coaches = handler.get(this::getCoaches);
        getBannedMenu(coaches);
        //추천 메뉴 출력
        //종료
    }

    private Coaches getCoaches() {
        List<String> coachesName = inputView.getCoachesName();
        return new Coaches(coachesName);
    }

    private void getBannedMenu(Coaches coaches) {
        coaches.getCoaches().forEach(
                coach -> handler.run(() -> getBannedMenuEachCoach(coach))
        );
    }

    private void getBannedMenuEachCoach(Coach coach) {
        List<String> banedMenuNames = inputView.getBanedMenu(coach.getName());
        banedMenuNames.stream()
                .map(Menu::from)
                .forEach(coach::addBanedMenu);
        outputView.newLine();
    }
}
