package model.command.query;

import lombok.Builder;
import lombok.Value;
import model.command.Command;
import model.entity.EsportGame;
import org.apache.commons.lang3.StringUtils;

@Value
@Builder
public class GetGameQuery implements Command<EsportGame> {
    String title;

    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(title);
    }

    @Override
    public Class<EsportGame> resultClass() {
        return EsportGame.class;
    }
}

