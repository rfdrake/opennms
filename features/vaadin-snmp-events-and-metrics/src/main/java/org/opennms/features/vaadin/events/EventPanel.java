/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2006-2012 The OpenNMS Group, Inc.
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
package org.opennms.features.vaadin.events;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.opennms.core.utils.ConfigFileConstants;
import org.opennms.core.xml.JaxbUtils;
import org.opennms.features.vaadin.api.Logger;
import org.opennms.features.vaadin.config.EditorToolbar;
import org.opennms.netmgt.EventConstants;
import org.opennms.netmgt.config.EventConfDao;
import org.opennms.netmgt.model.events.EventBuilder;
import org.opennms.netmgt.model.events.EventProxy;
import org.opennms.netmgt.xml.eventconf.AlarmData;
import org.opennms.netmgt.xml.eventconf.Events;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class Event Panel.
 * 
 * @author <a href="mailto:agalue@opennms.org">Alejandro Galue</a> 
 */
@SuppressWarnings("serial")
public abstract class EventPanel extends Panel {

    /** The isNew flag. True, if the group is new. */
    private boolean isNew = false;

    /** The Events Configuration DAO. */
    private EventConfDao eventConfDao;

    /** The Events Proxy. */
    private EventProxy eventProxy;

    /** The Events File. */
    private File eventFile;

