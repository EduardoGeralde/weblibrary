package com.eduardoportfolio.weblibrary.infra;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class AmazonFileSaver {
	
	
	public String writer(String baseFolder, MultipartFile multipartFile){
		//The AmazonS3Client type object encapsulate all the logic and the communication protocol
		AmazonS3Client s3= client ();
		try{
			//We have only to pass the parameters, first the remote folder where we will save the
			//file (bucket), second is the file name, third is some InputStream implementation
			//thats really represent the file and fourth some extra informations like, expire file
			//date and any other specific information of the application
			s3.putObject("weblibrary", multipartFile.getOriginalFilename(),
										multipartFile.getInputStream(),
										new ObjectMetadata());
			return "https://s3.amazonaws.com/weblibrary/" + multipartFile.getOriginalFilename();
		} catch (AmazonClientException | IOException e){
			throw new RuntimeException (e);
		}
	}
	
	//In any Amazon integration, we always have to create a object that has the access credentials 
	private AmazonS3Client client(){
		AWSCredentials credentials = new BasicAWSCredentials("example","example");
		AmazonS3Client newClient = new AmazonS3Client(credentials,new ClientConfiguration());
		newClient.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		
		return newClient;
	}
}
