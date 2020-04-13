package com.zx.card.system.service;


import com.zx.card.system.model.Menu;
import com.zx.card.system.model.vo.Tree;

import java.util.List;
import java.util.Set;

public interface MenuService {

    Set<String> getUserPermsByID(Integer userId);

    List<Tree<Menu>> selectMenuTreeByID(Integer id);

    List<Menu> selectAllMenu();

    Menu selectMenuByID(Integer id);

    int insertMenu(Menu menu);

    int updateMenuByID(Menu menu);

    int removeMenuByID(Integer id);

    Tree<Menu> selectMenuTree();

    Tree<Menu> selectMenuTree(Integer roleId);


}
