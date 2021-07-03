package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    /**
     * Qualifier 안에 들어가는 문자열 등에 오타가 있으면 잡기 힘든 오류가 발생한다.
     * 따라서 이를 따로 어노테이션을 만들어서 사용하게 되면 관리하기 쉬워진다.
     */
}
