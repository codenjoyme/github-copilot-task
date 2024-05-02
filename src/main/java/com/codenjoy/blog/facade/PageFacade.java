package com.codenjoy.blog.facade;

import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.dto.PageVisitDTO;
import com.codenjoy.blog.service.*;
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
    private final VisitService visits;

    public String content(String fileName) {
        visits.visit(fileName);
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

    public List<PageVisitDTO> visits() {
        return visits.visits(directory);
    }
}