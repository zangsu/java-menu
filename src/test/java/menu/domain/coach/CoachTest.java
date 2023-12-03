package menu.domain.coach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import menu.domain.menu.Menu;
import menu.exception.MenuException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CoachTest {

    @Nested
    @DisplayName("코치 생성 테스트")
    class 코치_생성_테스트{
        
        @ParameterizedTest
        @ValueSource(strings = {"경덕","장혁수","황보경덕"})
        @DisplayName("")
        void 정상_생성_테스트(String coachName) {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> new Coach(coachName));
        }

        @ParameterizedTest
        @ValueSource(strings = {"덕","최김황경덕","김이박최경덕"})
        @DisplayName("이름이 2~4글자가 아닐 경우 예외 발생")
        void 긴_이름_예외_테스트(String coachName) {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Coach(coachName))
                    .withMessage(MenuException.INVALID_COACH_NAME_LENGTH.getMessage());
        }
    }

    @Nested
    @DisplayName("못먹는 메뉴 테스트")
    static class 못_먹는_메뉴_테스트{

        private Coach coach;

        @BeforeEach
        void setUp(){
            coach = new Coach("경덕");
        }

        @ParameterizedTest
        @MethodSource("normalBannedMenus")
        @DisplayName("못먹는 음식을 정상적으로 추가한다.")
        void 정상_입력_테스트(List<Menu> menus) {
            coach.addBanedMenu(menus);
        }
        static Stream<List<Menu>> normalBannedMenus(){
            return Stream.of(
                    List.of(Menu.DOJANG_JJIGAE, Menu.KIMCHI_JJIGAE),
                    List.of(Menu.BIBIMBAP),
                    List.of()
            );
        }
    }
}