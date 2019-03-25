package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.model.enums.ContentStatus;
import ga.yuan.yogo.model.enums.ContentType;
import ga.yuan.yogo.model.enums.MetaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GenerateDataTest {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MetaRepository metaRepository;

    @Test
    public void testMetaAddInBatch() {
        Set<Meta> metas = new HashSet<>();

        //        add category metas
        for (int i = 0; i < 5; i++) {
            Meta m = new Meta();
            m.setName("category-" + i);
            m.setType(MetaType.CATEGORY);
            m.setSlug("category-" + i);
            metas.add(m);
        }

        //        add tag metas
        for (int i = 0; i < 5; i++) {
            Meta m = new Meta();
            m.setName("tag-" + i);
            m.setType(MetaType.TAG);
            m.setSlug("tag-" + i);
            metas.add(m);
        }
        metaRepository.saveAll(metas);
    }

    @Test
    public void testContentAddInBatch() {
        Set<Content> contents = new HashSet<>();

        //        add publish post
        for (int i = 0; i < 5; i++) {
            Content c = new Content();
            c.setTitle("This is publish post " + i);
            c.setSlug("This is publish post " + i);
            c.setBody("This is publish post " + i);
            c.setStatus(ContentStatus.PUBLISH);
            c.setType(ContentType.POST);
            c.setAllowComment(true);
            contents.add(c);
        }

        //        add draft post
        for (int i = 0; i < 5; i++) {
            Content c = new Content();
            c.setTitle("This is draft post " + i);
            c.setSlug("This is draft post " + i);
            c.setBody("This is draft post " + i);
            c.setStatus(ContentStatus.DRAFT);
            c.setType(ContentType.POST);
            c.setAllowComment(true);
            contents.add(c);
        }

        //        add publish page
        for (int i = 0; i < 5; i++) {
            Content c = new Content();
            c.setTitle("This is publish post " + i);
            c.setSlug("This is publish post " + i);
            c.setBody("This is publish post " + i);
            c.setStatus(ContentStatus.PUBLISH);
            c.setType(ContentType.PAGE);
            c.setAllowComment(true);
            contents.add(c);
        }

        //        add draft page
        for (int i = 0; i < 5; i++) {
            Content c = new Content();
            c.setTitle("This is draft post " + i);
            c.setSlug("This is draft post " + i);
            c.setBody("This is draft post " + i);
            c.setStatus(ContentStatus.DRAFT);
            c.setType(ContentType.PAGE);
            c.setAllowComment(true);
            contents.add(c);
        }
        contentRepository.saveAll(contents);
    }

}
