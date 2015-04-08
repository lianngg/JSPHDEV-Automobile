package adapter;

import scale.EditThread;
import server.AutoServer;


public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, EditThread, AutoServer {
    // Empty class for extensibility.
    // Implementation details are in ProxyAutomobile.
}
