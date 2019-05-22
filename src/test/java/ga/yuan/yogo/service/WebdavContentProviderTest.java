package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.ContentDO;
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

    @Before
    public void setup() {
        provider = new WebdavContentProvider("yearliny@outlook.com",
                "ar579tm33ppzf88s",
                "https://dav.jianguoyun.com/dav/Test/Inbox");
    }

    @Test
    public void lastModify() throws IOException, ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expected = format.parse("2019-05-22 13:52:10");
        Date actual = provider.lastModify("https://dav.jianguoyun.com/dav/Test/Inbox/lastModify");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetContent() throws IOException, ParseException {
        List<ContentDO> contents = provider.getContent("/dav/Documents/Notes/00%40Inbox");
        System.out.println(contents);
    }

    @Test
    public void testGet() throws IOException, ParseException {
        InputStream is = provider.getSardine().get("https://dav.jianguoyun.com/dav/Documents/Notes/00@Inbox/Chrome%E4%BF%9D%E5%AD%98PDF.md");
        String rawString = CommonUtil.strFromInputStream(is);
        System.out.println(rawString);
        ContentDO content = ContentParserUtil.load(rawString);
        System.out.println(content);
    }
}
