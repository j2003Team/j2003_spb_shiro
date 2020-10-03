package com.qf.j2003;

import com.qf.j2003.po.SysPerm;
import com.qf.j2003.po.SysUser;
import com.qf.j2003.service.SysPermService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class SpbShiroAppApplicationTests {

    @Autowired(required = false)
    private SysPermService permService;
    @Test
    public void contextLoads() {
        List<SysPerm> persm = permService.findPermsByName("admin");

        System.out.println(persm.size());

    }
    @Test
    public void test1(){
        SysUser admin = permService.findUserByName("admin");
        System.out.println(admin);

    }

    @Test
    public void testMD5hsah(){
       String pwd ="1234";
        Md5Hash md5Hash = new Md5Hash(pwd,null,2);
        String s = md5Hash.toString();
        System.out.println(s);

    }
    @Test
    public void testMD5(){
        String pwd ="1234";
      // new MD5(pwd);
        System.out.println();

    }

    @Test
    public void testMd5(){
        String pwd ="1234";
        String salt ="admin";
        Md5Hash md5Hash = new Md5Hash(pwd,salt,2);
        String string1 = md5Hash.toString();
        System.out.println(string1);
        SimpleHash md5 = new SimpleHash("MD5", pwd, salt, 2);
        System.out.println(md5.toString());

    }
}
