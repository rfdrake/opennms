/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2011-2014 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2014 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.17 at 12:58:43 PM EDT 
//


package org.opennms.xmlns.xsd.event;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dbid" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dist-poller" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creation-time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="master-station" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}mask" minOccurs="0"/>
 *         &lt;element name="uei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nodeid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="host" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="interface" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmphost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}snmp" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}parms" minOccurs="0"/>
 *         &lt;element name="descr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}logmsg" minOccurs="0"/>
 *         &lt;element name="severity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pathoutage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}correlation" minOccurs="0"/>
 *         &lt;element name="operinstruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}autoaction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}operaction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}autoacknowledge" minOccurs="0"/>
 *         &lt;element name="loggroup" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}tticket" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}forward" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}script" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ifIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ifAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mouseovertext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/event}alarm-data" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dbid",
    "distPoller",
    "creationTime",
    "masterStation",
    "mask",
    "uei",
    "source",
    "nodeid",
    "time",
    "host",
    "_interface",
    "snmphost",
    "service",
    "snmp",
    "parms",
    "descr",
    "logmsg",
    "severity",
    "pathoutage",
    "correlation",
    "operinstruct",
    "autoaction",
    "operaction",
    "autoacknowledge",
    "loggroup",
    "tticket",
    "forward",
    "script",
    "ifIndex",
    "ifAlias",
    "mouseovertext",
    "alarmData"
})
@XmlRootElement(name = "event")
public class Event {

    protected Integer dbid;
    @XmlElement(name = "dist-poller")
    protected String distPoller;
    @XmlElement(name = "creation-time")
    protected String creationTime;
    @XmlElement(name = "master-station")
    protected String masterStation;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected Mask mask;
    protected String uei;
    @XmlElement(required = true)
    protected String source;
    protected Long nodeid;
    @XmlElement(required = true)
    protected String time;
    protected String host;
    @XmlElement(name = "interface")
    protected String _interface;
    protected String snmphost;
    protected String service;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected Snmp snmp;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected Parms parms;
    protected String descr;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected Logmsg logmsg;
    protected String severity;
    protected String pathoutage;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected Correlation correlation;
    protected String operinstruct;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected List<Autoaction> autoaction;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected List<Operaction> operaction;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected Autoacknowledge autoacknowledge;
    protected List<String> loggroup;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected Tticket tticket;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected List<Forward> forward;
    @XmlElement(namespace = "http://xmlns.opennms.org/xsd/event")
    protected List<Script> script;
    protected Integer ifIndex;
    protected String ifAlias;
    protected String mouseovertext;
    @XmlElement(name = "alarm-data", namespace = "http://xmlns.opennms.org/xsd/event")
    protected AlarmData alarmData;
    @XmlAttribute(name = "uuid")
    protected String uuid;

    /**
     * Gets the value of the dbid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDbid() {
        return dbid;
    }

    /**
     * Sets the value of the dbid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDbid(Integer value) {
        this.dbid = value;
    }

    /**
     * Gets the value of the distPoller property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistPoller() {
        return distPoller;
    }

    /**
     * Sets the value of the distPoller property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistPoller(String value) {
        this.distPoller = value;
    }

    /**
     * Gets the value of the creationTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the value of the creationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationTime(String value) {
        this.creationTime = value;
    }

    /**
     * Gets the value of the masterStation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterStation() {
        return masterStation;
    }

    /**
     * Sets the value of the masterStation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterStation(String value) {
        this.masterStation = value;
    }

    /**
     * The event mask which helps to uniquely identify an
     *             event
     * 
     * @return
     *     possible object is
     *     {@link Mask }
     *     
     */
    public Mask getMask() {
        return mask;
    }

    /**
     * Sets the value of the mask property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mask }
     *     
     */
    public void setMask(Mask value) {
        this.mask = value;
    }

    /**
     * Gets the value of the uei property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUei() {
        return uei;
    }

    /**
     * Sets the value of the uei property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUei(String value) {
        this.uei = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the nodeid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNodeid() {
        return nodeid;
    }

    /**
     * Sets the value of the nodeid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNodeid(Long value) {
        this.nodeid = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the host property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Gets the value of the interface property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterface() {
        return _interface;
    }

    /**
     * Sets the value of the interface property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterface(String value) {
        this._interface = value;
    }

    /**
     * Gets the value of the snmphost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmphost() {
        return snmphost;
    }

    /**
     * Sets the value of the snmphost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmphost(String value) {
        this.snmphost = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

    /**
     * The snmp information from the trap
     * 
     * @return
     *     possible object is
     *     {@link Snmp }
     *     
     */
    public Snmp getSnmp() {
        return snmp;
    }

    /**
     * Sets the value of the snmp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Snmp }
     *     
     */
    public void setSnmp(Snmp value) {
        this.snmp = value;
    }

