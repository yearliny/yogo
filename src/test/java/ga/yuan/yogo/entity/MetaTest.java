package ga.yuan.yogo.entity;

import ga.yuan.yogo.model.entity.Meta;
import ga.yuan.yogo.model.enums.MetaType;
import ga.yuan.yogo.repository.MetaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetaTest {
    @Autowired
    private MetaRepository metaRepository;

    @Test
    public void testAdd(){
        Meta m1 = new Meta();
        m1.setName("health");
        m1.setSlug("health");
        m1.setType(MetaType.CATEGORY);
        metaRepository.save(m1);
    }

    @Test
    public void testFindByType(){
        List<Meta> m = metaRepository.findByType(MetaType.CATEGORY);
        System.out.printf(m.get(0).toString());
        testAdd();
    }
}
