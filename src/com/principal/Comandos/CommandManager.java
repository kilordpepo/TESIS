package com.principal.Comandos;

import java.util.HashMap;

/**
 * Universidad Catolica Andres Bello
 * Facultad de Ingenieria
 * Escuela de Ingenieria Informatica
 * Trabajo Especial de Grado
 * ----------------------------------
 * Tutor:
 * --------------
 * Wilmer Pereira
 *
 * Autores:
 * --------------
 * Garry Bruno
 * Carlos Valero
 */
public class CommandManager {

    private static CommandManager commandManager;

    private static final HashMap<String, Class<? extends ICommand>> COMMANDS =
            new HashMap<String, Class<? extends ICommand>>();

    private CommandManager() {
        registCommand(ActualizarFingerCommand.COMMAND_NAME, ActualizarFingerCommand.class);
        registCommand(BuscarRecursoCommand.COMMAND_NAME, BuscarRecursoCommand.class);
        registCommand(DescargarRecursoCommand.COMMAND_NAME, DescargarRecursoCommand.class);
        registCommand(OrganizarAnilloCommand.COMMAND_NAME, OrganizarAnilloCommand.class);
        registCommand(RecibirMensajeCommand.COMMAND_NAME, RecibirMensajeCommand.class);
        registCommand(ExitCommand.COMMAND_NAME, ExitCommand.class);
        registCommand(AsignarRedCommand.COMMAND_NAME, AsignarRedCommand.class);
        registCommand(TipoNodoCommand.COMMAND_NAME, TipoNodoCommand.class);
    }

    public static synchronized CommandManager getIntance() {
        if (commandManager == null) {
            commandManager = new CommandManager();
        }
        return commandManager;
    }

    public ICommand getCommand(String commandName) {
        if (COMMANDS.containsKey(commandName.toUpperCase())) {
            try {
                return COMMANDS.get(commandName.toUpperCase()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return new ErrorCommand();
            }
        } else {
            return new NoEncontradoCommand();
        }
    }

    public void registCommand(String commandName,
                              Class<? extends ICommand> command) {
        COMMANDS.put(commandName.toUpperCase(), command);
    }
}
