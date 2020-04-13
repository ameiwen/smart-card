package com.zx.card.system.model.vo;

import java.util.List;

public class UserVO {
    //vo字段
    private String roleNames;

    private List<Integer> roleIds;

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
