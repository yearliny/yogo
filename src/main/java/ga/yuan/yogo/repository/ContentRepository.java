package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.Content;
import ga.yuan.yogo.model.enums.ContentStatus;
import ga.yuan.yogo.model.enums.ContentType;
import ga.yuan.yogo.model.vo.SitemapVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ContentRepository extends JpaRepository<Content, Long> {

    /**
     * 按照文章类型和文章状态查询文章
     *
     * @param type   文章类型
     * @param status 文章状态
     * @param page   页码和每页文章数量
     * @return Pageable 对象
     */
    Page<Content> findByTypeAndStatusOrderByCreatedDesc(ContentType type, ContentStatus status, Pageable page);

    Page<Content> findByTypeAndStatusOrderByCreatedDesc(ContentType type, Set<ContentStatus> status, Pageable page);

    /**
     * 查询文章以生成网站地图 sitemap.xml
     *
     * @return List<Content> 仅包含链接、发表时间的信息
     */
    @Query(nativeQuery = true, value = "SELECT c.slug, c.created FROM yg_contents c WHERE c.type='POST' AND c.status='PUBLISH' ORDER BY c.created, c.cid DESC LIMIT ?1")
    List<SitemapVO> listSitemap(int size);

}
