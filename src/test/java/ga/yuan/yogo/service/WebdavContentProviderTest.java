package ga.yuan.yogo.service;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.service.impl.WebdavContentProvider;
import ga.yuan.yogo.util.CommonUtil;
import ga.yuan.yogo.util.ContentParserUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public class WebdavContentProviderTest {
    private WebdavContentProvider provider = new WebdavContentProvider();

    @Test
    public void testLastModify() throws IOException, ParseException {
        System.out.println(provider.lastModify());
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
