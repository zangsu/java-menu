package menu;

import java.util.ArrayList;
import java.util.List;
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

    public List<Category> selectCategory() {
        List<Category> selectCategories = new ArrayList<>();
        while(selectCategories.size() < 5){
            Category category = categorySelector.select();
            if(categoryCount(selectCategories, category) >= 2) {
                continue;
            }
            selectCategories.add(category);
        }
        return selectCategories;
    }

    private static long categoryCount(List<Category> selectCategories, Category category) {
        return selectCategories.stream()
                .filter(selectCategory -> selectCategory.equals(category))
                .count();
    }

    public Menu selectMenu(Category category) {
        return menuSelector.select(category);
    }
}
