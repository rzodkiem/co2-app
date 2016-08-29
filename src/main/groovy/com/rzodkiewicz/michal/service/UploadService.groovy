package com.rzodkiewicz.michal.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface UploadService {

    void uploadUnfccDataset(MultipartFile file) throws IOException
}