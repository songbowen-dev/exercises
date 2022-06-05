package songbowen.exercises;

public class CharSequence {

    /**
     * 输入字符串s1和s2，如何判断字符串s2中是否包含字符串s1的某个变位词？
     * 如果字符串s2中包含字符串s1的某个变位词，则字符串s1至少有一个变位词是字符串s2的子字符串。
     * 假设两个字符串中只包含英文小写字母。例如，字符串s1为"ac"，字符串s2为"dgcaf"，
     * 由于字符串s2中包含字符串s1的变位词"ca"，因此输出为true。如果字符串s1为"ab"，
     * 字符串s2为"dgcaf"，则输出为false。
     */
    static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        int[] charMap = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            charMap[c - 'a']++;
        }
        int index1 = 0;
        int index2 = s1.length() - 1;
        for (int i = index1; i <= index2; i++) {
            char c = s2.charAt(i);
            charMap[c - 'a']--;
        }
        if (checkAllZero(charMap)) {
            return true;
        }
        if (index2 == s2.length() - 1) {
            return false;
        }
        do {
            charMap[s2.charAt(index1++) - 'a']++;
            charMap[s2.charAt(++index2) - 'a']--;
            if (checkAllZero(charMap)) {
                return true;
            }
        } while (index2 != s2.length() - 1);
        return false;
    }

    static boolean checkAllZero(int[] charMap) {
        for (int j : charMap) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "sabdaa";
        String s2 = "asdadacabggasdaga";

        System.out.println(checkInclusion(s1, s2));

    }

}
