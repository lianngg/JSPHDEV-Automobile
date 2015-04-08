package server;

import java.util.Properties;

import model.Automobile;
import adapter.BuildAuto;

public class BuildCarModelOptions {
    public Automobile buildAutoOptions(Properties properties) {
        AutoServer server = new BuildAuto();
        return server.acceptClientPropertiesObject(properties);
    }
}
