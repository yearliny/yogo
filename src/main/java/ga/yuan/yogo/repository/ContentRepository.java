package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.ContentDO;
import ga.yuan.yogo.model.enums.ContentStatusEnum;
import ga.yuan.yogo.model.enums.ContentTypeEnum;
import ga.yuan.yogo.model.vo.SitemapVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ContentRepository extends JpaRepository<ContentDO, Long> {

    /**
     * 按照文章类型和文章状态查询文章
     *
     * @param type   文章类型
     * @param status 文章状态
     * @param page   页码和每页文章数量
     * @return Pageable 对象
     */
    Page<ContentDO> findByTypeAndStatusOrderByCreatedDesc(ContentTypeEnum type, ContentStatusEnum status, Pageable page);

    Page<ContentDO> findByTypeAndStatusOrderByCreatedDesc(ContentTypeEnum type, Set<ContentStatusEnum> status, Pageable page);

    /**
     * 查询文章以生成网站地图 sitemap.xml
     *
     * @return List<SitemapVO> 仅包含链接、发表时间的信息
     */
    @Query(value = "SELECT c.slug, c.created FROM ContentDO c WHERE c.type='POST' AND c.status='PUBLISH'")
    List<SitemapVO> findSitemap(Pageable pageable);

}
