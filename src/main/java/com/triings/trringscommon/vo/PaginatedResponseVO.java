package com.triings.trringscommon.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseVO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -7882290922087005939L;

    @JsonProperty("items")
    private List<T> items;
    @JsonProperty("totalItems")
    private Long totalItems;
    @JsonProperty("pageNumber")
    private int pageNumber;
    @JsonProperty("pageSize")
    private int pageSize;
    @JsonProperty("totalPages")
    private int totalPages;
    @JsonProperty("isLastPage")
    private Boolean isLastPage;
}
