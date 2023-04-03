package mediator;

import exception.NoHandlerForCommandException;
import exception.NullCommandException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.command.Command;
import model.command.CommandHandler;

import java.util.Objects;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class BusinessMediator implements Mediator {

    private final Set<CommandHandler<?, ?>> handlers;

    @Override
    // We suppress the unchecked warning because the filter we used already guarantees the handler can manage the command
    @SuppressWarnings("unchecked")
    public <R, T extends Command<R>> R execute(T command) {
        if (Objects.isNull(command)) {
            throw new NullCommandException();
        }
        return handlers.stream()
                .filter(candidate -> candidate.canHandle(command))
                .findFirst()
                .map(handler -> ((CommandHandler<R, T>) handler).handle(command))
                .orElseThrow(() -> {
                            log.error("No handler found for command of type {}", command.getClass().getSimpleName());
                            throw new NoHandlerForCommandException(command);
                        }
                );
    }
}
