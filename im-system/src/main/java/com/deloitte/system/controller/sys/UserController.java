package com.deloitte.system.controller.sys;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.model.sys.User;
import com.deloitte.system.model.sys.UserOperationVo;
import com.deloitte.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 信息操作处理
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private String prefix = SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/user";

    @Autowired
    private UserService userService;

//    @GetMapping("/index")
//    public String users() {
//        return prefix + "/userList";
//    }
//
    /**
     * 用户个人信息页面
     * */
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Long id, ModelMap modelMap){
        modelMap.put("id",id);
        return prefix + "/profile";
    }

    /**
     * 检验用户输入的原密码是否正确
     * @param userId
     * @param oldPassword
     * @return
     */
    @RequestMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(Long userId, String oldPassword){

        User user = userService.selectUserById(userId);
        if (user != null) {
            String userPassword = user.getPassword();
            PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();
           if(pe.matches(oldPassword, userPassword)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 修改用户密码
     * @param userId
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("/changePassword")
    @ResponseBody
    public AjaxResult changePassword(Long userId, String newPassword, String confirmPassword){

        if (!StringUtils.equals(newPassword, confirmPassword)){
            return AjaxResult.error("新密码与确认密码不一致");
        }
        User user = userService.selectUserById(userId);
        if (user == null) {
            return AjaxResult.error("修改的用户不存在");
        }
        user.setPassword(newPassword);

        return updatePassword(user);
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user) {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 新增用户 element dialog代替
     */
//	@GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody User user) {
        return toAjax(userService.insertUser(user));
    }

    @PostMapping("/find")
    @ResponseBody
    public User findById(Long id) {
        return userService.selectUserById(id);
    }


    @PostMapping("/findByUsername")
    @ResponseBody
    public AjaxResult findByUsername( String username) {
        User user = userService.selectUserByUsername(username);
       return  AjaxResult.success().put("user",user);
    }

    /**
     * 修改用户 element dialog代替
     */
//	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        User user = userService.selectUserById(id);
        mmap.put("user", user);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody User user) {

        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody UserOperationVo userOperationVo) {
        return toAjax(userService.deleteUserByIds(userOperationVo.getIds()));
    }


    /**
     * 重置密码
     */
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult updatePassword(@RequestBody User user) {
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            return error("密码不能为空");
        }
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
        DelegatingPasswordEncoder en = new DelegatingPasswordEncoder("MD5", encoders);
        user.setPassword(en.encode(password));
        userService.updatePassword(user);
        return success();
    }

    /**
     * 重置为固定密码
     */
    @PostMapping("/resetFixedPassword")
    @ResponseBody
    public AjaxResult resetFixedPassword(Long userId){
        userService.resetFixedPassword(userId);
        return success();
    }

    public static void main(String[] args) {
        ;
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
        DelegatingPasswordEncoder en = new DelegatingPasswordEncoder("MD5", encoders);
        System.out.println((en.encode("Admin123456")));

        MessageDigestPasswordEncoder mdpe = new MessageDigestPasswordEncoder("MD5");
        String str = mdpe.encode("123");

        System.out.println(str);
        PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        System.out.println(en.matches("123",str));

        System.out.println(pe.matches("Qyb123","{MD5}{IwHAwNMvAe2BWoJZoBG1NeLTo9foEj0cdl8hRZplXno=}ce8908f702a3b5a9a459fc6eea626d59")+"");

    }

}
