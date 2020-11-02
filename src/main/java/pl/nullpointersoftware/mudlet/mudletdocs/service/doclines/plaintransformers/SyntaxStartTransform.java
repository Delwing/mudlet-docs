package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;

@Component
public class SyntaxStartTransform implements PlainTransformer {

    @Override
    public String transform(String line) {
        line = line.replaceAll("<syntaxhighlight lang=\"(.*)\">\\S", "```$1\n--- ");
        line = line.replaceAll("<syntaxhighlight lang=\"(.*)\">", "```$1");
        line = line.replaceAll("<pre>", "```");
        return line;
    }
}
