package io.getarrays.server.service;

import io.getarrays.server.model.Server;

import java.util.Collection;

public interface IServerService {
  Server createServer(Server server);

  Server ping(String ipAddress);

  Collection<Server> listServers(int limit);

  Server get(Long id);

  Server updateServer(Server server);

  Boolean delete(Long id);

}
