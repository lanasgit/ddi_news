package com.ddi.task.news.api.mapper;

import com.ddi.task.news.api.dto.NewsListResDto;
import com.ddi.task.news.api.dto.SiteAddReqDto;
import com.ddi.task.news.api.dto.SiteListResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    List<NewsListResDto> selectNewsList();
    List<String> selectKeywordList();
    int keywordExist(String keyword);
    void insertKeyword(String keyword);
    int newsExist(NewsListResDto newsListResDto);
    void insertNews(NewsListResDto newsListResDto);
    void deleteKeyword(String keyword);
    List<SiteListResDto> selectSiteList();
    void insertSite(SiteAddReqDto siteAddReqDto);
    void deleteSite(long siteSn);
}
