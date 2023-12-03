package menu.domain.coach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import menu.exception.MenuException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CoachesTest {

    @Nested
    @DisplayName("생성 테스트")
    static class 생성_테스트{

        @ParameterizedTest
        @MethodSource("normalCoachNames")
        @DisplayName("정상적인 입력 테스트")
        void 정상_생성_테스트(List<String> coachNames){
            Assertions.assertThatNoException()
                    .isThrownBy(() -> new Coaches(coachNames));
        }


        static Stream<List<String>> normalCoachNames(){
            return Stream.of(
                    List.of("김철수", "이영희"),
                    List.of("김철수", "이영희", "박영희"),
                    List.of("김철수", "이영희", "박영희", "최철수"),
                    List.of("김철수", "이영희", "박영희", "최철수", "홍길동")
            );
        }

        @Test
        @DisplayName("중복된 입력이 존재하면 예외가 발생한다.")
        void 중복_이름_테스트() {
            List<String> coachNames = List.of("김철수", "이영희", "홍길동", "김철수");
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Coaches(coachNames))
                    .withMessage(MenuException.DUPLICATE_COACH_NAME.getMessage());
        }

        @Test
        @DisplayName("최소 인원 미만이면 예외가 발생한다.")
        void 최소_인원_미만_테스트() {
            List<String> coachNames = List.of("김철수");
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Coaches(coachNames))
                    .withMessage(MenuException.MIN_COACHES_NUMBER.getMessage());
        }

        @Test
        @DisplayName("최대 인원 초과면 예외가 발생한다.")
        void 최대_인원_초과_테스트() {
            List<String> coachNames = List.of("김철수", "이영희", "박영희", "최철수", "홍길동", "김철수");
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Coaches(coachNames))
                    .withMessage(MenuException.MAX_COACHES_NUMBER.getMessage());
        }

        @Test
        @DisplayName("2글자 미만의 이름이 포함되어 있으면 예외가 발생한다.")
        void 이름_2글자_미만_테스트() {
            List<String> coachNames = List.of("김철수", "김");
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Coaches(coachNames))
                    .withMessage(MenuException.INVALID_COACH_NAME_LENGTH.getMessage());
        }

        @Test
        @DisplayName("5글자 초과의 이름이 포함되어 있으면 예외가 발생한다.")
        void 이름_5글자_초과_테스트() {
            List<String> coachNames = List.of("김철수", "김철수김철수");
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Coaches(coachNames))
                    .withMessage(MenuException.INVALID_COACH_NAME_LENGTH.getMessage());
        }
    }
}