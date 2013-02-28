/**
 * 
 */
package org.opennms.features.topology.app.internal.support;

import org.opennms.netmgt.model.OnmsIpInterface;

import com.vaadin.data.Property;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

public class OnmsIpInterfaceGenerator implements ColumnGenerator {

	private static final long serialVersionUID = 7806832669018164281L;

	@Override
	public Object generateCell(Table source, Object itemId, Object columnId) {
		Property property = source.getContainerProperty(itemId, columnId);
		if (property == null || property.getValue() == null) {
			return null;
		} else {
			return ((OnmsIpInterface)property.getValue()).getIpAddressAsString();
		}
	}
}