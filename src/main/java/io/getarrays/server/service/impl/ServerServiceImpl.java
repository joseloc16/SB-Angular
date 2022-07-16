package io.getarrays.server.service.impl;

import io.getarrays.server.model.Server;
import io.getarrays.server.repository.IServerRepo;
import io.getarrays.server.service.IServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static io.getarrays.server.enumeration.Status.SERVER_DOWN;
import static io.getarrays.server.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements IServerService {

  private final IServerRepo repo;

  @Override
  public Server create(Server server) {
    log.info("Saving new server: {}", server.getName());
    server.setImageUrl(setServerImageUrl());
    return repo.save(server);
  }

  @Override
  public Server ping(String ipAddress) throws IOException {
    log.info("Pinging server IP: {}", ipAddress);
    Server server = repo.findByIpAddress(ipAddress);
    InetAddress address = InetAddress.getByName(ipAddress);
    server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
    repo.save(server);
    return server;
  }

  @Override
  public Collection<Server> list(int limit) {
    log.info("Fetching all server");
    return repo.findAll(of(0, limit)).toList();
    //page (número de página, limit (los n primeros
  }

  @Override
  public Server get(Long id) {
    log.info("Fetching server by id: {}", id);
    return repo.findById(id).get();
  }

  @Override
  public Server update(Server server) {
    log.info("Updating server: {}", server.getName());
    return repo.save(server);
  }

  @Override
  public Boolean delete(Long id) {
    log.info("Deleting server by ID: {}", id);
    repo.deleteById(id);
    return TRUE;
  }

  private String setServerImageUrl() {
    String[] imagesName =
        {"server1.png", "server2.png", "server3.png", "server4.png"};
    return ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/server/image/" + imagesName[new Random().nextInt(4)])
        .toUriString();
  }
}
