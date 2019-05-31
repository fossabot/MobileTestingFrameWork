package Configuration;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum Arg implements ServerArgument {

    TIMEOUT("--command-timeout"),
    LOG_LEVEL("--log-level"),
    WDALOCALPORT("--webdriveragent-port"),
    BootstrapPort("-bp "),
    SESSIONOVERRIDE("--session-override"),
    ADDRESS("-a"),
    PORT("-P"),
    CALLBACKPORT("-cp"),
    NODECONFIG("--nodeconfig"),
    HUBROLE("-role hub");




    private  final  String arg;
    Arg(String arg) {
        this.arg = arg;
    }

    @Override
    public String getArgument() {
        return arg;
    }


}