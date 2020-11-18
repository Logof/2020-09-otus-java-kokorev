import annotation.After;
import annotation.Before;
import annotation.Test;
import exceptions.testStarterException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testRun {
    private List<Method> beforeMethods;
    private List<Method> testMethods;
    private List<Method> afterMethods;
    private int currentMethod = 0;

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private Class testingClass;
    private Object testObject;

    public static void main(String[] args) {
        try {
            new testRun(cookie.class).testing();
        } catch (testStarterException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public testRun(Class testingClass) {
        this.testingClass = testingClass;
        beforeMethods = new ArrayList<>();
        testMethods = new ArrayList<>();
        afterMethods = new ArrayList<>();
    }

    public void testing() throws testStarterException {
        findClassMetods();
        getObject();
        runTestResult(beforeMethods);
        runTestResult(testMethods);
        runTestResult(afterMethods);
    }


    private void findClassMetods() {
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

    private void runTestMethod(Method method ) throws testStarterException {
        try {
            method.invoke(testObject);
        } catch (InvocationTargetException e) {
            throw new testStarterException(String.format("%sFAIL%s: Метод '%s' упал в ошибку: %s", ANSI_RED, ANSI_RESET, method.getName(), e.getTargetException()));
        }
        catch (Exception e) {
            throw new testStarterException(String.format("%sFAIL%s: Метод '%s' упал в ошибку", ANSI_RED, ANSI_RESET, method.getName()));
        }
    }

    private void getObject() throws testStarterException {
        Constructor<Object> contructor = null;
        try {
            contructor = testingClass.getConstructor();
        } catch (Exception e) {
            throw new testStarterException("Тестовый класс должен иметь конструктор без каких-либо аргументов");
        }

        testObject = null;
        try {
            testObject = testingClass.cast(contructor.newInstance());
        } catch (Exception e) {
            throw new testStarterException("InstantiationException");
        }

    }

    private void runTestResult(List<Method> methods) throws testStarterException {
        int failedTests = 0;

        for (Method method : methods) {
            System.out.println(String.format("#%d. Запускаем тест: %s", ++currentMethod, method.getName()));

            try {
                runTestMethod(method);
                System.out.println(String.format("%sSUCCESS%s", ANSI_GREEN, ANSI_RESET));

            } catch (testStarterException e) {
                failedTests++;
                System.out.println(e.getMessage());
            }
        }
        System.out.println(String.format("Было запущено %d тестов. Успешно пройдено - %d. Завершилось с ошибками - %d", afterMethods.size(), (afterMethods.size() - failedTests), failedTests));

    }
}
