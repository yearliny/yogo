package ga.yuan.yogo.config;

import com.rometools.rome.feed.atom.Entry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用于生成网站的 RSS
 */
@Component("atomFeedView")
public class YogoAtomFeedView extends AbstractAtomFeedView {
    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
