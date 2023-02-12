package command;

public class InitialContext {
    private final DaPizdaCommand daPizdaCommand = new DaPizdaCommand();
    private final MemCommand memCommand = new MemCommand();
    private final ShowDickSizeCommand showDickSizeCommand = new ShowDickSizeCommand();
    private final ShowPidorCommand showPidorCommand = new ShowPidorCommand();

    public Command getCommandFromCache(String commandName) {
        switch (commandName) {
            case "/dick@mrkMy_bot":
                return showDickSizeCommand;
            case "/pidor@mrkMy_bot":
                return showPidorCommand;
            case "memcmnd":
                return memCommand;
            case "dacmnd":
                return daPizdaCommand;
            default:return null;
        }
    }
}
