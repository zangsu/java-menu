package menu;

import java.util.ArrayList;
import java.util.List;
import menu.domain.coach.Coach;
import menu.domain.coach.Coaches;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;
import menu.domain.selector.CategorySelector;
import menu.domain.selector.MenuSelector;

public class MenuService {
    private final CategorySelector categorySelector;
    private final MenuSelector menuSelector;

    public MenuService(CategorySelector categorySelector, MenuSelector menuSelector) {
        this.categorySelector = categorySelector;
        this.menuSelector = menuSelector;
    }

    private static long categoryCount(List<Category> selectCategories, Category category) {
        return selectCategories.stream()
                .filter(selectCategory -> selectCategory.equals(category))
                .count();
    }

    public List<Category> selectCategory() {
        List<Category> selectCategories = new ArrayList<>();
        while (selectCategories.size() < 5) {
            Category category = categorySelector.select();
            if (categoryCount(selectCategories, category) >= 2) {
                continue;
            }
            selectCategories.add(category);
        }
        return selectCategories;
    }

    public Menu selectMenu(Category category) {
        return menuSelector.select(category);
    }

    public void selectMenu(Coaches coaches, Category category) {
        coaches.consumeCoaches(coach -> selectMenuPerCoach(category, coach));
    }

    private void selectMenuPerCoach(Category category, Coach coach) {
        while (true) {
            Menu menu = selectMenu(category);
            if (coach.selectMenu(menu)) {
                return;
            }
        }
    }
}
