package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest extends NsTest {
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 컴퓨터_숫자는_세_개_테스트(){
        List<Integer> computer = Application.selectComputerNumber();

        assertEquals(3, computer.size());
    }

    @Test
    void 컴퓨터_숫자는_서로_다른_수_테스트(){
        List<Integer> computer = Application.selectComputerNumber();
        Set<Integer> nonDuplicateNumber = new HashSet<>(computer);

        assertEquals(3, nonDuplicateNumber.size());
    }

    @Test
    void 컴퓨터_숫자는_1부터_9사이_테스트(){
        List<Integer> computer = Application.selectComputerNumber();

        for(Integer number : computer){
            assertThat(number).isBetween(1,9);
        }
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
