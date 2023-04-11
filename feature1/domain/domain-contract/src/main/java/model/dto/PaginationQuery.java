package model.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder(toBuilder = true)
public class PaginationQuery {
    Integer currentPage;
    Integer pageSize;

    /**
     * Check if this pagination instance is valid
     *
     * @return true if it is, false otherwise
     */
    public boolean isValid() {
        return
                Objects.nonNull(currentPage) &&
                        currentPage >= 0 &&
                        Objects.nonNull(pageSize) &&
                        pageSize >= 0;
    }
}
