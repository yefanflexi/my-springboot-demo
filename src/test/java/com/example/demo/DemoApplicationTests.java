package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void postgresqlTest() {
        String sql = "select id,username,password from users";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user =  new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        System.out.println("query success:");
        for (User user:userList){
            System.out.println(user.toString());
        }
    }

    @Test
    public void testRepository(){
        //查询所有数据
        List<User> userList = userService.findAll();
        System.out.println("findAll()========="+userList.size());
        //通过name查询数据
        List<User> userList1 = userService.findByName("张三");
        System.out.println("findByName====="+userList1.toString());
        Assert.isTrue(userList1.get(0).getUsername().equals("张三"),"data error");
        //通过ids查询数据
        List<String> ids =  new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        List<User> userList2 = userService.findByIdIn(ids);
        System.out.println("findByIdIn()========="+userList2.size());
        //分页查询
        PageRequest pageRequest = new PageRequest(0,10);
        Page<User> userList3 = userService.findAll(pageRequest);
        System.out.println("page findAll()========="+userList3.getSize()+"/"+userList3.getTotalPages());
        //新增数据
        User user = new User();
        user.setId("4");
        user.setUsername("haha");
        user.setPassword("haha");
        userService.save(user);
        //删除数据
        //userService.delete("4");

    }

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","aa");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
        //删除
        redisTemplate.delete("name");
        redisTemplate.opsForValue().set("name","ay");
        name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);

    }
}
