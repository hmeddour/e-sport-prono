package mediator;

import exception.NoHandlerForCommandException;
import model.command.Command;
import model.command.CommandHandler;

import java.util.Optional;
import java.util.Set;
public class MediatorProxy extends BusinessMediator {
    private Mediator delegate;

    public MediatorProxy(Set<CommandHandler<?, ?>> handlers) {
        super(handlers);
    }

    @Override
    public <R,T extends Command<R>> R execute(T command) {
        return Optional.ofNullable(delegate.execute(command))
                .orElseThrow(() -> new NoHandlerForCommandException(command));
    }

    public void setDelegate(Mediator mediator) {
        delegate = mediator;
    }
}
