package com.rzodkiewicz.michal.controller

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.dto.DropdownDto
import com.rzodkiewicz.michal.dto.Result
import com.rzodkiewicz.michal.util.enums.Sector
import com.rzodkiewicz.michal.service.EmissionService
import com.rzodkiewicz.michal.dto.EmissionFilterRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = '/emission')
@Api
class EmissionController {

    private final EmissionService emissionService

    @Autowired
    EmissionController(EmissionService emissionService){
        this.emissionService = emissionService
    }

    @RequestMapping(value = '/sectors', method = RequestMethod.GET)
    ResponseEntity getSectors(){
        try{
            Set<Sector> sectors = emissionService.getSectors()
            Set<Result> results= []
            sectors.each{ it->
                results.add(new Result(name: it, value: it, text: it))
            }
            ResponseEntity.ok(results)
        }catch(Exception e){
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace())
        }
    }

    @RequestMapping(value = '/countries', method = RequestMethod.GET)
    ResponseEntity getCountries(){
        try{
            Set<String> countries = emissionService.getCountries()
            Set<Result> results = []
            countries.each {it->
                results.add(new Result(name: it, value: it, text: it))
            }

            ResponseEntity.ok(results)
        }catch(Exception e){
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace())
        }
    }

    @RequestMapping(value = '/years', method = RequestMethod.GET)
    ResponseEntity getYears(){
        try{
            Set<Integer> years = emissionService.getYears();
            Set<Result> results = [];
            years.each { it ->
                results.add(new Result(name: it, value: it, text: it));
            }
            ResponseEntity.ok(results);
        }catch (Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace())
        }
    }

    @ApiOperation(value = 'Returns emission data based on filter in request body')
    @RequestMapping(value = '/emissions', consumes = 'application/json', method = RequestMethod.POST)
    ResponseEntity getEmissions(@ApiParam @RequestBody EmissionFilterRequest request){
        try{
            Set<Emission> emissions = emissionService.fetchFilteredEmission(request)
            ResponseEntity.ok(emissions)
        }catch(Exception e){
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace())
        }
    }
}
