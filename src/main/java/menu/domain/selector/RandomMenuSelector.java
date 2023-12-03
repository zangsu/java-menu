package menu.domain.selector;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;

public class RandomMenuSelector implements MenuSelector {
    @Override
    public Menu select(Category category) {
        List<String> menus = category.getMenus().stream()
                .map(Menu::getName)
                .collect(Collectors.toList());
        String selectedMenuName = Randoms.shuffle(menus).get(0);
        return Menu.from(selectedMenuName);
    }
}
