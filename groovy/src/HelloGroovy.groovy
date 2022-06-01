class HelloGroovy {

    /**
     * @ref: https://kotlinworld.com/320
     */
    static void main(String[] args) {
        // groovy는 동적 타이핑 언어이다.
        // 변수는 def 로 선언한다.
        def greetingMessage = "Hello Groovy :)"
        println(greetingMessage)

        // Java의 클래스와 대응되는 모습을 확인할 수 있다.
        println(greetingMessage.class) // -> class java.lang.String

        // format string을 쓰고 싶으면 ${} 을 쓴다.
        def version = "4.0.2"
        println("groovy version is ${version}")

        // RegEx 매칭은 ==~ 으로 할 수 있다.
        def myEmail = "eddy@groovy.com"
        def emailPattern = /([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})/
        println(myEmail ==~ emailPattern) // -> true

        // Java의 lambda랑 비슷하다고 보면 된다.
        def closure = { num ->
            println(num)
        }
        closure(5)

        // ArrayList
        def arrayList = [1, 2, 3]

        // Set
        def set = arrayList.toSet()

        // Map
        // key는 기본적으로 String이라서 ""를 붙여주지 않아도 된다.
        def map = [name: "eddy", age: 10]

        // 메서드 호출하기
        def result = add 3, 5
        println(result) // -> 8

        // 객체 생성하기
        def student = new Student(name: "eddy", grade: 4, gpa: 4.5)
        println(student.name) // -> eddy
        println(student.grade) // -> 4
    }

    // 메서드 만들기
    static int add(int x, int y) {
        x + y // 마지막 줄이 return 이다.
    }
}
