package gov.cms.qpp.conversion.api.controllers.v1;

import gov.cms.qpp.conversion.api.services.QrdaService;
import gov.cms.qpp.conversion.api.services.ValidationService;
import gov.cms.qpp.conversion.encode.JsonWrapper;
import gov.cms.qpp.conversion.util.NamedInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller to handle uploading files for QRDA-III Conversion
 */
@RestController
@RequestMapping("/v1/qrda3")
@CrossOrigin
public class QrdaControllerV1 {
	@Autowired
	private QrdaService qrdaService;

	@Autowired
	private ValidationService validationService;

	/**
	 * Endpoint to transform an uploaded file into a valid or error json response
	 *
	 * @param file Uploaded file
	 * @return Valid json or error json content
	 * @throws IOException If errors occur during file upload or conversion
	 */
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadQrdaFile(@RequestParam MultipartFile file) throws IOException {
		JsonWrapper qpp = qrdaService.convertQrda3ToQpp(new NamedInputStream(file.getInputStream(), file.getName()));

		validationService.validateQpp(qpp);

		return qpp.toString();
	}
}