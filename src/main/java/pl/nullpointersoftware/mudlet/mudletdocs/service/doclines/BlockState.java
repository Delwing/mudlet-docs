package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines;

import lombok.Data;

@Data
public class BlockState {

    boolean isCodeBlockOpen;

    public void checkForOpeners(String line) {
        if (line.trim().startsWith("<syntaxhighlight lang=\"lua\">")) {
            isCodeBlockOpen = true;
        }
    }

    public void checkForClosings(String line) {
        if (line.trim().startsWith("</syntaxhighlight")) {
            isCodeBlockOpen = false;
        }
    }

}
