package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
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
        Set<MetaDO> metas = new HashSet<>();

        //        add category metas
        for (int i = 0; i < 5; i++) {
            MetaDO m = new MetaDO();
            m.setName("category-" + i);
            m.setType(MetaTypeEnum.CATEGORY);
            m.setSlug("category-" + i);
            metas.add(m);
        }

        //        add tag metas
        for (int i = 0; i < 5; i++) {
            MetaDO m = new MetaDO();
            m.setName("tag-" + i);
            m.setType(MetaTypeEnum.TAG);
            m.setSlug("tag-" + i);
            metas.add(m);
        }
        metaRepository.saveAll(metas);
    }

    @Test
    public void testContentAddInBatch() {
        Set<ContentDO> contents = new HashSet<>();

        //        add publish post
        for (int i = 0; i < 5; i++) {
            ContentDO c = new ContentDO();
            c.setTitle("This is publish post " + i);
            c.setSlug("This is publish post " + i);
            c.setBody("This is publish post " + i);
            c.setStatus(ContentStatusEnum.PUBLISH);
            c.setType(ContentTypeEnum.POST);
            c.setAllowComment(true);
            contents.add(c);
        }

        //        add draft post
        for (int i = 0; i < 5; i++) {
            ContentDO c = new ContentDO();
            c.setTitle("This is draft post " + i);
            c.setSlug("This is draft post " + i);
            c.setBody("This is draft post " + i);
            c.setStatus(ContentStatusEnum.DRAFT);
            c.setType(ContentTypeEnum.POST);
            c.setAllowComment(true);
            contents.add(c);
        }

        //        add publish page
        for (int i = 0; i < 5; i++) {
            ContentDO c = new ContentDO();
            c.setTitle("This is publish post " + i);
            c.setSlug("This is publish post " + i);
            c.setBody("This is publish post " + i);
            c.setStatus(ContentStatusEnum.PUBLISH);
            c.setType(ContentTypeEnum.PAGE);
            c.setAllowComment(true);
            contents.add(c);
        }

        //        add draft page
        for (int i = 0; i < 5; i++) {
            ContentDO c = new ContentDO();
            c.setTitle("This is draft post " + i);
            c.setSlug("This is draft post " + i);
            c.setBody("This is draft post " + i);
            c.setStatus(ContentStatusEnum.DRAFT);
            c.setType(ContentTypeEnum.PAGE);
            c.setAllowComment(true);
            contents.add(c);
        }
        contentRepository.saveAll(contents);
    }

}
