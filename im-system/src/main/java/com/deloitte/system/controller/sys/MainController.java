package com.deloitte.system.controller.sys;

import com.deloitte.framework.support.BaseController;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.model.sys.Menu;
import com.deloitte.system.model.sys.User;
import com.deloitte.system.service.MenuService;
import com.deloitte.system.service.UserService;
import com.deloitte.system.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class MainController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Resource
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String indexPage(ModelMap modelMap) {

        // 根据用户id取出菜单
        List<Menu> menus = menuService.getMenuByUser(UserUtils.getUserId());
        User user = userService.selectUserById(UserUtils.getUserId());

        modelMap.put("menus", menus);
        modelMap.put("user", user);

        return SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/core/index";
    }

}
