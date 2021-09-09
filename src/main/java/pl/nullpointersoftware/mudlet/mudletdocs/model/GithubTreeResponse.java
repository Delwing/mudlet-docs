package pl.nullpointersoftware.mudlet.mudletdocs.model;

import lombok.Data;

import java.util.List;

@Data
public class GithubTreeResponse {

    private String sha;
    private String url;
    private List<TreeItem> tree;

    @Data
    public static class TreeItem {
        private String path;
        private String url;
        private String sha;
    }

}
