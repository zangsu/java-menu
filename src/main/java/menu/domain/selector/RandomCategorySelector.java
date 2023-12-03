package menu.domain.selector;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.menu.Category;

public class RandomCategorySelector implements CategorySelector {
    @Override
    public Category select() {
        int categoryNumber = Randoms.pickNumberInRange(CATEGORY_MIN_NUMBER, CATEGORY_MAX_NUMBER);
        return Category.from(categoryNumber);
    }
}
