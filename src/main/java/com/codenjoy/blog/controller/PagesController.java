package com.codenjoy.blog.controller;


import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.dto.PageVisitDTO;
import com.codenjoy.blog.facade.PageFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.codenjoy.blog.controller.PagesController.REST_URL;
import static com.codenjoy.blog.controller.Samples.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.TEXT_PLAIN_VALUE;

/**
 * <a href="https://restfulapi.net/resource-naming/">REST Api naming conventions</a>
 * Please make sure that names of methods are the same as in the model.js file and
 * PagesControllerTest - it will help to work with client side code.
 * Another thing is that the order of methods is important - it should be the same
 * as in the model.js file and in the Swagger UI.
 *
 *   HTTP GET    /api/pages?tag={tag}
 *      Get a list of all Pages. Tag paremater is optional.
 *      @see PagesController#getAllPages(String)
 *   
 *   HTTP GET    /api/pages/{pageName}
 *      Obtaining a Page content by name.
 *      @see PagesController#getPage(String)
 *
 *   HTTP GET    /api/pages/tags
 *      Get a list of all pages tags.
 *      @see PagesController#getAllTags()
 *
 *   HTTP GET    /api/pages/visits
 *      Get a list of all pages visits.
 *      @see PagesController#getAllVisits()
 */
@Slf4j
@Tag(name = "Pages API")
@RestController
@RequestMapping(REST_URL)
@AllArgsConstructor
public class PagesController {

    public static final String REST_URL = "/api";

    private final PageFacade pages;

    @Operation(summary = "Get a list of all Pages",
            description = "Pages are returned with path and description.",
            parameters = @Parameter(name = "tag", allowEmptyValue = true,
                                    description = "Tag name to filter"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. Pages list",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE, 
                                               examples = @ExampleObject(SAMPLE_PAGES))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE, 
                                               examples = @ExampleObject("Internal server error")))
            })
    @Order(1)
    @GetMapping("/pages")
    public List<PageDTO> getAllPages(@RequestParam(name = "tag", required = false) String tag) {
        return pages.pages(tag);
    }

    @Operation(summary = "Obtaining a Page by path",
            description = "A complete page file.",
            parameters = @Parameter(name = "fileName", description = "Page name", example = SAMPLE_PAGE_NAME),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. Page content",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE,
                                    examples = @ExampleObject(SAMPLE_PAGE_CONTENT))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE,
                                    examples = @ExampleObject("Internal server error")))
            })
    @Order(2)
    @GetMapping("/pages/{fileName}")
    public ResponseEntity<String> getPage(@PathVariable("fileName") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(TEXT_PLAIN_VALUE + ";charset=" + UTF_8.name()))
                .body(pages.content(fileName));
    }

    @Operation(summary = "Get a list of all pages tags",
            description = "Distinct tags from all pages sorted alphabetically.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. Tags list",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(SAMPLE_TAGS))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE,
                                    examples = @ExampleObject("Internal server error")))
            })
    @Order(3)
    @GetMapping("/pages/tags")
    public List<String> getAllTags() {
        return pages.tags();
    }

    @Operation(summary = "Get a list of all pages visits",
            description = "Visits of all pages.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. Visits list",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(SAMPLE_VISITS))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE,
                                    examples = @ExampleObject("Internal server error")))
            })
    @Order(4)
    @GetMapping("/pages/visits")
    public List<PageVisitDTO> getAllVisits() {
        return pages.visits();
    }
}