package menu.domain.coach;

import java.util.List;
import java.util.stream.Stream;
import menu.domain.menu.Menu;
import menu.exception.MenuException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CoachTest {

    @Nested
    @DisplayName("코치 생성 테스트")
    class 코치_생성_테스트 {
        @ParameterizedTest
        @ValueSource(strings = {"경덕", "장혁수", "황보경덕"})
        @DisplayName("")
        void 정상_생성_테스트(String coachName) {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> new Coach(coachName));
        }


        @ParameterizedTest
        @ValueSource(strings = {"덕", "최김황경덕", "김이박최경덕"})
        @DisplayName("이름이 2~4글자가 아닐 경우 예외 발생")
        void 긴_이름_예외_테스트(String coachName) {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Coach(coachName))
                    .withMessage(MenuException.INVALID_COACH_NAME_LENGTH.getMessage());
        }
    }

    @Nested
    @DisplayName("못먹는 메뉴 테스트")
    static class 못_먹는_메뉴_테스트 {

        private Coach coach;

        static Stream<List<Menu>> normalBannedMenus() {
            return Stream.of(
                    List.of(Menu.DOJANG_JJIGAE, Menu.KIMCHI_JJIGAE),
                    List.of(Menu.BIBIMBAP),
                    List.of()
            );
        }

        static Stream<List<Menu>> overBannedMenus() {
            return Stream.of(
                    List.of(Menu.DOJANG_JJIGAE, Menu.KIMCHI_JJIGAE, Menu.BIBIMBAP),
                    List.of(Menu.DOJANG_JJIGAE, Menu.KIMCHI_JJIGAE, Menu.BIBIMBAP, Menu.DONGPO_PORK)
            );
        }

        @BeforeEach
        void setUp() {
            coach = new Coach("경덕");
        }

        @ParameterizedTest
        @MethodSource("normalBannedMenus")
        @DisplayName("못먹는 음식을 정상적으로 추가한다.")
        void 정상_입력_테스트(List<Menu> menus) {
            coach.addBanedMenu(menus);
        }

        @Test
        @DisplayName("못먹는 음식이 중복으로 저장되면 예외가 발생한다.")
        void 중복_저장_예외() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> coach.addBanedMenu(List.of(Menu.DOJANG_JJIGAE, Menu.DOJANG_JJIGAE)))
                    .withMessage(MenuException.DUPLICATE_MENU.getMessage());
        }

        @ParameterizedTest
        @MethodSource("overBannedMenus")
        @DisplayName("못먹는 음식이 2개보다 많으면 예외가 발생한다.")
        void 못먹는_음식_초과_입력_테스트(List<Menu> bannedMenus) {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> coach.addBanedMenu(bannedMenus))
                    .withMessage(MenuException.MAX_BANNED_MENU.getMessage());
        }
    }

    @Nested
    @DisplayName("메뉴 선택 테스트")
    static class 메뉴_선택_테스트 {
        private Coach coach;

        @BeforeEach
        void setUp() {
            coach = new Coach("경덕");
            coach.addBanedMenu(List.of(Menu.DOJANG_JJIGAE, Menu.KIMCHI_JJIGAE));
        }

        @ParameterizedTest
        @MethodSource("normalSelectMenu")
        @DisplayName("정상적으로 메뉴를 선택한다.")
        void 정상_선택_테스트(List<Menu> selectMenus) {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> {
                        selectMenus.forEach(menu -> {
                            boolean b = coach.selectMenu(menu);
                            Assertions.assertThat(b).isTrue();
                        });
                    });
        }

        static Stream<List<Menu>> normalSelectMenu() {
            return Stream.of(
                    List.of(Menu.BIBIMBAP, Menu.DONGPO_PORK, Menu.BANH_MI, Menu.FRENCH_TOAST, Menu.BUN_CHA),
                    List.of(Menu.BIBIMBAP, Menu.DONGPO_PORK, Menu.BANH_MI, Menu.FRENCH_TOAST),
                    List.of(Menu.BIBIMBAP, Menu.DONGPO_PORK, Menu.BANH_MI),
                    List.of(Menu.BIBIMBAP, Menu.DONGPO_PORK),
                    List.of(Menu.BIBIMBAP)
            );
        }
        
        @ParameterizedTest
        @MethodSource("bannedSelectMenu")
        @DisplayName("못먹는 메뉴를 선택하면 false를 반환한다.")
        void 못먹는_메뉴_선택(){
            Assertions.assertThat(coach.selectMenu(Menu.DOJANG_JJIGAE)).isFalse();
        }
        
        static Stream<Menu> bannedSelectMenu(){
            return Stream.of(
                    Menu.DOJANG_JJIGAE,
                    Menu.KIMCHI_JJIGAE
            );
        }

        @Test
        @DisplayName("이미 선택한 메뉴를 선택하면 false를 반환한다.")
        void 이미_선택한_메뉴_선택(){
            coach.selectMenu(Menu.BIBIMBAP);
            Assertions.assertThat(coach.selectMenu(Menu.BIBIMBAP)).isFalse();
        }

        @Test
        @DisplayName("5개 이상의 메뉴를 선택하면 예외가 발생한다.")
        void 메뉴_초과_선택(){
            coach.selectMenu(Menu.BIBIMBAP);
            coach.selectMenu(Menu.DONGPO_PORK);
            coach.selectMenu(Menu.BANH_MI);
            coach.selectMenu(Menu.FRENCH_TOAST);
            coach.selectMenu(Menu.BUN_CHA);
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> coach.selectMenu(Menu.DOJANG_JJIGAE))
                    .withMessage(MenuException.CANT_SELECT_MORE_MENU.getMessage());
        }
    }


}