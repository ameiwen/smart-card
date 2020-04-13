package com.zx.card.system.model.vo;

public class TempleDO {

    private String ftl;
    private String path;

    public TempleDO(String ftl, String path) {
        this.ftl = ftl;
        this.path = path;
    }

    public String getFtl() {
        return ftl;
    }

    public void setFtl(String ftl) {
        this.ftl = ftl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
