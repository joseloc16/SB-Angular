package io.getarrays.server.service.impl;

import io.getarrays.server.model.Server;
import io.getarrays.server.repository.IServerRepo;
import io.getarrays.server.service.IServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements IServerService {

  private final IServerRepo repo;

  @Override
  public Server createServer(Server server) {
    log.info("Saving new server: {}", server.getName());
    return null;
  }

  @Override
  public Server ping(String ipAddress) {
    return null;
  }

  @Override
  public Collection<Server> listServers(int limit) {
    return null;
  }

  @Override
  public Server get(Long id) {
    return null;
  }

  @Override
  public Server updateServer(Server server) {
    return null;
  }

  @Override
  public Boolean delete(Long id) {
    return null;
  }
}
