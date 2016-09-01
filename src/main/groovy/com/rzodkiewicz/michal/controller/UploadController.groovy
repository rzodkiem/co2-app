package com.rzodkiewicz.michal.controller

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.repository.EmissionRepository
import com.rzodkiewicz.michal.service.UploadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/upload")
class UploadController {
    private final UploadService uploadService

    @Autowired
    UploadController(UploadService uploadService){
        this.uploadService = uploadService
    }

    @RequestMapping(value = "/unfccc", method = RequestMethod.POST)
    ResponseEntity uploadUnfcccData(@RequestParam("file") MultipartFile file){
        try{
            ResponseEntity.ok(uploadService.uploadUnfccDataset(file))
        }catch (IOException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
