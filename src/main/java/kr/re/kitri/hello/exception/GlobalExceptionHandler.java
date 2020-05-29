package kr.re.kitri.hello.exception;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;

@ControllerAdvice
@RestController
//모든 예외를 처리하도록 어노테이션 2종 선언

public class GlobalExceptionHandler {
    //value에 처리하고 싶은 예외 클래스를 선언하고 예외 캐치시 매핑된 메서드 실행
    @ExceptionHandler(value = SQLInvalidAuthorizationSpecException.class)
    public String handleSQLInvalidException(SQLInvalidAuthorizationSpecException exception){
        return "데이터베이스 접속에 문제가 있습니다" + exception.getMessage();
    }

    // @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(value = ArithmeticException.class)
    public String handleArithmeticException(ArithmeticException exception){
        return "연산 예외가 발생했습니다." + exception.getMessage();
    }

    @ExceptionHandler(value = SQLException.class)
    public String handleSQLException(SQLException exception){
        return "SQL 에러가 발생했습니다." + exception.getMessage();
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    public String handleBadSqlGramarException(BadSqlGrammarException exception){
        return "SQL 문법 에러가 발생했습니다.." + exception.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    public String handleAllException(Exception exception){
        return "그 외의 에러가 발생했습니다." + exception.getMessage();
    }
}
