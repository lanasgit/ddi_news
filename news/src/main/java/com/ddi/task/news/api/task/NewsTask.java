package com.ddi.task.news.api.task;

import com.ddi.task.news.api.dto.NewsListResDto;
import com.ddi.task.news.api.dto.SiteListResDto;
import com.ddi.task.news.api.mapper.NewsMapper;
import com.ddi.task.news.common.exception.CustomException;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.List;

@Component
@AllArgsConstructor
public class NewsTask {

    private final NewsMapper newsMapper;

    @Scheduled(cron = "0 0 * * * *") // 수집 주기: 매시 정각
    public void newsTaskScheduled() {
        try {
            // 등록된 키워드 수집 처리
            List<String> keywordList = newsMapper.selectKeywordList();
            List<SiteListResDto> SiteList = newsMapper.selectSiteList();
            for (String keyword : keywordList) {
                for (SiteListResDto dto : SiteList) {
                    Document doc;
                    if (dto.getSitePath().endsWith("=")) {
                        doc = Jsoup.connect(dto.getSitePath() + URLEncoder.encode(keyword, "UTF-8")).get();
                    } else {
                        doc = Jsoup.connect(dto.getSitePath()).get();
                    }
                    Elements elementData;
                    if (!dto.getClassNm().isEmpty()) {
                        elementData = doc.select("a").select(dto.getClassNm());
                    } else {
                        elementData = doc.select("a");

                    }
                    for (Element e : elementData) {
                        NewsListResDto newsListResDto = new NewsListResDto();
                        newsListResDto.setTitle(e.text());
                        newsListResDto.setHref(e.attr("href"));

                        if (e.text().contains(keyword) && newsMapper.newsExist(newsListResDto) == 0) {
                            newsMapper.insertNews(newsListResDto);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new CustomException("newsTaskScheduled fail");
        }
    }
}
