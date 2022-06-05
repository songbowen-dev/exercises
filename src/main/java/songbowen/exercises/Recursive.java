package songbowen.exercises;

import java.util.*;
import java.util.stream.Collectors;

public class Recursive {

    /**
     * 一个整数数组，长度为n，将其分为m 份，使各份的和相等，求m 的最大值
     * 比如数组：{3,2,4,3,6}
     * m=1，可以分成{3，2，4，3，6} ;
     * m=2，可以分成{3,6}{2,4,3} ；
     * m=3，可以分成{3,3}{2,4}{6}
     * 所以m 的最大值为3
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] in = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        maxShare(in);
    }


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
}
