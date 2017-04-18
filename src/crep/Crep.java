package crep;

import java.io.*;
import java.util.regex.Pattern;

public class Crep {

    private final String word;
    private final String fileName;

    public Crep(String word, String fileName) {
        this.word = word;
        this.fileName = fileName;
    }

    /*
    -r (regex) вместо слова задаёт регулярное выражение для поиска(на консоль выводятся только строки, содержащие данное выражение)
    -v инвертирует условие фильтрации (выводится только то, что ему НЕ соответствует)
    -i игнорировать регистр слов
    */
    public String creper(boolean r, boolean v, boolean i) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            StringBuilder str = new StringBuilder();

            String regex;
            int flags = 0;

            if (v && i) {
                regex = r ? "^((?!" + word + ").)*$" : "^((?!\\Q" + word + "\\E).)*$";
                flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
            }

            else if (v) regex = r ? "^((?!" + word + ").)*$" : "^((?!\\Q" + word + "\\E).)*$";

            else if (i) {
                regex = r ? ".*" + word + ".*" : ".*\\Q" + word + "\\E.*";
                flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
            }

            else regex = r ? ".*" + word + ".*" : ".*\\Q" + word + "\\E.*";

            Pattern pattern = Pattern.compile(regex, flags);

            while (line != null) {
                if (pattern.matcher(line).matches()) str.append("\n").append(line);
                line = br.readLine();
            }
            return str.toString().replaceFirst("\n", "");
        }
    }
}