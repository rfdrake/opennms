/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2012 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2012 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.features.topology.app.internal.operations;

import java.util.List;

import org.opennms.features.topology.api.Constants;
import org.opennms.features.topology.api.GraphContainer;
import org.opennms.features.topology.api.Operation;
import org.opennms.features.topology.api.OperationContext;
import org.opennms.features.topology.api.support.VertexHopGraphProvider.VertexHopCriteria;
import org.opennms.features.topology.api.topo.Criteria;
import org.opennms.features.topology.api.topo.VertexRef;

public class AddFocusVerticesOperation implements Constants, Operation {

	@Override
	public Undoer execute(List<VertexRef> targets, final OperationContext operationContext) {
		if (targets == null || targets.isEmpty()) return null;

		final GraphContainer graphContainer = operationContext.getGraphContainer();

		boolean foundExistingCriteria = false;
		for (Criteria criteria : graphContainer.getCriteria()) {
			try {
				VertexHopCriteria hopCriteria = (VertexHopCriteria)criteria;
				for (VertexRef target : targets) {
					hopCriteria.add(target);
				}
			} catch (ClassCastException e) {}
		}

		// If there isn't a currently existing VertexHopCriteria, add one
		if (foundExistingCriteria == false) {
			VertexHopCriteria hopCriteria = new VertexHopCriteria(targets);
			graphContainer.setCriteria(hopCriteria);
		}

		graphContainer.redoLayout();
		return null;
	}

	@Override
	public boolean display(List<VertexRef> targets, OperationContext operationContext) {
		return true;
	}

	@Override
	public boolean enabled(List<VertexRef> targets, OperationContext operationContext) {
		return targets != null && targets.size() > 0;
	}

	@Override
	public String getId() {
		return "AddFocusVertices";
	}
}
