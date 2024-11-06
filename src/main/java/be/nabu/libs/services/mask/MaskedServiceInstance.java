/*
* Copyright (C) 2016 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
