package model.command.query;

import lombok.Builder;
import lombok.Value;
import model.command.Command;
import model.dto.PaginatedResult;
import model.dto.PaginationQuery;
import model.entity.EsportGame;

import java.util.Objects;

@Value
@Builder
public class GetGameListQuery implements Command<PaginatedResult<EsportGame>> {

    PaginationQuery pagination;

    @Override
    public boolean isValid() {
        return Objects.nonNull(pagination) && pagination.isValid();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<PaginatedResult<EsportGame>> resultClass() {
        // That's not very elegant, but it is a quick way to work around java generics not being reified
        return (Class) PaginatedResult.class;
    }
}
