package menu;

import java.util.List;
import menu.domain.coach.Coach;
import menu.domain.coach.Coaches;
import menu.domain.menu.Category;
import menu.domain.selector.RandomCategorySelector;
import menu.domain.selector.RandomMenuSelector;
import menu.exception.handler.ExceptionHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final MenuService menuService = new MenuService(new RandomCategorySelector(), new RandomMenuSelector());

    private final ExceptionHandler handler;

    public MenuController(ExceptionHandler handler) {
        this.handler = handler;
    }

    public void run() {
        outputView.printStartMessage();
        Coaches coaches = handler.get(this::getCoaches);
        banMenu(coaches);
        recommendMenu(coaches);
        //종료
    }

    private Coaches getCoaches() {
        List<String> coachesName = inputView.getCoachesName();
        return new Coaches(coachesName);
    }

    private void banMenu(Coaches coaches) {
        coaches.consumeCoaches(this::banMenuWithHandling);
    }

    private void banMenuWithHandling(Coach coach) {
        handler.run(() -> banMenuPerCoach(coach));
    }

    private void banMenuPerCoach(Coach coach) {
        List<String> banedMenuNames = inputView.getBanedMenu(coach.getName());
        menuService.banMenu(coach, banedMenuNames);
    }

    private void recommendMenu(Coaches coaches) {
        List<Category> selectCategories = menuService.selectCategory();
        selectCategories.forEach(
                category -> recommendMenus(coaches, category)
        );
        outputView.printResult(selectCategories, coaches);
    }

    private void recommendMenus(Coaches coaches, Category category) {
        menuService.recommendMenus(coaches, category);
    }
}
