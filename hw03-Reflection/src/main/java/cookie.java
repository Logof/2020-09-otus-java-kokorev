import annotation.After;
import annotation.Before;
import annotation.Test;
import exceptions.cookieExceptions;

public class cookie {
    private boolean makeCooked; // Признак, что печенье готовиться
    private int countCookie;     // Сколько печенек приготовлено
    private int checkCount;

    public cookie(){
        countCookie = 0;
        makeCooked = false;
    }

    @Before
    public void cook() {
        if (!makeCooked) {
            makeCooked = true;
            checkCount = (int) (1 + (Math.random() * 5));
            System.out.println("До готовности печенья его нужно проверить " + checkCount + " раз/раза");
        }

    }

    @Test
    public void checkCooking() throws cookieExceptions {
        if (checkCount == 0) {
            makeCooked = false;
            countCookie += 1;
            System.out.println("Печенье готово, можно кушать");
        } else {
            checkCount -= 1;
            System.out.println(String.format("Печенье осталось посмотреть %d раз(а) до полной готовности", checkCount));
        }
        //
    }
    @Test
    public void grabCooking() throws cookieExceptions {
        if (makeCooked) {
            throw new cookieExceptions("Печенье еще не готово. Нужно подождать");
        }
    }

    @After
    public void eatCookie() throws cookieExceptions {
        if (countCookie <= 0 & !makeCooked) {
            throw new cookieExceptions("Печенье закончилось. Нужно приготовить новые");
        }
        if (makeCooked) {
            throw new cookieExceptions("Печенье еше готовиться. Нужно подождать");
        }
        countCookie -= 1;
        System.out.println("Вы съели одну печеньку");
    }

}