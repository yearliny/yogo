package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.entity.MetaDO;
import ga.yuan.yogo.model.enums.MetaTypeEnum;
import ga.yuan.yogo.service.impl.WebdavContentProvider;
import ga.yuan.yogo.util.CommonUtil;
import ga.yuan.yogo.util.ContentParserUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebdavContentProviderTest {
    private WebdavContentProvider provider;
    private ContentDO content = new ContentDO();
    private MetaDO m1 = new MetaDO();
    private MetaDO m2 = new MetaDO();
    private MetaDO m3 = new MetaDO();

    @Before
    public void setup() {
        provider = new WebdavContentProvider("yearliny@outlook.com",
                "ar579tm33ppzf88s",
                "https://dav.jianguoyun.com/dav/Test/Inbox");

        m1.setName("Java");
        m1.setType(MetaTypeEnum.TAG);
        m2.setName("Python");
        m2.setType(MetaTypeEnum.TAG);
        m3.setName("Program");
        m3.setType(MetaTypeEnum.CATEGORY);

        content.setTitle("Hello World!");
        content.setSlug("hello-world");
        content.setCreated(new Date(1373719585000L));
        content.setModified(new Date(1525141800000L));
        content.setAllowComment(true);
        content.setBodyRaw("This is example post!");
        content.addMeta(m1, m2, m3);

    }

    @Test
    public void lastModify() throws IOException, ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expected = format.parse("2019-05-22 13:52:10");
        Date actual = provider.lastModify("https://dav.jianguoyun.com/dav/Test/Inbox/lastModify");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getContent() throws IOException, ParseException {
        List<ContentDO> actual = provider.getContent("/dav/Test/Inbox/getContent");
        // expect
        List<ContentDO> expected = List.of(content);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get() throws IOException, ParseException {
        InputStream is = provider.getSardine().get("https://dav.jianguoyun.com/dav/Documents/Notes/00@Inbox/Chrome%E4%BF%9D%E5%AD%98PDF.md");
        String rawString = CommonUtil.strFromInputStream(is);
        System.out.println(rawString);
        ContentDO content = ContentParserUtil.load(rawString);
        System.out.println(content);
    }
}
