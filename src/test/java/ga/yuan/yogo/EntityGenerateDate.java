package ga.yuan.yogo;

import ga.yuan.yogo.model.entity.Comment;
import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.model.enums.ContentStatus;
import ga.yuan.yogo.model.enums.ContentType;
import ga.yuan.yogo.model.enums.MetaType;
import ga.yuan.yogo.repository.CommentRepository;
import ga.yuan.yogo.repository.ContentRepository;
import ga.yuan.yogo.repository.MetaRepository;
import ga.yuan.yogo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityGenerateDate {
    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void test1() {
        Meta m1 = new Meta();
        m1.setMid(1L);
        m1.setName("health");
        m1.setSlug("health");
        m1.setType(MetaType.CATEGORY);
        Meta meta = metaRepository.save(m1);

    }

    @Test
    public void test2() {
        Meta m2 = new Meta();
        m2.setMid(2L);
        m2.setName("linux");
        m2.setSlug("linux");
        m2.setType(MetaType.TAG);
        Meta meta = metaRepository.save(m2);
    }

    @Test
    public void test3() {
        User u = new User();
        u.setCreated(new Date());
        u.setDisplayName("yearliny");
        u.setMail("yearliny@outlook.com");
        u.setUid(1L);
        userRepository.save(u);
    }

    @Test
    public void test4() {
        Content c = new Content();
        c.setAuthor(userRepository.findById(3L).get());
        c.setTitle("Hello World");
        c.setBody("ddaw dawdaw");
        c.setStatus(ContentStatus.PUBLISH);
        c.setType(ContentType.POST);
        Meta m1 = metaRepository.findById(1L).get();
        Meta m2 = metaRepository.findById(2L).get();

        c.getMetas().add(m1);
        c.getMetas().add(m2);

        m1.getContents().add(c);
        m2.getContents().add(c);

    }

    @Test
    public void test5() {
        Content c = contentRepository.findById(4L).get();
        Meta m1 = metaRepository.findById(1L).get();
        Meta m2 = metaRepository.findById(2L).get();

        Set<Meta> metas = new HashSet<>();
        metas.add(m1);
        metas.add(m2);

        c.setMetas(metas);

    }

    @Test
    public void test6() {
        Meta m1 = new Meta();
        m1.setName("health");
        m1.setSlug("health");
        m1.setType(MetaType.CATEGORY);

        Meta m2 = new Meta();
        m2.setName("linux");
        m2.setSlug("linux");
        m2.setType(MetaType.TAG);

        Content c = new Content();
        c.setTitle("Hello World");
        c.setBody("This is dump data");
        c.setStatus(ContentStatus.PUBLISH);
        c.setType(ContentType.POST);

        c.getMetas().add(m1);
        c.getMetas().add(m2);

        contentRepository.save(c);
    }

    @Test
    public void test7() {
        Comment c1 = new Comment();
        c1.setAuthor("mom");
        c1.setBody("Hello!");
        commentRepository.save(c1);

        Comment c2 = new Comment();
        c2.setAuthor("yearliny");
        c2.setBody("Hello too!");
        c2.setParent(c1);

        commentRepository.save(c2);

    }
}
