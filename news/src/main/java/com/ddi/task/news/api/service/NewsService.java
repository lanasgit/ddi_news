package com.ddi.task.news.api.service;

import com.ddi.task.news.api.dto.NewsListResDto;
import com.ddi.task.news.api.dto.SiteAddReqDto;
import com.ddi.task.news.api.dto.SiteListResDto;
import com.ddi.task.news.api.mapper.NewsMapper;
import com.ddi.task.news.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsMapper newsMapper;

    @Transactional(readOnly = true)
    public List newsList() {
        try {
            List<NewsListResDto> newsList = newsMapper.selectNewsList();
            return newsList;
        } catch (Exception e) {
            throw new CustomException("newsList fail");
        }
    }

    @Transactional(readOnly = true)
    public List keywordList() {
        try {
            List<String> keywordList = newsMapper.selectKeywordList();
            return keywordList;
        } catch (Exception e) {
            throw new CustomException("keywordList fail");
        }
    }

    @Transactional
    public String addKeyword(String keyword) {
        if (newsMapper.keywordExist(keyword) > 0) {
            throw new CustomException("이미 존재하는 키워드입니다.");
        }
        try {
            newsMapper.insertKeyword(keyword);

            // 최초 등록된 키워드 바로 수집 처리
            List<SiteListResDto> SiteList = newsMapper.selectSiteList();
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
                for (Element e : elementData){
                    NewsListResDto newsListResDto = new NewsListResDto();
                    newsListResDto.setTitle(e.text());
                    newsListResDto.setHref(e.attr("href"));

                    if (e.text().contains(keyword) && newsMapper.newsExist(newsListResDto) == 0) {
                        newsMapper.insertNews(newsListResDto);
                    }
                }
            }

            return "키워드 추가 및 수집 완료";
        } catch (Exception e) {
            throw new CustomException("addKeyword fail");
        }
    }

    @Transactional
    public String removeKeyword(String keyword) {
        try {
            newsMapper.deleteKeyword(keyword);
            return "키워드 삭제 완료";
        } catch (Exception e) {
            throw new CustomException("removeKeyword fail");
        }
    }

    @Transactional(readOnly = true)
    public List siteList() {
        try {
            List<SiteListResDto> siteList = newsMapper.selectSiteList();
            return siteList;
        } catch (Exception e) {
            throw new CustomException("siteList fail");
        }
    }

    @Transactional
    public String addSite(SiteAddReqDto siteAddReqDto) {
        try {
            newsMapper.insertSite(siteAddReqDto);
            return "사이트 추가 완료";
        } catch (Exception e) {
            throw new CustomException("addSite fail");
        }
    }

    @Transactional
    public String removeSite(Long siteSn) {
        try {
            newsMapper.deleteSite(siteSn);
            return "사이트 삭제 완료";
        } catch (Exception e) {
            throw new CustomException("removeSite fail");
        }
    }

}
