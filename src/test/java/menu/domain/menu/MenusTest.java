package menu.domain.menu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import menu.exception.MenuException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MenusTest {

    @Nested
    @DisplayName("생성 테스트")
    static class 생성_테스트{
        @ParameterizedTest
        @MethodSource("normalMenus")
        @DisplayName("정상적인 생성 테스트")
        void 정상_생성_테스트(List<Menu> normalMenus) {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> new Menus(normalMenus));
        }

        static Stream<List<Menu>> normalMenus(){
            return Stream.of(
                    List.of(),
                    List.of(Menu.BAGUETTE),
                    List.of(Menu.BAGUETTE, Menu.BULGOGI),
                    List.of(Menu.BAGUETTE, Menu.BULGOGI, Menu.PHO)
            );
        }

        @Test
        @DisplayName("중복된 메뉴가 존재하면 예외가 발생한다.")
        void 중복_메뉴_테스트() {
            List<Menu> menus = List.of(Menu.BAGUETTE, Menu.BULGOGI, Menu.BAGUETTE);
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Menus(menus))
                    .withMessage(MenuException.DUPLICATE_MENU.getMessage());
        }
    }
}