package be.nabu.libs.services.mask;

import be.nabu.libs.services.api.ExecutionContext;
import be.nabu.libs.services.api.Service;
import be.nabu.libs.services.api.ServiceException;
import be.nabu.libs.services.api.ServiceInstance;
import be.nabu.libs.types.api.ComplexContent;
import be.nabu.libs.types.mask.MaskedContent;

public class MaskedServiceInstance implements ServiceInstance {

	private MaskedService service;
	private ServiceInstance instance;

	public MaskedServiceInstance(MaskedService service, ServiceInstance instance) {
		this.service = service;
		this.instance = instance;
	}
	
	@Override
	public Service getDefinition() {
		return service;
	}

	@Override
	public ComplexContent execute(ExecutionContext executionContext, ComplexContent input) throws ServiceException {
		if (input != null) {
			input = new MaskedContent(input, service.getMaskedService().getServiceInterface().getInputDefinition());
		}
		ComplexContent output = instance.execute(executionContext, input);
		return output == null ? null : new MaskedContent(output, service.getServiceInterface().getOutputDefinition());
	}

}
