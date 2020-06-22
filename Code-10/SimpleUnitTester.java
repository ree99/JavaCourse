import java.lang.reflect.Method;
public class SimpleUnitTester {

    public int execute(Class clazz) throws Exception {
        int failedCount = 0;

        // your code

        Object object = clazz.newInstance();
        Method[] methods =clazz.getDeclaredMethods();

        for(int i=0;i<methods.length;i++){
          String name = methods[i].getName();
          if(name.startsWith("test")){
            if(methods[i].getGenericReturnType() == boolean.class){
              Object val = methods[i].invoke(object);
              if(!((Boolean)val).booleanValue()) failedCount++;
            }
          }
        }

        return failedCount;
    }

}
