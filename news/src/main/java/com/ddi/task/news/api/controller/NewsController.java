package com.ddi.task.news.api.controller;

import com.ddi.task.news.api.dto.SiteAddReqDto;
import com.ddi.task.news.api.service.NewsService;
import com.ddi.task.news.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public Response newsList() {
        return Response.success(newsService.newsList());
    }

    @GetMapping("/keyword")
    public Response<List> KeywordList() {
        return Response.success(newsService.keywordList());
    }

    @PostMapping("/keyword")
    public Response KeywordAdd(String keyword) {
        return Response.success(newsService.addKeyword(keyword));
    }

    @DeleteMapping("/keyword")
    public Response KeywordRemove(String keyword) {
        return Response.success(newsService.removeKeyword(keyword));
    }

    @GetMapping("/site")
    public Response SiteList() {
        return Response.success(newsService.siteList());
    }

    @PostMapping("/site")
    public Response SiteAdd(SiteAddReqDto siteAddReqDto) {
        return Response.success(newsService.addSite(siteAddReqDto));
    }

    @DeleteMapping("/site")
    public Response SiteRemove(Long siteSn) {
        return Response.success(newsService.removeSite(siteSn));
    }

}
