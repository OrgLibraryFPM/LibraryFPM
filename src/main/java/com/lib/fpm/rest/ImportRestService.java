package com.lib.fpm.rest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.lib.fpm.importdata.ImportData;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/upload")
public class ImportRestService {
	@Inject
	private ImportData importData;
	
	@POST
	@Path("/import")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response importData(
			@FormDataParam("Filedata") InputStream fileInputStream,
			@FormDataParam("Filedata") FormDataContentDisposition contentDispositionHeader) throws ClassNotFoundException, IOException, SQLException {
		Integer index = contentDispositionHeader.getFileName().indexOf(".accdb");
		if (index<0){
			return Response.status(Status.BAD_REQUEST).build();
		}
		try{
			importData.importData(IOUtils.toByteArray(fileInputStream));
		} catch (IllegalArgumentException e){
			return Response.status(Status.CONFLICT).build();
		} finally{
			fileInputStream.close();
		}
		return Response.ok(contentDispositionHeader, MediaType.APPLICATION_JSON).build();
	}
}
