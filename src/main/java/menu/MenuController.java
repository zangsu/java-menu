package menu;

import java.util.List;
import java.util.stream.Collectors;
import menu.domain.coach.Coach;
import menu.domain.coach.Coaches;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;
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
        getBannedMenu(coaches);
        recommendMenu(coaches);
        //종료
    }

    private void recommendMenu(Coaches coaches) {
        List<Category> selectCategories = menuService.selectCategory();
        selectCategories.forEach(
                category -> selectMenu(coaches, category)
        );
        outputView.printResult(selectCategories, coaches);
    }

    private void selectMenu(Coaches coaches, Category category) {
        menuService.selectMenu(coaches, category);
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
        List<Menu> bannedMenus = banedMenuNames.stream()
                .map(Menu::from)
                .collect(Collectors.toList());
        coach.addBanedMenu(bannedMenus);
        outputView.newLine();
    }
}
