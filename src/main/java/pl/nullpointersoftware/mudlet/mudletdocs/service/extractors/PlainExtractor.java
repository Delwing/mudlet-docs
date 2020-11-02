package pl.nullpointersoftware.mudlet.mudletdocs.service.extractors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlainExtractor {

    public static final PlainExtractor HEADER_EXTRACTOR = new PlainExtractor("^\\s*==(.*)==");
    public static final PlainExtractor FUNCTION_SIGNATURE = new PlainExtractor(";(.*)");

    private final Pattern pattern;

    public PlainExtractor(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public String extract(String line) {
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public boolean canExtract(String line) {
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

}
