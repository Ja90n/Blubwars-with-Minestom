package com.Ja90n.instances;

import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.network.socket.Server;

import java.lang.reflect.Proxy;
import java.security.Permission;
import java.util.List;

public class Config {

    private String serverHost;
    private int port;
    private boolean velocity;
    private String forwardingSecret;

    public void setDefault() {
        serverHost = "0.0.0.0";
        port = 25566;
        velocity = false;
        forwardingSecret = "";
    }

    public int getPort() {
        return port;
    }

    public String getForwardingSecret() {
        return forwardingSecret;
    }

    public String getServerHost() {
        return serverHost;
    }

    public boolean getVelocity() {
        return velocity;
    }
}
