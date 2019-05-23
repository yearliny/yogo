package ga.yuan.yogo.service.impl;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.service.ContentProviderService;
import ga.yuan.yogo.util.CommonUtil;
import ga.yuan.yogo.util.ContentParserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客关联 Webdav，即可同步 Webdav 中的内容到博客中
 */
@Slf4j
@Service
public class WebdavContentProvider implements ContentProviderService {
    private Sardine sardine;
    private String baseUrl;
    /**
     * 比如 https://dav.jianguoyun.com/dav/ 中的 https://dav.jianguoyun.com 这一部分。
     * 使用 baseDomain 可以和 DavResource.getHref 形成新的资源链接，便于获取
     */
    private String baseDomain;

    private WebdavContentProvider() {
    }

    public WebdavContentProvider(String username, String password, String baseUrl) {
        this.sardine = SardineFactory.begin(username, password);
        this.baseUrl = baseUrl;
        this.baseDomain = baseUrl.substring(0, baseUrl.indexOf("/", 8));
    }

    public Sardine getSardine() {
        return sardine;
    }

    /**
     * 将路径下的 *.md 文件转换成 ContentDO 对象
     *
     * @param path 相对于 {@link WebdavContentProvider#baseDomain} 的路径，需要包含最开始的 "/" 斜杠。
     *             如果为空字符则视为转换主路径下的 *.md 文件。
     * @return 转换后的 List<ContentDO>
     * @throws IOException 转换失败
     */
    public List<ContentDO> getContent(String path) throws IOException, ParseException {
        List<ContentDO> contents = new ArrayList<>();
        List<DavResource> resources;
        try {
            resources = sardine.list(baseDomain + path);
            for (DavResource resource : resources) {
                if (!resource.isDirectory() && resource.getDisplayName().endsWith(".md")) {
                    String url = baseDomain + resource.getPath();
                    InputStream is = sardine.get(url);
                    String rawString = CommonUtil.strFromInputStream(is);
                    contents.add(ContentParserUtil.load(rawString));
                } else if (resource == resources.get(0)) {
                    continue;
                } else {
                    contents.addAll(getContent(resource.getPath()));
                }
            }
            contents.sort((a, b) -> b.getModified().compareTo(a.getModified()));
            return contents;
        } catch (IOException e) {
            throw new IOException("无法从Webdav服务器获取资源");
        }
    }


    @Override
    public Date lastModify() throws IOException {
        return lastModify(baseUrl);
    }

    public Date lastModify(String path) throws IOException {
        try {
            List<DavResource> resources = sardine.list(path);
            // resources 第一个资源就是当前目录
            return resources.get(0).getModified();
        } catch (IOException e) {
            throw new IOException("无法从Webdav服务器获取资源");
        }
    }


    @Override
    public List<ContentDO> getContent() throws IOException, ParseException {
        return getContent(baseUrl);
    }

    @Override
    public List<ContentDO> getContent(Date start) {
        return null;
    }
}
