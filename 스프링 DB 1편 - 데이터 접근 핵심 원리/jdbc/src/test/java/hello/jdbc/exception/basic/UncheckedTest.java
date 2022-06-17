package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class UncheckedTest {

    @Test
    void unchecked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void unchecked_throw() {
        Service service = new Service();
        assertThatThrownBy(service::callThrow)
                .isInstanceOf(MyUncheckedException.class);
    }

    // 런타임 예외 상속받은 예외는 언체크 예외
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    // 언체크 예외는 예외를 잡거나 던지지 않아도 된다
    // 예외를 잡지 않으면 자동으로 밖으로 던짐
    static class Service {
        Repository repository = new Repository();

        public void callCatch() {
            try{
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("예외처리, message={}", e.getMessage(), e);
            }
        }

        // 예외를 잡지않아도 자연스럽게 상위로 넘어감
        public void callThrow() {
            repository.call();
        }
    }

    static class Repository {
        public void call() {
            throw new MyUncheckedException("ex");
        }
    }
}
