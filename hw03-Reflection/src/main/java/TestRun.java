import annotation.After;
import annotation.Before;
import annotation.Test;
import exceptions.TestStarterException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRun {
    private List<Method> beforeMethods;
    private List<Method> testMethods;
    private List<Method> afterMethods;

    private int currentMethod = 0;
    private int succesCount = 0;
    private int errorCount = 0;

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private Class testingClass;
    private Object testObject;

    public static void main(String[] args) {
        try {
            new TestRun(Cookie.class).testing();
        } catch (TestStarterException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public TestRun(Class testingClass) {
        this.testingClass = testingClass;
        beforeMethods = new ArrayList<>();
        testMethods = new ArrayList<>();
        afterMethods = new ArrayList<>();
    }

    public void testing() throws TestStarterException {
        getClassMetods();
        for (Method method : testMethods) {
            currentMethod = 0;
            succesCount = 0;
            errorCount = 0;

            System.out.println("=====================================");
            getObject();

            List<Method> test = new ArrayList<>();
            test.add(method);

            runTestResult(beforeMethods);
            runTestResult(test);
            runTestResult(afterMethods);

            System.out.println(String.format("Было запущено %d тестов. Успешно пройдено - %d. Завершилось с ошибками - %d", currentMethod, succesCount, errorCount));
        }
    }


    private void getClassMetods() {
        Arrays.stream(testingClass.getMethods()).forEach(method -> {
                    if (method.isAnnotationPresent(Before.class)) {
                        beforeMethods.add(method);
                    }
                    if (method.isAnnotationPresent(Test.class)) {
                        testMethods.add(method);
                    }
                    if (method.isAnnotationPresent(After.class)) {
                        afterMethods.add(method);
                    }
                }
        );
    }

    private void runTestMethod(Method method ) throws TestStarterException {
        try {
            method.invoke(testObject);
        } catch (InvocationTargetException e) {
            throw new TestStarterException(String.format("%sFAIL%s: Метод '%s' упал в ошибку: %s", ANSI_RED, ANSI_RESET, method.getName(), e.getTargetException()));
        }
        catch (Exception e) {
            throw new TestStarterException(String.format("%sFAIL%s: Метод '%s' упал в ошибку", ANSI_RED, ANSI_RESET, method.getName()));
        }
    }

    private void getObject() throws TestStarterException {
        Constructor<Object> contructor = null;
        try {
            contructor = testingClass.getConstructor();
        } catch (Exception e) {
            throw new TestStarterException("Тестовый класс должен иметь конструктор без каких-либо аргументов");
        }

        testObject = null;
        try {
            testObject = testingClass.cast(contructor.newInstance());
        } catch (Exception e) {
            throw new TestStarterException("InstantiationException");
        }

    }

    private void runTestResult(List<Method> methods) throws TestStarterException {
       for (Method method : methods) {
            System.out.println(String.format("#%d. Запускаем тест: %s", ++currentMethod, method.getName()));

            try {
                runTestMethod(method);
                System.out.println(String.format("%sSUCCESS%s", ANSI_GREEN, ANSI_RESET));

                succesCount ++;

            } catch (TestStarterException e) {

                System.out.println(e.getMessage());

                errorCount ++;
            }
        }
    }
}
