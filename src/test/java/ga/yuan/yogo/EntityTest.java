package ga.yuan.yogo;

import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.model.enums.MetaType;
import ga.yuan.yogo.repository.ContentRepository;
import ga.yuan.yogo.repository.MetaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-dev.properties")
public class EntityTest {
    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private ContentRepository contentRepository;

//    添加一个 meta
    @Test
    public void testMeta1(){
        Meta m1 = new Meta();
        m1.setMid(1L);
        m1.setName("health");
        m1.setSlug("health");
        m1.setType(MetaType.CATEGORY);
        Meta meta = metaRepository.save(m1);
        Assert.assertEquals("meta add fail", meta, metaRepository.findById(1L).get());
    }

//    按类型查找 meta
    @Test
    public void testMeta2(){
        List<Meta> m = metaRepository.findByType(MetaType.CATEGORY);
        Assert.assertEquals("find meta by type failure",
                metaRepository.findById(1L).get(),
                m.get(0));
    }

//    添加
}
