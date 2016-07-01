/*************************************************************************
 *
 * $RCSfile: IOOo.java,v $
 *
 * $Revision: 1.6 $
 *
 * last change: $Author: cedricbosdo $ $Date: 2008/12/13 13:42:50 $
 *
 * The Contents of this file are made available subject to the terms of
 * the GNU Lesser General Public License Version 2.1
 *
 * Sun Microsystems Inc., October, 2000
 *
 *
 * GNU Lesser General Public License Version 2.1
 * =============================================
 * Copyright 2000 by Sun Microsystems, Inc.
 * 901 San Antonio Road, Palo Alto, CA 94303, USA
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA 02111-1307 USA
 *
 * The Initial Developer of the Original Code is: Sun Microsystems, Inc..
 *
 * Copyright: 2002 by Sun Microsystems, Inc.
 *
 * All Rights Reserved.
 *
 * Contributor(s): Cedric Bosdonnat
 *
 *
 ************************************************************************/
package org.libreoffice.ide.eclipse.core.model.config;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.libreoffice.ide.eclipse.core.model.IUnoidlProject;

/**
 * Interface for a LibreOffice configuration instance. This can even be implemented as an URE instance.
 *
 * @author cedricbosdo
 */
public interface IOOo {

    /**
     * Set the home directory.
     *
     * @param pHome
     *            the absolute path to the home directory
     * @throws InvalidConfigException
     *             is thrown if the path doesn't match the implementation requirement for a LibreOffice instance. The
     *             error code will be {@link InvalidConfigException#INVALID_OOO_HOME}
     */
    public void setHome(String pHome) throws InvalidConfigException;

    /**
     * Returns the path to the LibreOffice home directory. This string could be passed to the Path constructor to get
     * the folder object.
     *
     * @return path to the LibreOffice home directory.
     */
    public String getHome();

    /**
     * Returns the LibreOffice name. It should be a unique identifier
     *
     * @return LibreOffice name
     */
    public String getName();

    /**
     * <p>
     * Returns the path to the LibreOffice classes directory. These strings could be passed to the Path constructor to
     * get the folder object.
     * </p>
     *
     * <p>
     * <em>This method should be used for future compatibility with
     * URE applications</em>
     * </p>
     *
     * @return path to the LibreOffice classes directory
     */
    public String[] getClassesPath();

    /**
     * <p>
     * Returns the path to the LibreOffice shared libraries. This string could be passed to the Path constructor to get
     * the folder object.
     * </p>
     *
     * @return path to the LibreOffice libraries directory
     */
    public String[] getLibsPath();

    /**
     * <p>
     * Returns the path to any folder containing binaries in the LibreOffice installation. This string could be passed
     * to the Path constructor to get the folder object.
     * </p>
     *
     * @return paths to the LibreOffice binary directories
     */
    public String[] getBinPath();

    /**
     * @return the path to the <code>types.rdb</code> file of the LibreOffice or URE instance.
     */
    public String[] getTypesPath();

    /**
     * @return the path to the <code>services.rdb</code> file of the LibreOffice or URE instance.
     */
    public String[] getServicesPath();

    /**
     * @return the path to the UNO bootstrap properties file.
     */
    public String getUnorcPath();

    /**
     * @return the path to the UNO executable file
     */
    public String getUnoPath();

    /**
     * @return the path to the <code>javaldx</code> executable
     */
    public String getJavaldxPath();

    /**
     * Returns a command to execute a <code>uno</code> component.
     *
     * @param pImplementationName
     *            the name of the component implementation to run
     * @param pLibLocation
     *            the name of the library containing the implementation
     * @param pRegistriesPaths
     *            the path to the additional registries
     * @param pArgs
     *            the argument for the component launch
     *
     * @return the command to execute the <code>uno</code> binary
     */
    public String createUnoCommand(String pImplementationName, String pLibLocation, String[] pRegistriesPaths,
        String[] pArgs);

    /**
     * Run the <code>uno</code> executable with the given Main implementation, the arguments and the launcher.
     *
     * @param pPrj
     *            the project to run
     * @param pMain
     *            the main implementation
     * @param pArgs
     *            the argument to pass to the main implementation
     * @param pLaunch
     *            the launcher
     * @param pMonitor
     *            a monitor to follow the progress
     */
    public void runUno(IUnoidlProject pPrj, String pMain, String pArgs, ILaunch pLaunch, IProgressMonitor pMonitor);

    /**
     *
     * @param pPrj
     *            the project to run
     * @param pLaunch
     *            the launcher to which we'll add our processes
     * @param pUserInstallation
     *            the userInstallation folder to use. If null we'll go with the default system one.
     * @param pExtraOptionsProvider
     *            provider for extra env variables to be set before launching.
     * @param pMonitor
     *            a monitor to follow the progress
     */
    public void runOffice(IUnoidlProject pPrj, ILaunch pLaunch, IPath pUserInstallation,
        IExtraOptionsProvider pExtraOptionsProvider, IProgressMonitor pMonitor);

    /**
     * @return <code>true</code> if the LibreOffice instance has a package manager.
     */
    public boolean canManagePackages();

    /**
     * Update a package in the LibreOffice instance if it can manages packages.
     *
     * @param pPackageFile
     *            the package to add or update
     * @param pUserInstallation
     *            path to the user profile folder.
     */
    public void updatePackage(File pPackageFile, IPath pUserInstallation);
}
