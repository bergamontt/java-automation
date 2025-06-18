package analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTokenizer {

    public List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z0-9-`']+");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String token = matcher.group();
            token = token.replaceAll("[-`']", "");
            if (token.isEmpty()) continue;
            tokens.add(token.toLowerCase());
        }
        return tokens;
    }

}
