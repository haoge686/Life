package cn.lh.life.project.system.user.controller;

import java.util.List;

import cn.lh.life.framework.config.NTSConfig;
import cn.lh.life.framework.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import cn.lh.life.project.system.menu.domain.Menu;
import cn.lh.life.project.system.menu.service.IMenuService;
import cn.lh.life.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author nts.sunline.cn
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private NTSConfig ntsConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", ntsConfig.getCopyrightYear());
        mmap.put("demoEnabled", ntsConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", ntsConfig.getVersion());
        return "main";
    }
}
