package songbowen.exercises;

import java.util.*;
import java.util.stream.Collectors;

public class Recursive {

    static boolean isShare(int[] in, int[] group, int num, int groupSize, int curSize, int groupId) {
        if (curSize == 0) {
            if (groupId == num) {
                return true;
            }
            groupId++;
            curSize = groupSize;
        }
        for (int i = 0; i < in.length; i++) {
            if (group[i] != 0) {
                continue;
            }
            int curValue = in[i];
            if (curSize < curValue) {
                continue;
            }
            group[i] = groupId;
            if (isShare(in, group, num, groupSize, curSize - curValue, groupId)) {
                return true;
            } else {
                group[i] = 0;
            }
        }
        return false;
    }

    /**
     * 一个整数数组，长度为n，将其分为m 份，使各份的和相等，求m 的最大值
     * 比如数组：{3,2,4,3,6}
     * m=1，可以分成{3，2，4，3，6} ;
     * m=2，可以分成{3,6}{2,4,3} ；
     * m=3，可以分成{3,3}{2,4}{6}
     * 所以m 的最大值为3
     */
    static void maxShare(int[] in) {
        int sum = Arrays.stream(in).sum();
        int maxNum = sum / Arrays.stream(in).max().getAsInt();
        for (int num = maxNum; num >= 2; num--) {
            if (sum % num != 0) {
                continue;
            }
            int[] group = new int[in.length];
            if (isShare(in, group, num, sum / num, sum / num, 1)) {
                Map<Integer, List<Integer>> groupMap = new HashMap<>();
                for (int i = 0; i < group.length; i++) {
                    List<Integer> groupList = groupMap.get(group[i]);
                    if (groupList == null) {
                        groupMap.put(group[i], new ArrayList<>(Collections.singletonList(in[i])));
                    } else {
                        groupList.add(in[i]);
                    }
                }
                Set<Map.Entry<Integer, List<Integer>>> entries = groupMap.entrySet();
                for (Map.Entry<Integer, List<Integer>> entry : entries) {
                    System.out.println("{" + entry.getValue().stream().map(Object::toString)
                            .collect(Collectors.joining(",")) + "}");
                }
                return;
            }
        }
        System.out.println("{" + Arrays.stream(in).mapToObj(Objects::toString).collect(Collectors.joining(",")) + "}");
    }

    /**
     * 求集合所有的子集
     * 例如 [1,2] 的子集为 [] [1] [2] [1,2]
     */
    static List<Set<Integer>> subSet(Set<Integer> set) {
        List<Set<Integer>> result = new ArrayList<>();
        result.add(new TreeSet<>());
        for (Integer value : set) {
            List<Set<Integer>> tmp = new ArrayList<>();
            for (Set<Integer> subSet : result) {
                TreeSet<Integer> newSet = new TreeSet<>(subSet);
                newSet.add(value);
                tmp.add(newSet);
                newSet = new TreeSet<>(subSet);
                tmp.add(newSet);
            }
            result = tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        List<Set<Integer>> sets = subSet(set);
        System.out.println(sets);
    }
}
