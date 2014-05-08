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
import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/upload")
public class ImportRestService {
	@Inject
	private ImportData importData;
	
	@POST
	@Path("/import")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response findById(
			@FormDataParam("Filedata") InputStream fileInputStream) throws ClassNotFoundException, IOException, SQLException {
		importData.importData(IOUtils.toByteArray(fileInputStream));
		fileInputStream.close();
		System.out.println();
		return Response.status(Status.OK).build();
	}
}
