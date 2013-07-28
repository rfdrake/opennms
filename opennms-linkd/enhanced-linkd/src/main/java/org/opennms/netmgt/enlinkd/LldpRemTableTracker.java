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

package org.opennms.netmgt.enlinkd;


import org.opennms.netmgt.model.topology.TopologyElement;
import org.opennms.netmgt.model.topology.LldpElementIdentifier;
import org.opennms.netmgt.model.topology.LldpEndPoint;
import org.opennms.netmgt.model.topology.LldpLink;
import org.opennms.netmgt.model.topology.NodeElementIdentifier;
import org.opennms.netmgt.snmp.RowCallback;
import org.opennms.netmgt.snmp.SnmpInstId;
import org.opennms.netmgt.snmp.SnmpObjId;
import org.opennms.netmgt.snmp.SnmpRowResult;
import org.opennms.netmgt.snmp.SnmpValue;
import org.opennms.netmgt.snmp.TableTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LldpRemTableTracker extends TableTracker {
private final static Logger LOG = LoggerFactory.getLogger(LldpRemTableTracker.class);
	
    public static final SnmpObjId LLDP_REM_TABLE_ENTRY = SnmpObjId.get(".1.0.8802.1.1.2.1.4.1.1"); // start of table (GETNEXT)
    
    
    public final static SnmpObjId LLDP_REM_CHASSIS_ID_SUBTYPE = SnmpObjId.get(LLDP_REM_TABLE_ENTRY,"4");
    public final static SnmpObjId LLDP_REM_CHASSIS_ID         = SnmpObjId.get(LLDP_REM_TABLE_ENTRY,"5");
    public final static SnmpObjId LLDP_REM_PORT_ID_SUBTYPE    = SnmpObjId.get(LLDP_REM_TABLE_ENTRY,"6");
    public final static SnmpObjId LLDP_REM_PORT_ID            = SnmpObjId.get(LLDP_REM_TABLE_ENTRY,"7");
    public final static SnmpObjId LLDP_REM_PORT_DESCR         = SnmpObjId.get(LLDP_REM_TABLE_ENTRY,"8");
    public final static SnmpObjId LLDP_REM_SYSNAME            = SnmpObjId.get(LLDP_REM_TABLE_ENTRY,"9");

    public static final SnmpObjId[] s_lldpremtable_elemList = new SnmpObjId[] {
        
        /**
         *  "The type of encoding used to identify the chassis associated
         *  with the remote system."
         */
        LLDP_REM_CHASSIS_ID_SUBTYPE,
        
        /**
         * "The string value used to identify the chassis component
         * associated with the remote system."
         */
       LLDP_REM_CHASSIS_ID,

        /**
         * "The type of port identifier encoding used in the associated
         * 'lldpRemPortId' object."
         */
        LLDP_REM_PORT_ID_SUBTYPE,

        /**
         * "The string value used to identify the port component
            associated with the remote system."
         */
        LLDP_REM_PORT_ID,
        
        /**
         * 	"The string value used to identify the description of 
         *  the given port associated with the remote system."
         */
        LLDP_REM_PORT_DESCR,

        /**
         * "The string value used to identify the port component
         * associated with the remote system."
         */
        LLDP_REM_SYSNAME

    };
    

    class LldpRemRow extends SnmpRowResult {

		public LldpRemRow(int columnCount, SnmpInstId instance) {
			super(columnCount, instance);
            LOG.debug( "column count = {}, instance = {}", columnCount, instance);
		}
    	
	    public Integer getLldpRemLocalPortNum() {
	    	return getInstance().getSubIdAt(1);
	    }
	    
	    public Integer getLldpRemChassisidSubtype() {
	    	return getValue(LLDP_REM_CHASSIS_ID_SUBTYPE).toInt();
	    }
	    
	    public SnmpValue getLldpRemChassisId() {
	    	return getValue(LLDP_REM_CHASSIS_ID);
	    }
	    
	    public Integer getLldpRemPortidSubtype() {
	    	return getValue(LLDP_REM_PORT_ID_SUBTYPE).toInt();
	    }

	    public SnmpValue getLldpRemPortid() {
	    	return getValue(LLDP_REM_PORT_ID);
	    }
	    
	    public String getLldpRemPortDescr() {
	    	return getValue(LLDP_REM_PORT_DESCR).toDisplayString();
	    }

	    public String getLldpRemSysname() {
	        return getValue(LLDP_REM_SYSNAME).toDisplayString();
	    }
	    
	    public LldpElementIdentifier getRemElementIdentifier(Integer sourceNode) {
	    	return LldpHelper.getElementIdentifier(getLldpRemChassisId(), getLldpRemSysname(), getLldpRemChassisidSubtype(),sourceNode);
	    }
	    
	    public LldpEndPoint getRemEndPoint(Integer sourceNode) {
	    	return LldpHelper.getEndPoint(getLldpRemPortidSubtype(),getLldpRemPortid(),sourceNode );
	    }
	    
	    public LldpLink getLink(LldpElementIdentifier lldpIdentifier, NodeElementIdentifier nodeIdentifier, LldpLocPortGetter lldpLocPort) {
            TopologyElement deviceA = new TopologyElement();
            deviceA.addElementIdentifier(nodeIdentifier);
            deviceA.addElementIdentifier(lldpIdentifier);
            LOG.info( "processLldpRemRow: row count: {}", getColumnCount());
            LOG.info( "processLldpRemRow: row local port num: {}",  getLldpRemLocalPortNum());

            LldpEndPoint endPointA = lldpLocPort.get(getLldpRemLocalPortNum(), nodeIdentifier.getNodeid());
            deviceA.addEndPoint(endPointA);
            LOG.info( "processLldpRemRow: row local port id: {}", endPointA.getLldpPortId());
            LOG.info( "processLldpRemRow: row local port subtype: {}", endPointA.getLldpPortIdSubType());
    		
    		TopologyElement deviceB = new TopologyElement();
            LldpElementIdentifier lldpRemElementIdentifier = getRemElementIdentifier(nodeIdentifier.getNodeid());
            LOG.info( "processLldpRemRow: row rem lldp identifier: {}", lldpRemElementIdentifier);
            deviceB.addElementIdentifier(lldpRemElementIdentifier);
    		
    		LldpEndPoint endPointB = getRemEndPoint(nodeIdentifier.getNodeid());
    		endPointB.setIfDescr(getLldpRemPortDescr());
    		deviceB.addEndPoint(endPointB);
            LOG.info( "processLldpRemRow: row rem port id: {}", endPointB.getLldpPortId());
            LOG.info( "processLldpRemRow: row rem port subtype: {}", endPointB.getLldpPortIdSubType());
    		
    		return new LldpLink(endPointA, endPointB,nodeIdentifier.getNodeid());
	    }
    }

    public LldpRemTableTracker() {
        super(s_lldpremtable_elemList);
    }
    
    /**
     * <p>Constructor for LldpRemTableTracker.</p>
     *
     * @param rowProcessor a {@link org.opennms.netmgt.snmp.RowCallback} object.
     */
    public LldpRemTableTracker(final RowCallback rowProcessor) {
        super(rowProcessor, s_lldpremtable_elemList);
    }
    
    /** {@inheritDoc} */
    @Override
    public SnmpRowResult createRowResult(final int columnCount, final SnmpInstId instance) {
        return new LldpRemRow(columnCount, instance);
    }

    /** {@inheritDoc} */
    @Override
    public void rowCompleted(final SnmpRowResult row) {
        processLldpRemRow((LldpRemRow)row);
    }

    /**
     * <p>processLldpRemRow</p>
     *
     * @param row a {@link org.opennms.netmgt.enlinkd.LldpRemTableTracker.LldpRemRow} object.
     */
    public void processLldpRemRow(final LldpRemRow row) {
    }

}