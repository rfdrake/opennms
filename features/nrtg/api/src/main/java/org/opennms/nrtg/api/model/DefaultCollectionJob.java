/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2010-2011 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2011 The OpenNMS Group, Inc.
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

package org.opennms.nrtg.api.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * User: chris
 * Date: 13.06.12
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class DefaultCollectionJob implements CollectionJob {
    private static final long serialVersionUID = -857193182688356245L;

    private static Logger logger = LoggerFactory.getLogger(DefaultCollectionJob.class);

    private String m_id;
    private String m_site;
    private String m_netInterface;
    private String m_service;
    private int m_nodeId;
    private String m_protocolConfiguration;

	private HashMap<Set<String>, Set<String>> m_metricSets = new HashMap<Set<String>, Set<String>>();
    private HashMap<String, String> m_allMetrics = new HashMap<String, String>();
    private HashMap<String, Object> m_parameters = null;
    private Date m_creationTimestamp = new Date();
    private Date m_finishedTimestamp = null;

    @Override
    public String getId() {
        return m_id;
    }

    @Override
    public void setId(String id) {
        this.m_id = id;
    }

    @Override
    public void setNodeId(int nodeId) {
        this.m_nodeId = nodeId;
    }

    @Override
    public int getNodeId() {
        return m_nodeId;
    }

    @Override
    public String getSite() {
        return m_site;
    }

    @Override
    public void setSite(String site) {
        this.m_site = site;
    }

    @Override
    public String getNetInterface() {
        return m_netInterface;
    }

    @Override
    public void setNetInterface(String netInterface) {
        this.m_netInterface = netInterface;
    }

    @Override
    public String getService() {
        return m_service;
    }

    @Override
    public void setService(String service) {
        this.m_service = service;
    }

    @Override
    public String getProtocolConfiguration() {
		return m_protocolConfiguration;
	}

    @Override
	public void setProtocolConfiguration(String protocolConfiguration) {
		m_protocolConfiguration = protocolConfiguration;
	}


    @Override
    public void setParameters(HashMap<String, Object> parameters) {
        this.m_parameters = parameters;
    }

    @Override
    public HashMap<String, Object> getParameters() {
        return m_parameters;
    }

    @Override
    public MeasurementSet getMeasurementSet() {
        LightweightMeasurementSet measurementSet = new LightweightMeasurementSet(getNodeId(), getService(), getNetInterface(), getFinishedTimestamp());

        for (Set<String> destinationSet : m_metricSets.keySet()) {

            Set<String> metricSet = m_metricSets.get(destinationSet);

            for (String metricId : metricSet) {
                measurementSet.addMeasurement(metricId, getMetricValue(metricId));
            }
        }

        return measurementSet;
    }

    @Override
    public void setCreationTimestamp(Date creationTimestamp) {
        m_creationTimestamp = creationTimestamp;
    }

    @Override
    public Date getCreationTimestamp() {
        return m_creationTimestamp;
    }

    @Override
    public Date getFinishedTimestamp() {
        return m_finishedTimestamp;
    }

    @Override
    public void setFinishedTimestamp(Date finishedTimestamp) {
        m_finishedTimestamp = finishedTimestamp;
    }

    @Override
    public void addMetric(String metricId, Set<String> destinationSet) throws IllegalArgumentException {
        if (destinationSet == null) {
            throw new IllegalArgumentException("destinationSet must not be null");
        } else {
            if (destinationSet.isEmpty()) {
                throw new IllegalArgumentException("destinationSet must not be empty");
            }
        }
        if (metricId == null) {
            throw new IllegalArgumentException("metricId must not be null");
        } else {
            if ("".equals(metricId)) {
                throw new IllegalArgumentException("metricId must not be ''");
            }
        }

        // the destination set to use, initialized with the given parameter
        TreeSet<String> destinationSetToUse = new TreeSet<String>(destinationSet);

        // checking whether a already defined destination set conatins the metricId
        for (Set<String> alreadyDefinedDestinationSet : m_metricSets.keySet()) {
            if (m_metricSets.get(alreadyDefinedDestinationSet).contains(metricId)) {

                // if the destination set matches we're done
                if (destinationSetToUse.equals(alreadyDefinedDestinationSet))
                    return;

                // removing metric from already defined destination set
                m_metricSets.get(alreadyDefinedDestinationSet).remove(metricId);

                // modifying the destination set to include the already defined set
                destinationSetToUse.addAll(alreadyDefinedDestinationSet);

                logger.debug("Metric " + metricId + "already exists in " + alreadyDefinedDestinationSet + ", moving metric to " + destinationSetToUse);
            }
        }

        // create map entry if destination set not exists
        if (!m_metricSets.containsKey(destinationSetToUse)) {
            m_metricSets.put(destinationSetToUse, new HashSet<String>());
        }

        // adding the metric to the map
        m_metricSets.get(destinationSetToUse).add(metricId);
        m_allMetrics.put(metricId, null);
    }

    public void addAllMetrics(List<String> metric, Set<String> destinationSet) {
        for (String metricId : metric) {
            addMetric(metricId, destinationSet);
        }
    }

    @Override
    public void setMetricValue(String metricId, String value) throws IllegalArgumentException {
        if (metricId == null) {
            throw new IllegalArgumentException("metricId must not be null");
        } else {
            if ("".equals(metricId)) {
                throw new IllegalArgumentException("metricId must not be ''");
            } else {
                if (!m_allMetrics.containsKey(metricId)) {
                    throw new IllegalArgumentException("metricId is undefined");
                }
            }
        }

        m_allMetrics.put(metricId, value);
    }

    @Override
    public String getMetricValue(String metricId) throws IllegalArgumentException {
        if (metricId == null) {
            throw new IllegalArgumentException("metricId must not be null");
        } else {
            if ("".equals(metricId)) {
                throw new IllegalArgumentException("metricId must not be ''");
            } else {
                if (!m_allMetrics.containsKey(metricId)) {
                    throw new IllegalArgumentException("metricId is undefined");
                }
            }
        }

        return m_allMetrics.get(metricId);
    }

    private String getDestinationString(Set<String> destinationSet) {
        String destinationString = "";
        for (String destination : destinationSet) {
            if (!"".equals(destinationString))
                destinationString += ", ";
            destinationString += destination;
        }

        return destinationString;
    }

    /*
     * Returns a map for each combination of destinations occuring in the collection
     * job with the corresponding measurements. If a metric is associated with more
     * than one destination it occurs only in one measurement set for multiple destinations.
     */
    @Override
    public Map<String, MeasurementSet> getMeasurementSetsByDestination() {
        Map<String, MeasurementSet> measurementMap = new TreeMap<String, MeasurementSet>();

        for (Set<String> destinationSet : m_metricSets.keySet()) {
            LightweightMeasurementSet measurementSet = new LightweightMeasurementSet(getNodeId(), getService(), getNetInterface(), getFinishedTimestamp());

            Set<String> metricSet = m_metricSets.get(destinationSet);

            for (String metricId : metricSet) {
                measurementSet.addMeasurement(metricId, getMetricValue(metricId));
            }

            measurementMap.put(getDestinationString(destinationSet), measurementSet);
        }

        return measurementMap;
    }


    @Override
    public Set<String> getAllMetrics() {
        return m_allMetrics.keySet();
    }

}