package ua.kiev.prog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= {ElementType.METHOD, ElementType.PACKAGE})
@Retention(value= RetentionPolicy.RUNTIME)

@interface Saver {

}
