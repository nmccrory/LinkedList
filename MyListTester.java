
package linkedlist;

import java.lang.reflect.*;
import java.util.*;

public class MyListTester {

    public static boolean eq2(ArrayList<Integer> al, List<Integer> li) {
        if (al.size() != li.size())
            return false;
        for (int i = 0; i<al.size(); i++) {
            int i1 = al.get(i);
            int i2 = li.get(i);
            if (i1 != i2)
                return false;
        }
        return true;
    }
    public static boolean eq(ArrayList<Integer> al, List<Integer> li) {
      boolean result = eq2(al,li);
      if(!result) {
        System.out.println("Expected: "+li);
        System.out.println("     Got: "+al);
      }
      return result;
    }

    public static void main(String[] args) throws Exception {
        try {
            assert(false);
            throw new Error("Assertions are not enabled");
        } catch (AssertionError ae) {}
        assert args.length >= 1 : "You must specify which implementation you are testing";
        Class<?> c = Class.forName(args[0]);
        int[] arr1 = {1,2,3,6,7,8,9};
        int[] arr2 = {4,5};
        Constructor con = c.getDeclaredConstructor(new Class[]{int[].class});
        assert Modifier.isPublic(con.getModifiers()) : "Constructor should be public";

        /* basic testing */

        MyList list1 = (MyList)con.newInstance(arr1);
        MyList list2 = (MyList)con.newInstance(arr2);

        MyList list3 = list1.split(4);
        assert(eq(list1.getData(), Arrays.asList(1,2,3,6,7)));
        assert(eq(list3.getData(), Arrays.asList(8,9)));

        list3 = list1.split(4);
        assert(eq(list1.getData(), Arrays.asList(1,2,3,6,7)));
        assert(eq(list3.getData(), Arrays.asList()));

        //assert(eq(list1.getData(), Arrays.asList()));
        //assert(eq(list3.getData(), Arrays.asList(1,2,3,6,7)));

        list1 = (MyList)con.newInstance(arr1);
        list1.add(1, list2);
        assert(eq(list1.getData(), Arrays.asList(1,4,5,2,3,6,7,8,9)));

        list2 = (MyList)con.newInstance(arr2);
        list1.add(0, list2);
        assert(eq(list1.getData(), Arrays.asList(4,5,1,4,5,2,3,6,7,8,9)));

        list2 = (MyList)con.newInstance(arr2);
        list1.add(11, list2);
        assert(eq(list1.getData(), Arrays.asList(4,5,1,4,5,2,3,6,7,8,9,4,5)));

        // Add random tests
        final int MAX_VAL = 100;
        final Random rand = new Random();
        for(int k=0;k<10;k++) {
          ArrayList<Integer> tst1 = new ArrayList<>();
          ArrayList<Integer> tst2 = new ArrayList<>();
          int[] start = new int[1];
          int size = rand.nextInt(10)+20;
          start[0] = rand.nextInt(MAX_VAL);
          list1 = (MyList)con.newInstance(start);
          tst1.add(start[0]);
          start[0] = rand.nextInt(MAX_VAL);
          list2 = (MyList)con.newInstance(start);
          tst2.add(start[0]);
          for(int i=0;i<size;i++) {
            start[0] = rand.nextInt(MAX_VAL);
            list3 = (MyList)con.newInstance(start);
            list1.add(i+1,list3);
            tst1.add(start[0]);
            start[0] = rand.nextInt(MAX_VAL);
            list3 = (MyList)con.newInstance(start);
            list2.add(i+1,list3);
            tst2.add(start[0]);
          }
          assert eq(list1.getData(),tst1);
          assert eq(list2.getData(),tst2);
          int pos1 = rand.nextInt(10)+5;
          int pos2 = rand.nextInt(5)+1;
          ArrayList<Integer> tst3 = new ArrayList<>();
          for(int i=0;i<tst2.size();i++) {
            if(pos2 == i) {
              for(int j=pos1+1;j<tst1.size();j++) {
                tst3.add(tst1.get(j));
              }
            }
            tst3.add(tst2.get(i));
          }
          list3 = list1.split(pos1);
          list2.add(pos2,list3);
          assert eq(list2.getData(),tst3);
          tst3.clear();
          for(int i=0;i<=pos1;i++)
            tst3.add(tst1.get(i));
          assert eq(list1.getData(),tst3);
        }

        System.out.println("All tests passed.");
    }
}
