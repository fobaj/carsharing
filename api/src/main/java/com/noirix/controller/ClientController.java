package com.noirix.controller;

import com.noirix.controller.request.*;
import com.noirix.domain.Client;
import com.noirix.domain.Repairing;
import com.noirix.repository.ClientRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/clients")
@RequiredArgsConstructor
public class ClientController {

    public final ClientRepository clientRepository;


    @ApiOperation(value = "Finding all clients")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading clients"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<Client>> findAllClients() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading client"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Client database id", example = "7", required = true, dataType =
                    "long"
                    , paramType = "path")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client findClientById(@PathVariable Long id) {
        return clientRepository.findById(id);
    }


    @ApiOperation(value = "Create client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation client"),
            @ApiResponse(code = 422, message = "Failed client creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client savingClient(@RequestBody ClientCreateRequest clientCreateRequest,
                               RepairingCreateRequest repairingCreateRequest) {

        Client client = new Client();
        client.setName(clientCreateRequest.getName());
        client.setSurname(clientCreateRequest.getSurname());
        client.setGender(clientCreateRequest.getGender());
        client.setBirthDate(clientCreateRequest.getBirthDate());
        client.setCountry(clientCreateRequest.getCountry());
        client.setPassport(clientCreateRequest.getPassport());
        client.setTelephone(clientCreateRequest.getTelephone());
        client.setEMail(clientCreateRequest.getEMail());
        client.setLogin(clientCreateRequest.getLogin());
        client.setPassword(clientCreateRequest.getPassword());
        client.setCreated(new Timestamp(System.currentTimeMillis()));
        client.setChanged(new Timestamp(System.currentTimeMillis()));
        client.setIsDeleted(clientCreateRequest.getIsDeleted());

        Repairing repairing = new Repairing();
        repairing.setDamage(repairingCreateRequest.getDamage());
        repairing.setCost(repairingCreateRequest.getCost());

        client.setRepairings(Collections.singleton(new Repairing(client, repairing.getDamage(), repairing.getCost())));

        return clientRepository.save(client);
    }


    @ApiOperation(value = "Update client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful updating client"),
            @ApiResponse(code = 422, message = "Failed client updating properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@PathVariable Long id, @RequestBody ClientCreateRequest clientCreateRequest) {

        Client client = clientRepository.findById(id);

        client.setName(clientCreateRequest.getName());
        client.setSurname(clientCreateRequest.getSurname());
        client.setGender(clientCreateRequest.getGender());
        client.setBirthDate(clientCreateRequest.getBirthDate());
        client.setCountry(clientCreateRequest.getCountry());
        client.setPassport(clientCreateRequest.getPassport());
        client.setTelephone(clientCreateRequest.getTelephone());
        client.setEMail(clientCreateRequest.getEMail());
        client.setLogin(clientCreateRequest.getLogin());
        client.setPassword(clientCreateRequest.getPassword());
        client.setChanged(new Timestamp(System.currentTimeMillis()));
        client.setIsDeleted(clientCreateRequest.getIsDeleted());
        client.setRepairings(client.getRepairings());

        return clientRepository.update(client);
    }


    @ApiOperation(value = "Update2 client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful updating client"),
            @ApiResponse(code = 422, message = "Failed client updating properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Client update2Client(@RequestBody ClientChangeRequest clientChangeRequest) {

        Client client = clientRepository.findById(clientChangeRequest.getId());

        client.setName(clientChangeRequest.getName());
        client.setSurname(clientChangeRequest.getSurname());
        client.setGender(clientChangeRequest.getGender());
        client.setBirthDate(clientChangeRequest.getBirthDate());
        client.setCountry(clientChangeRequest.getCountry());
        client.setPassport(clientChangeRequest.getPassport());
        client.setTelephone(clientChangeRequest.getTelephone());
        client.setEMail(clientChangeRequest.getEMail());
        client.setLogin(clientChangeRequest.getLogin());
        client.setPassword(clientChangeRequest.getPassword());
        client.setChanged(new Timestamp(System.currentTimeMillis()));
        client.setIsDeleted(clientChangeRequest.getIsDeleted());
        client.setRepairings(client.getRepairings());

        return clientRepository.update(client);
    }


    @ApiOperation(value = "Deleting client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleting client"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Client database id", example = "7", required = true, dataType =
                    "long"
                    , paramType = "path")
    })
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteClient(@PathVariable Long id) {

        Client client = clientRepository.findById(id);

        return clientRepository.delete(client);

    }
}
