package com.zx.card.system.service.impl;

import com.zx.card.system.dao.MenuMapper;
import com.zx.card.system.dao.RoleMenuMapper;
import com.zx.card.system.model.Menu;
import com.zx.card.system.model.vo.Tree;
import com.zx.card.system.service.MenuService;
import com.zx.card.utils.BuildTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Set<String> getUserPermsByID(Integer userId) {
        List<String> perms = menuMapper.selectUsersPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<Menu>> selectMenuTreeByID(Integer id) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menus = menuMapper.selectMenuTreeByID(id);
        for (Menu menu : menus) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(menu.getId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", menu.getUrl());
            attributes.put("icon", menu.getIcon());
            attributes.put("tag",menu.getTag());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
        return list;
    }

    @Override
    public List<Menu> selectAllMenu() {
        List<Menu> list = menuMapper.selectMenuListWhere(new HashMap<>());
        return list;
    }

    @Override
    public Menu selectMenuByID(Integer id) {
        Menu menu = menuMapper.selectMenuByPrimaryKey(id);
        return menu;
    }

    @Override
    public int insertMenu(Menu menu) {
        return menuMapper.insertMenuSelective(menu);
    }

    @Override
    public int updateMenuByID(Menu menu) {
        return menuMapper.updateMenuByPrimaryKeySelective(menu);
    }

    @Override
    public int removeMenuByID(Integer id) {
        return menuMapper.deleteMenuByPrimaryKey(id);
    }

    @Override
    public Tree<Menu> selectMenuTree() {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menus = menuMapper.selectMenuListWhere(new HashMap<>());
        for (Menu menu : menus) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(menu.getId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<Menu> selectMenuTree(Integer roleId) {
        // 根据roleId查询权限
        List<Menu> menus = menuMapper.selectMenuListWhere(new HashMap<>());
        List<Integer> menuIds = roleMenuMapper.listMenuIdByRoleId(roleId);
        List<Integer> temp = menuIds;
        for (Menu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menuDOs = menuMapper.selectMenuListWhere(new HashMap<>());
        for (Menu sysMenuDO : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            Integer menuId = sysMenuDO.getId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }
}
