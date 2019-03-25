package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.model.enums.MetaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MetaRepository metaRepository;

    /**
     * 测试多对多关系绑定,持久态的实体，可以通过插入仅仅设置外键id的瞬时态来进行插入
     * result: 先查询content，再通过id查询meta，最后关联关系
     */
    @Test
    public void testRelateMeta() {
        var content = contentRepository.getOne(1L);
        var meta = new Meta();
        meta.setMid(7L);
        content.addMeta(meta);
        contentRepository.saveAndFlush(content);
    }

    /**
     * 二者都是瞬时态，但是meta手动设定了id
     * result:可以发现，实际操作还是先通过meta id 查询出持久态对象，然后进行关联
     */
    @Test
    public void testCreateRelateMeta() {
        Content content = new Content();
        content.setTitle("-----testCreateRelateMeta----------");

        contentRepository.saveAndFlush(content);

        Meta meta = new Meta();
        meta.setMid(6L);

        content.getMetas().add(meta);

        contentRepository.saveAndFlush(content);
    }

    /**
     * 两者都是瞬时态，且都未设定id
     * result: 正确的先插入 content、meta，然后再关联关系
     */
    @Test
    public void testTransientRelateMeta() {
        Content content = new Content();
        content.setTitle("-----testTransientRelateMeta----------");

        Meta meta = new Meta();
        meta.setName("testTransientRelateMeta");
        meta.setType(MetaType.TAG);

        content.getMetas().add(meta);

        contentRepository.saveAndFlush(content);
    }

}
