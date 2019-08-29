package com.sch.mngt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sch.mngt.dto.StudentDetailsDTO;
import com.sch.mngt.utils.FileUploadHelper;

import lombok.extern.java.Log;

@RestController
@Log
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@PostMapping("/api/upload")
	public ResponseEntity<Object> studentsFileUpload(@RequestParam("file") MultipartFile uploadfile) {
		System.out.println("studentsFileUpload() -start");
		log.info("studentsFileUpload() -start");

		if (uploadfile.isEmpty()) {
			return new ResponseEntity<Object>("please select a file!", HttpStatus.OK);
		}

		List<StudentDetailsDTO> studentDetailsList = new ArrayList<StudentDetailsDTO>();

		fileUploadHelper.parseFile(uploadfile, studentDetailsList);
		log.info("studentsFileUpload() -end");
		return new ResponseEntity<Object>("Successfully uploaded - " + uploadfile.getOriginalFilename(),
				new HttpHeaders(), HttpStatus.OK);

	}

}
