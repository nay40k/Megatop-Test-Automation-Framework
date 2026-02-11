package by.megatop.utils;

public class UnicodeUtils {
    public static String decodeUnicodeEscapes(String input) {
        if (input == null) return null;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '\\' && i + 1 < input.length() && input.charAt(i + 1) == 'u') {
                if (i + 5 <= input.length()) {
                    String hex = input.substring(i + 2, i + 6);
                    try {
                        int codePoint = Integer.parseInt(hex, 16);
                        sb.appendCodePoint(codePoint);
                        i += 6;
                        continue;
                    } catch (NumberFormatException ignored) {}
                }
            }
            sb.append(c);
            i++;
        }
        return sb.toString();
    }
}
