package ga.yuan.yogo;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.repository.ContentRepository;
import ga.yuan.yogo.repository.MetaRepository;
import ga.yuan.yogo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MetaRepository metaRepository;


    @Test
    public void contextLoads() { }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("yearliny");
        user.setPassword("123456");
        user.setMail("yearliny@outlook.com");
        user.setUrl("https://yuan.ga");
        user.setDisplayName("Feng");
        user.setCreated(new Date());

        userRepository.save(user);
    }

    @Test
    public void addContentWithUser() {
        User user = userRepository.getOne(1L);
        Content c1 = new Content();
        Content c2 = new Content();

        c1.setAuthor(user);
        c2.setAuthor(user);
        c1.setTitle("Hello World!");
        c2.setTitle("This is second post!");
        c1.setCreated(new Date(System.currentTimeMillis()));
        c2.setCreated(new Date(System.currentTimeMillis()-1000));

        List<Content> contents = new ArrayList<>();
        contents.add(c1);
        contents.add(c2);

        contentRepository.saveAll(contents);
    }

    public void testEntityCollation(){

    }

}
