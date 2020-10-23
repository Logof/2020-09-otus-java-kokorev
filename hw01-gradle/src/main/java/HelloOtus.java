import com.google.common.collect.Lists;

import java.util.ArrayList;

public class HelloOtus {
    public static void main(String[] args){
        ArrayList<Integer> list;
        list = Lists.newArrayList(1, 2, 3, 4, 5);
        System.out.println(list);
        System.out.println(Lists.reverse(list));
    }
}
