package xyz.hugme.hugmebackend;


import org.junit.jupiter.api.Test;

public class OuterClass {

    @Test
    public void bool() throws Exception{
        System.out.println(Boolean.parseBoolean("trUe"));
        System.out.println(Boolean.parseBoolean("true"));

     }

    public String getHello() {
        return "helloOuterClass";
    }
    static class StaticNestedClass{
        // static 중첩 클래스는 외부 클래스의 참조를 포함하지 않기 때문에 아래의 OuterClass.this 는 컴파일 에러
//        public String reachOuterClass(){
//            return OuterClass.this.getHello();
//        }
        public String getHello() {
            return "helloStaticNestedClass";
        }
    }
    class InnerClass {
        public String reachOuterClass(){
            // 그냥 this는 InnerClass 의 this 이고,
            // OuterClass.this 는 OuterClass 의 this 이다.
            // 이너클래스는 외부클래스의 참조를 무조건 동반하기 때문에 이런 문법이 허용된다.
            return OuterClass.this.getHello();
        }
        public String getHello() {
            return "helloInnerClass";
        }
    }
}
