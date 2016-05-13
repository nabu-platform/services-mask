package be.nabu.libs.services.mask;

import java.util.Set;

import be.nabu.libs.services.api.Service;
import be.nabu.libs.services.api.ServiceInstance;
import be.nabu.libs.services.api.ServiceInterface;

public class MaskedService implements Service {

	private Service service;
	private ServiceInterface iface;

	public MaskedService(Service service, ServiceInterface iface) {
		this.service = service;
		this.iface = iface;
	}
	
	@Override
	public ServiceInterface getServiceInterface() {
		return iface;
	}

	@Override
	public ServiceInstance newInstance() {
		return new MaskedServiceInstance(this, service.newInstance());
	}

	@Override
	public Set<String> getReferences() {
		return null;
	}

	Service getMaskedService() {
		return service;
	}
	
}
