package menu.domain.selector;

import menu.domain.menu.Category;
import menu.domain.menu.Menu;

public interface MenuSelector {

    Menu select(Category category);
}
