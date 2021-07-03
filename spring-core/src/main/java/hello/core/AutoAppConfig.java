package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // Component Scan 대상 패키지를 지정하지 않기 위해서 Config class를 프로젝트 최상단에 위치시킨다.
        // 탐색할 패키지의 시작 패키지를 지정한다.
        // Default는 현재 클래스를 포함하는 패키지이다. 현재는 "hello.core"이다.
        basePackages = "hello.core",
        // 기존의 AppConfig 파일을 유지하기 위해서 Component Scan 대상에서 제외시킴.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