    /**
     * The varbinds from the trap
     * 
     * @return
     *     possible object is
     *     {@link Parms }
     *     
     */
    public Parms getParms() {
        return parms;
    }

    /**
     * Sets the value of the parms property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parms }
     *     
     */
    public void setParms(Parms value) {
        this.parms = value;
    }

    /**
     * Gets the value of the descr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescr() {
        return descr;
    }

    /**
     * Sets the value of the descr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescr(String value) {
        this.descr = value;
    }

    /**
     * The event logmsg
     * 
     * @return
     *     possible object is
     *     {@link Logmsg }
     *     
     */
    public Logmsg getLogmsg() {
        return logmsg;
    }

    /**
     * Sets the value of the logmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link Logmsg }
     *     
     */
    public void setLogmsg(Logmsg value) {
        this.logmsg = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverity(String value) {
        this.severity = value;
    }

    /**
     * Gets the value of the pathoutage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathoutage() {
        return pathoutage;
    }

    /**
     * Sets the value of the pathoutage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathoutage(String value) {
        this.pathoutage = value;
    }

    /**
     * The event correlation information
     * 
     * @return
     *     possible object is
     *     {@link Correlation }
     *     
     */
    public Correlation getCorrelation() {
        return correlation;
    }

    /**
     * Sets the value of the correlation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Correlation }
     *     
     */
    public void setCorrelation(Correlation value) {
        this.correlation = value;
    }

    /**
     * Gets the value of the operinstruct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperinstruct() {
        return operinstruct;
    }

    /**
     * Sets the value of the operinstruct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperinstruct(String value) {
        this.operinstruct = value;
    }

    /**
     * The automatic action to occur when this event
     *             occurs Gets the value of the autoaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the autoaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutoaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Autoaction }
     * 
     * 
     */
    public List<Autoaction> getAutoaction() {
        if (autoaction == null) {
            autoaction = new ArrayList<Autoaction>();
        }
        return this.autoaction;
    }

    /**
     * The operator action to be taken when this event
     *             occurs Gets the value of the operaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operaction }
     * 
     * 
     */
    public List<Operaction> getOperaction() {
        if (operaction == null) {
            operaction = new ArrayList<Operaction>();
        }
        return this.operaction;
    }

    /**
     * The autoacknowledge information for the
     *             user
     * 
     * @return
     *     possible object is
     *     {@link Autoacknowledge }
     *     
     */
    public Autoacknowledge getAutoacknowledge() {
        return autoacknowledge;
    }

    /**
     * Sets the value of the autoacknowledge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Autoacknowledge }
     *     
     */
    public void setAutoacknowledge(Autoacknowledge value) {
        this.autoacknowledge = value;
    }

    /**
     * Gets the value of the loggroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loggroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoggroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLoggroup() {
        if (loggroup == null) {
            loggroup = new ArrayList<String>();
        }
        return this.loggroup;
    }

    /**
     * The trouble ticket info
     * 
     * @return
     *     possible object is
     *     {@link Tticket }
     *     
     */
    public Tticket getTticket() {
        return tticket;
    }

    /**
     * Sets the value of the tticket property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tticket }
     *     
     */
    public void setTticket(Tticket value) {
        this.tticket = value;
    }

    /**
     * The forwarding information for this
     *             event Gets the value of the forward property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the forward property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForward().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Forward }
     * 
     * 
     */
    public List<Forward> getForward() {
        if (forward == null) {
            forward = new ArrayList<Forward>();
        }
        return this.forward;
    }

    /**
     * The script information for this
     *             event Gets the value of the script property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the script property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScript().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Script }
     * 
     * 
     */
    public List<Script> getScript() {
        if (script == null) {
            script = new ArrayList<Script>();
        }
        return this.script;
    }

    /**
     * Gets the value of the ifIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIfIndex() {
        return ifIndex;
    }

    /**
     * Sets the value of the ifIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIfIndex(Integer value) {
        this.ifIndex = value;
    }

    /**
     * Gets the value of the ifAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfAlias() {
        return ifAlias;
    }

    /**
     * Sets the value of the ifAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfAlias(String value) {
        this.ifAlias = value;
    }

    /**
     * Gets the value of the mouseovertext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMouseovertext() {
        return mouseovertext;
    }

    /**
     * Sets the value of the mouseovertext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMouseovertext(String value) {
        this.mouseovertext = value;
    }

    /**
     * Data used to create an event.
     * 
     * @return
     *     possible object is
     *     {@link AlarmData }
     *     
     */
    public AlarmData getAlarmData() {
        return alarmData;
    }

    /**
     * Sets the value of the alarmData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlarmData }
     *     
     */
    public void setAlarmData(AlarmData value) {
        this.alarmData = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
