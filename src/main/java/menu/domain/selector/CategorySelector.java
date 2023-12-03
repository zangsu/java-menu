package menu.domain.selector;

import menu.domain.menu.Category;

public interface CategorySelector {
    int CATEGORY_MIN_NUMBER = 1;
    int CATEGORY_MAX_NUMBER = 5;
    Category select();
}