    /**
     * Instantiates a new event panel.
     *
     * @param eventConfDao the OpenNMS Events Configuration DAO
     * @param eventProxy the OpenNMS Events Proxy
     * @param eventFile the events file
     * @param events the OpenNMS events object
     * @param logger the logger object
     */
    public EventPanel(final EventConfDao eventConfDao, final EventProxy eventProxy, final File eventFile, final Events events, final Logger logger) {

        if (eventProxy == null)
            throw new RuntimeException("eventProxy cannot be null.");

        if (eventConfDao == null)
            throw new RuntimeException("eventConfDao cannot be null.");

        this.eventConfDao = eventConfDao;
        this.eventProxy = eventProxy;
        this.eventFile = eventFile;

        setCaption("Events");
        addStyleName("light");

        final HorizontalLayout topToolbar = new HorizontalLayout();
        topToolbar.addComponent(new Button("Save Events File", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                processEvents(events, logger);
            }
        }));
        topToolbar.addComponent(new Button("Cancel", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                logger.info("Event processing has been canceled");
                cancel();
            }
        }));

        final EventTable eventTable = new EventTable(events.getEventCollection());

        final EventForm eventForm = new EventForm();
        eventForm.setVisible(false);

        final EditorToolbar bottomToolbar = new EditorToolbar() {
            @Override
            public void save() {
                org.opennms.netmgt.xml.eventconf.Event event = eventForm.getEvent();
                logger.info("Event " + event.getUei() + " has been " + (isNew ? "created." : "updated."));
                try {
                    eventForm.getFieldGroup().commit();
                    eventForm.setReadOnly(true);
                    eventTable.refreshRowCache();
                } catch (CommitException e) {
                    String msg = "Can't save the changes: " + e.getMessage();
                    logger.error(msg);
                    Notification.show(msg, Notification.Type.ERROR_MESSAGE);
                }
            }
            @Override
            public void delete() {
                Object eventId = eventTable.getValue();
                if (eventId != null) {
                    org.opennms.netmgt.xml.eventconf.Event event = eventTable.getEvent(eventId);
                    logger.info("Event " + event.getUei() + " has been removed.");
                    eventTable.select(null);
                    eventTable.removeItem(eventId);
                    eventTable.refreshRowCache();
                }
            }
            @Override
            public void edit() {
                eventForm.setReadOnly(false);
            }
            @Override
            public void cancel() {
                eventForm.getFieldGroup().discard();
                eventForm.setReadOnly(true);
            }
        };
        bottomToolbar.setVisible(false);

        eventTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Object eventId = eventTable.getValue();
                if (eventId != null) {
                    eventForm.setEvent(eventTable.getEvent(eventId));
                }
                eventForm.setReadOnly(true);
                eventForm.setVisible(eventId != null);
                bottomToolbar.setReadOnly(true);
                bottomToolbar.setVisible(eventId != null);
            }
        });   

        final Button add = new Button("Add Event", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                eventTable.addEvent(eventForm.createBasicEvent());
                eventForm.setReadOnly(false);
                bottomToolbar.setReadOnly(false);
                setIsNew(true);
            }
        });

        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.addComponent(topToolbar);
        mainLayout.addComponent(eventTable);
        mainLayout.addComponent(add);
        mainLayout.addComponent(eventForm);
        mainLayout.addComponent(bottomToolbar);
        mainLayout.setComponentAlignment(topToolbar, Alignment.MIDDLE_RIGHT);
        mainLayout.setComponentAlignment(add, Alignment.MIDDLE_RIGHT);

        setContent(mainLayout);
    }

    /**
     * Sets the value of the ifNew flag.
     *
     * @param isNew true, if the group is new.
     */
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    /**
     * Cancel.
     */
    public abstract void cancel();

    /**
     * Success.
     */
    public abstract void success();

    /**
     * Failure.
     *
     * @param reason the reason
     */
    public abstract void failure(String reason);

    /**
     * Process events.
     *
     * @param events the OpenNMS Events
     * @param logger the logger
     */
    public void processEvents(final Events events, final Logger logger) {
        if (eventFile.exists()) {
            ConfirmDialog.show(getUI(),
                               "Are you sure?",
                               "Do you really want to override the existig file?\nAll current information will be lost.",
                               "Yes",
                               "No",
                               new ConfirmDialog.Listener() {
                public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        validateFile(eventFile, events, logger);
                    }
                }
            });
        } else {
            validateFile(eventFile, events, logger);
        }
    }

    /**
     * Validate file.
     *
     * @param file the file
     * @param events the events
     * @param logger the logger
     */
    private void validateFile(final File file, final Events events, final Logger logger) {
        int eventCount = 0;
        for (org.opennms.netmgt.xml.eventconf.Event e : events.getEventCollection()) {
            if (eventConfDao.findByUei(e.getUei()) != null)
                eventCount++;
        }
        if (eventCount == 0) {
            saveFile(file, events, logger);
        } else {
            ConfirmDialog.show(getUI(),
                               "Are you sure?",
                               eventCount + " of the new events are already on the configuration files.\nDo you really want to override those events ?",
                               "Yes",
                               "No",
                               new ConfirmDialog.Listener() {
                public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        saveFile(file, events, logger);
                    }
                }
            });
        }
    }

    /**
     * Save file.
     *
     * @param file the file
     * @param events the events
     * @param logger the logger
     */
    private void saveFile(final File file, final Events events, final Logger logger) {
        try {
            // Normalize the Event Content (required to avoid marshaling problems)
            // TODO Are other normalizations required ?
            for (org.opennms.netmgt.xml.eventconf.Event event : events.getEventCollection()) {
                logger.debug("Normalizing event " + event.getUei());
                AlarmData ad = event.getAlarmData();
                if (ad != null && (ad.getReductionKey() == null || ad.getReductionKey().trim().isEmpty()))
                    event.setAlarmData(null);
            }
            // Save the XML of the new events
            saveEvents(events, file, logger);
            // Add a reference to the new file into eventconf.xml if there are events
            String fileName = file.getAbsolutePath().replaceFirst(".*\\/events\\/(.*)", "events/$1");
            final Events rootEvents = eventConfDao.getRootEvents();
            final File rootFile = ConfigFileConstants.getFile(ConfigFileConstants.EVENT_CONF_FILE_NAME);
            if (events.getEventCount() > 0) {
                if (!rootEvents.getEventFileCollection().contains(fileName)) {
                    logger.info("Adding a reference to " + fileName + " inside eventconf.xml.");
                    rootEvents.getEventFileCollection().add(0, fileName);
                    saveEvents(rootEvents, rootFile, logger);
                }
            } else {
                // If a reference to an empty events file exist, it should be removed.
                if (rootEvents.getEventFileCollection().contains(fileName)) {
                    logger.info("Removing a reference to " + fileName + " inside eventconf.xml because there are no events.");
                    rootEvents.getEventFileCollection().remove(fileName);
                    saveEvents(rootEvents, rootFile, logger);
                }
            }
            EventBuilder eb = new EventBuilder(EventConstants.EVENTSCONFIG_CHANGED_EVENT_UEI, "WebUI");
            eventProxy.send(eb.getEvent());
            logger.info("The event's configuration reload operation is being performed.");
            success();
        } catch (Exception e) {
            logger.error(e.getClass() + ": " + (e.getMessage() == null ? "[No Details]" : e.getMessage()));
            if (e.getMessage() == null) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                logger.error(sw.toString());
            }
            failure(e.getMessage());
        }
    }

    /**
     * Save events.
     *
     * @param events the events
     * @param eventFile the events file
     * @param logger the logger
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void saveEvents(final Events events, final File eventFile, final Logger logger) throws IOException {
        logger.info("Saving XML data into " + eventFile);
        FileWriter writer = new FileWriter(eventFile);
        JaxbUtils.marshal(events, writer);
        writer.close();
    }
}
