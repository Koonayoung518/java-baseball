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
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void 스트라이크_수_1개일_때_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(1,4,7));
        assertEquals(1, Application.numberOfStrikes(computer, user));
    }

    @Test
    void 스트라이크_수_2개일_때_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(1,4,3));
        assertEquals(2, Application.numberOfStrikes(computer, user));
    }

    @Test
    void 스트라이크_수_3개일_때_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(1,2,3));
        assertEquals(3, Application.numberOfStrikes(computer, user));
    }

    @Test
    void 볼_수_0개일_때_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(7,8,9));

        assertEquals(0, Application.numberOfBalls(computer, user));
    }

    @Test
    void 스트라이크로_인해_볼_수_0개일_때_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(1,8,9));

        assertEquals(0, Application.numberOfBalls(computer, user));
    }

    @Test
    void 볼_수_1개일_때_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(7,1,9));

        assertEquals(1, Application.numberOfBalls(computer, user));
    }

    @Test
    void 볼_수_2개일_때_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(2,1,9));

        assertEquals(2, Application.numberOfBalls(computer, user));
    }

    @Test
    void 볼_수_3개일_때_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(3,1,2));

        assertEquals(3, Application.numberOfBalls(computer, user));
    }

    @Test
    void 낫싱일_때_true_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(7,8,9));

        assertTrue(Application.checkNothing(computer, user));
    }

    @Test
    void 낫싱이_아닐_때_false_반환_테스트(){
        Player computer = new Player(List.of(1,2,3));
        Player user = new Player(List.of(7,1,9));

        assertFalse(Application.checkNothing(computer, user));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
