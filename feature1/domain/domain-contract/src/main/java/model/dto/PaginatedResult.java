package model.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PaginatedResult<T> {
    List<T> page;
    PaginationQuery query;
    Integer numberOfPages;
    Integer itemCount;
}
