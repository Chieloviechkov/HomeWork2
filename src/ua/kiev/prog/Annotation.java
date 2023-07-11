package ua.kiev.prog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
@interface Test {
    int a();
    int b();
}
class Summer {
    @Test(a=2,b=5)
    public static void test(int a,int b){
        System.out.println(a + b);
    }
}

public class Annotation {
    public static void main(String[] args) {
        try {
            Class<?> cls = Summer.class;
            Method method = cls.getMethod("test", int.class, int.class);
            if (method.isAnnotationPresent(Test.class)) {

                Test ma = method.getAnnotation(Test.class);
                method.invoke(null, ma.a(), ma.b());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
