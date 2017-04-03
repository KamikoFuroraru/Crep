package crep;

import java.io.*;
import java.util.regex.Matcher;
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
            Matcher matcher = null;
            while (line != null) {
                if (v && i)
                    matcher = Pattern.compile("^((?!" + word + ").)*$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(line);
                if (v && !i) matcher = Pattern.compile("^((?!" + word + ").)*$").matcher(line);
                if (!v && i)
                    matcher = Pattern.compile(".*" + word + ".*", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(line);
                if (r && !v && !i) matcher = Pattern.compile(".*" + word + ".*").matcher(line);
                if (!r && !v && !i) str.append(line).append("\n");
                if (matcher != null && matcher.matches()) str.append(line).append("\n");
                line = br.readLine();
            }
            return str.deleteCharAt(str.lastIndexOf("\n")).toString();
        }
    }
}