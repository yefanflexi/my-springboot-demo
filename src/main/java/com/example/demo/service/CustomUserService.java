//package com.example.demo.service;
//
//import com.example.demo.exception.BusinessException;
//import com.example.demo.model.AyUserRoleRel;
//import com.example.demo.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomUserService implements UserDetailsService {
//
//    @Resource
//    private AyUserRoleRelService ayUserRoleRelService;
//    @Resource
//    private UserService userService;
//    @Resource
//    private AyRoleService ayRoleService;
//
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        List<User> userList = userService.findByName(name);
//        if (userList == null || userList.size() <= 0){
//            throw new BusinessException("user is not exit");
//        }
//        User user = userList.get(0);
//        //获取用户所有关联角色
//        List<AyUserRoleRel> ayUserRoleList = ayUserRoleRelService.findByUserId(user.getId());
//        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
//        if (ayUserRoleList != null && ayUserRoleList.size() > 0) {
//            for (AyUserRoleRel rel : ayUserRoleList) {
//                //获取角色关联名称
//                String roleName = ayRoleService.find(rel.getRoleId()).getName();
//                authorityList.add(new SimpleGrantedAuthority(roleName));
//            }
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);
//    }
//}
