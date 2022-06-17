package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        assertThatThrownBy(service::callThrow)
                        .isInstanceOf(MyCheckedException.class);
    }

    // exception 상속받은 예외는 체크 예외
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    // 체크 예외는 예외를 잡아서 처리하거나 던지거나 둘 중 하나를 필수로 선택해야함
    static class Service {
        Repository repository = new Repository();

        // 체크 예외 잡아서 처리
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                //예외 처리 로직
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        // 체크 예외 밖으로 던지는 코드
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        // 체크 예외는 밖으로 던지는걸 선언적으로 남겨야함
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
