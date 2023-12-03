package menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.coach.Coach;
import menu.domain.coach.Coaches;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;
import menu.domain.selector.CategorySelector;
import menu.domain.selector.MenuSelector;

public class MenuService {
    private static final int WEEKDAY_COUNT = 5;
    private static final int MAX_CATEGORY_DUPLICATION = 2;
    private final CategorySelector categorySelector;
    private final MenuSelector menuSelector;

    public MenuService(CategorySelector categorySelector, MenuSelector menuSelector) {
        this.categorySelector = categorySelector;
        this.menuSelector = menuSelector;
    }

    public void banMenu(Coach coach, List<String> banedMenuNames) {
        if (banedMenuNames.isEmpty()) {
            coach.banMenus(Collections.emptyList());
            return;
        }
        List<Menu> bannedMenus = banedMenuNames.stream()
                .map(Menu::from)
                .collect(Collectors.toList());
        coach.banMenus(bannedMenus);
    }

    public List<Category> selectCategory() {
        List<Category> selectCategories = new ArrayList<>();
        while (selectCategories.size() < WEEKDAY_COUNT) {
            Category category = categorySelector.select();
            if (categoryCount(selectCategories, category) >= MAX_CATEGORY_DUPLICATION) {
                continue;
            }
            selectCategories.add(category);
        }
        return selectCategories;
    }

    private int categoryCount(List<Category> selectCategories, Category category) {
        return (int) selectCategories.stream()
                .filter(selectCategory -> selectCategory.equals(category))
                .count();
    }

    public void recommendMenus(Coaches coaches, Category category) {
        coaches.consumeCoaches(coach -> recommendMenuPerCoach(category, coach));
    }

    private void recommendMenuPerCoach(Category category, Coach coach) {
        while (true) {
            Menu menu = menuSelector.select(category);
            if (coach.selectMenu(menu)) {
                return;
            }
        }
    }
}
