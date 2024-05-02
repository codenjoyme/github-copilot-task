package com.codenjoy.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "The Page visits is represented by its file name and number of views.")
public class PageVisitDTO {

    @Schema(description = "File name")
    private String fileName;

    @Schema(description = "Number of views")
    private int views;
}
