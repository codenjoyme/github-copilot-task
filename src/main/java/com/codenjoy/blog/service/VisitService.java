package com.codenjoy.blog.service;

import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.dto.PageVisitDTO;
import com.codenjoy.blog.entity.Visit;
import com.codenjoy.blog.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@Component
public class VisitService {

    private final VisitorRepository visitors;
    private final PageService pages;

    public List<PageVisitDTO> visits(String directory) {
        Stream<PageDTO> allPages = pages.pages(directory);
        List<Visit> visitedPages = visitors.findAll();

        allPages.forEach(page -> {
            if (visitedPages.stream().noneMatch(a -> a.getFileName().equals(page.getFileName()))) {
                visitedPages.add(new Visit(page.getFileName(), 0));
            }
        });

        return visitedPages.stream()
                .map(a -> new PageVisitDTO(a.getFileName(), a.getViews()))
                .sorted(Comparator.comparing(PageVisitDTO::getFileName))
                .collect(toList());
    }

    public void visit(String fileName) {
        visitors.save(visitors.findByFileName(fileName)
                .orElse(new Visit(fileName, 0))
                .increaseViews());
    }
}
