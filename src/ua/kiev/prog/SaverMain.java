package ua.kiev.prog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;



class TextContainer {

    private String text = "";

    public TextContainer(String text) {
        this.text = text;
    }

    @Saver
    public void save(String path, String text){
        File file = new File(path);

        try(FileOutputStream os = new FileOutputStream(file)){
            os.write(text.getBytes(StandardCharsets.UTF_8));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
@SaveTo(path = "D:\\file.txt")

class SaverMain {

    public static void main(String[] args) {
        saveField(new TextContainer("Hello World"));

    }




    public static void saveField(Object obj){
        Class<?> cls = obj.getClass();
        if(cls.isAnnotationPresent(SaveTo.class)){
            String path = cls.getAnnotation(SaveTo.class).path();
            Method[] methods = cls.getDeclaredMethods();
            for(Method method : methods){
                if(method.isAnnotationPresent(Saver.class)){
                    try{
                        method.invoke(obj, path, cls.getDeclaredField("text").get(obj));
                        System.out.println("Виконано");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        } else System.out.println("Помилка");
    }
}
