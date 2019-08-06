package com.zyrj.usermanagement;

import com.zyrj.usermanagement.dao.ArticleMapper;
import com.zyrj.usermanagement.dao.UserMapper;
import com.zyrj.usermanagement.domain.Article;
import com.zyrj.usermanagement.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsermanagementApplicationTests {

@Autowired
    UserMapper userMapper;
@Autowired
RedisTemplate redisTemplate;
@Autowired
RedisTemplate<Object, Article> redisTemplateArticle;
@Autowired
    ArticleMapper articleMapper;

@Autowired
StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() throws ParseException {
        User user=new User();

SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        for (int i=0;i<30;i++){
            String uuid= UUID.randomUUID().toString().substring(0,6);
            user.setUsername(uuid);
            int year=(int)(Math.random()*50+1960) ;
            int month=(int)(Math.random()*11+1) ;
            int day=(int)(Math.random()*26+1) ;
            user.setBirthday(format.parse(year+"-"+month+"-"+day));
            user.setEmail(uuid+"@163.com");
            user.setNickname(uuid);
            user.setGender(Math.random()>0.5?"M":"F");
            user.setGradeId((int)(Math.random()*4+1));
            user.setTelephone("13456789102");
            userMapper.savaUser(user);
        }

    }


@Test
public void test01(){
    Article article=articleMapper.findArticleById(5);
    System.out.println(article);
    redisTemplateArticle.opsForValue().set("article-01",article);
    if(redisTemplateArticle.hasKey("article-01")){
        System.out.println("1");
    }else{
        System.out.println("没有");
    }

}





}
