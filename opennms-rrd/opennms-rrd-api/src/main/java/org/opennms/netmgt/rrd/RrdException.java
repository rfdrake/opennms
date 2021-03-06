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

package org.opennms.netmgt.rrd;

/**
 * This exception indicates an error has occurred creating, updating, or
 * fetching data from an Rrd file
 */
public class RrdException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5251168945484451493L;

    /**
     * <p>Constructor for RrdException.</p>
     */
    public RrdException() {
        super();
        // FIXME Auto-generated constructor stub
    }

    /**
     * <p>Constructor for RrdException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public RrdException(String message) {
        super(message);
        // FIXME Auto-generated constructor stub
    }

    /**
     * <p>Constructor for RrdException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     */
    public RrdException(String message, Throwable cause) {
        super(message, cause);
        // FIXME Auto-generated constructor stub
    }

    /**
     * <p>Constructor for RrdException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object.
     */
    public RrdException(Throwable cause) {
        super(cause);
        // FIXME Auto-generated constructor stub
    }

}
