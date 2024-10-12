package deque;

import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest<T> {
    public Comparator comparator() {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    return Integer.compare((Integer) o1, (Integer) o2);
                }
                return 0;
            }
        };
    }



    @Test
    public void testMaxWithComparator() {
        MaxArrayDeque<Integer> lld = new MaxArrayDeque<>(comparator());
        int testNum = 64;
        for (int i = 0; i < testNum; i++) {
            lld.addLast(i);
        }

        System.out.println(lld.max(comparator()));
    }

    @Test
    public void testMaxWithoutComparator() {
        MaxArrayDeque<Integer> lld = new MaxArrayDeque<>(comparator());
        int testNum = 64;
        for (int i = 0; i < testNum; i++) {
            lld.addLast(i);
        }
        lld.addLast(0);

        System.out.println(lld.max());
    }
}
