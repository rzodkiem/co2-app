package com.rzodkiewicz.michal.service

import com.rzodkiewicz.michal.domain.Emission
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface UploadService {

    List<Emission> uploadUnfccDataset(MultipartFile file) throws IOException
}