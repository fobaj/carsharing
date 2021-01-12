package com.noirix.controller;

import com.noirix.controller.request.*;
import com.noirix.domain.Renting;
import com.noirix.domain.Transfer;
import com.noirix.repository.RentingRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/rentings")
@RequiredArgsConstructor
public class RentingController {

    public final RentingRepository rentingRepository;

    @ApiOperation(value = "Finding all rentings")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading rentings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<Renting>> findAllCars() {
        return new ResponseEntity<>(rentingRepository.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding renting by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading renting"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Renting database id", example = "7", required = true, dataType =
                    "long"
                    , paramType = "path")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Renting findRentingById(@PathVariable Long id) {
        return rentingRepository.findById(id);
    }


    @ApiOperation(value = "Create renting")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation renting"),
            @ApiResponse(code = 422, message = "Failed renting creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Renting savingRenting(@RequestBody RentingCreateRequest rentingCreateRequest,
                                 TransferCreateRequest transferCreateRequest) {

        Renting renting = new Renting();
        renting.setCost(rentingCreateRequest.getCost());
        renting.setCreated(new Timestamp(System.currentTimeMillis()));
        renting.setChanged(new Timestamp(System.currentTimeMillis()));
        renting.setIsDeleted(rentingCreateRequest.getIsDeleted());

        Transfer transfer = new Transfer();
        transfer.setStartPlace(transferCreateRequest.getStartPlace());
        transfer.setEndPlace(transferCreateRequest.getEndPlace());
        transfer.setStartOdometerReading(transferCreateRequest.getStartOdometerReading());
        transfer.setEndOdometerReading(transferCreateRequest.getEndOdometerReading());
        transfer.setStartAmountOfFuel(transferCreateRequest.getStartAmountOfFuel());
        transfer.setEndAmountOfFuel(transferCreateRequest.getEndAmountOfFuel());

        renting.setTransfer(new Transfer(renting, transfer.getStartPlace(), transfer.getEndPlace(),
                transfer.getStartOdometerReading(), transfer.getEndOdometerReading(), transfer.getStartAmountOfFuel()
                , transfer.getEndAmountOfFuel()));

        return rentingRepository.save(renting);
    }


    @ApiOperation(value = "Update renting")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful updating renting"),
            @ApiResponse(code = 422, message = "Failed renting updating properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Renting updateRenting(@PathVariable Long id, @RequestBody RentingCreateRequest rentingCreateRequest) {

        Renting renting = rentingRepository.findById(id);

        renting.setCost(rentingCreateRequest.getCost());
        renting.setChanged(new Timestamp(System.currentTimeMillis()));
        renting.setIsDeleted(rentingCreateRequest.getIsDeleted());
        renting.setTransfer(rentingCreateRequest.getTransfer());

        return rentingRepository.update(renting);
    }


    @ApiOperation(value = "Update2 renting")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful updating renting"),
            @ApiResponse(code = 422, message = "Failed renting updating properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Renting update2Renting(@RequestBody RentingChangeRequest rentingChangeRequest) {

        Renting renting = rentingRepository.findById(rentingChangeRequest.getId());

        renting.setCost(rentingChangeRequest.getCost());
        renting.setChanged(new Timestamp(System.currentTimeMillis()));
        renting.setIsDeleted(rentingChangeRequest.getIsDeleted());
        renting.setTransfer(rentingChangeRequest.getTransfer());

        return rentingRepository.update(renting);
    }


    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteRenting(@PathVariable Long id) {

        Renting renting = rentingRepository.findById(id);

        return rentingRepository.delete(renting);

    }
}
