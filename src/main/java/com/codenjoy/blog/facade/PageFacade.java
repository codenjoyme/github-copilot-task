package com.codenjoy.blog.facade;

import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.service.FileService;
import com.codenjoy.blog.service.MarkdownService;
import com.codenjoy.blog.service.PageService;
import com.codenjoy.blog.service.SecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class PageFacade {

    @Value("${git.directory}")
    private String directory;

    private final MarkdownService markdown;
    private final SecretService secret;
    private final FileService files;
    private final PageService pages;

    public String content(String fileName) {
        return markdown.load(directory + "/" + fileName);
    }

    public List<PageDTO> pages(String tag) {
        return pages.pages(directory)
                .filter(page -> page.hasTag(tag))
                .collect(toList());
    }

    public List<String> tags() {
        return pages.tags(directory);
    }
}