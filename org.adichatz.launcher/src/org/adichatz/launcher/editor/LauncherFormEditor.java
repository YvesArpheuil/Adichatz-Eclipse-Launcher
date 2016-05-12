/*******************************************************************************
* Copyright � Adichatz (2007-2016) - www.adichatz.org
*
* arpheuil@adichatz.org
*
* This software is a computer program whose purpose is to build easily
* Eclipse RCP applications using JPA in a JEE or JSE context.
*
* This software is governed by the CeCILL-C license under French law and
* abiding by the rules of distribution of free software.  You can  use,
* modify and/ or redistribute the software under the terms of the CeCILL
* license as circulated by CEA, CNRS and INRIA at the following URL
* "http://www.cecill.info".
*
* As a counterpart to the access to the source code and  rights to copy,
* modify and redistribute granted by the license, users are provided only
* with a limited warranty  and the software's author,  the holder of the
* economic rights,  and the successive licensors  have only  limited
* liability.
*
* In this respect, the user's attention is drawn to the risks associated
* with loading,  using,  modifying and/or developing or reproducing the
* software by the user in light of its specific status of free software,
* that may mean  that it is complicated to manipulate,  and  that  also
* therefore means  that it is reserved for developers  and  experienced
* professionals having in-depth computer knowledge. Users are therefore
* encouraged to load and test the software's suitability as regards their
* requirements in conditions enabling the security of their systems and/or
* data to be ensured and,  more generally, to use and operate it in the
* same conditions as regards security.
*
* The fact that you are presently reading this means that you have had
* knowledge of the CeCILL license and that you accept its terms.
*
*
********************************************************************************
*
* Copyright � Adichatz (2007-2016) - www.adichatz.org
*
* arpheuil@adichatz.org
*
* Ce logiciel est un programme informatique servant � construire rapidement des
* applications Eclipse RCP en utilisant JPA dans un contexte JSE ou JEE.
*
* Ce logiciel est r�gi par la licence CeCILL-C soumise au droit fran�ais et
* respectant les principes de diffusion des logiciels libres. Vous pouvez
* utiliser, modifier et/ou redistribuer ce programme sous les conditions
* de la licence CeCILL telle que diffus�e par le CEA, le CNRS et l'INRIA
* sur le site "http://www.cecill.info".
*
* En contrepartie de l'accessibilit� au code source et des droits de copie,
* de modification et de redistribution accord�s par cette licence, il n'est
* offert aux utilisateurs qu'une garantie limit�e.  Pour les m�mes raisons,
* seule une responsabilit� restreinte p�se sur l'auteur du programme,  le
* titulaire des droits patrimoniaux et les conc�dants successifs.
*
* A cet �gard  l'attention de l'utilisateur est attir�e sur les risques
* associ�s au chargement,  � l'utilisation,  � la modification et/ou au
* d�veloppement et � la reproduction du logiciel par l'utilisateur �tant
* donn� sa sp�cificit� de logiciel libre, qui peut le rendre complexe �
* manipuler et qui le r�serve donc � des d�veloppeurs et des professionnels
* avertis poss�dant  des  connaissances  informatiques approfondies.  Les
* utilisateurs sont donc invit�s � charger  et  tester  l'ad�quation  du
* logiciel � leurs besoins dans des conditions permettant d'assurer la
* s�curit� de leurs syst�mes et ou de leurs donn�es et, plus g�n�ralement,
* � l'utiliser et l'exploiter dans les m�mes conditions de s�curit�.
*
* Le fait que vous puissiez acc�der � cet en-t�te signifie que vous avez
* pris connaissance de la licence CeCILL, et que vous en avez accept� les
* termes.
*******************************************************************************/
package org.adichatz.launcher.editor;

import static org.adichatz.launcher.LauncherTools.getValueFromBundle;

import java.io.File;

import org.adichatz.launcher.LauncherTools;
import org.adichatz.launcher.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IDocumentProviderExtension;

// TODO: Auto-generated Javadoc
/**
 * A editor for Launcher.xml file with two pages:</br>
 * <ul>
 * <li>A page containing a tree to launch applications or open files.</li>
 * <li>A XML editor page.</li>
 * </ul>
 *
 * @author Yves Arpheuil
 */
public class LauncherFormEditor extends FormEditor {

	/** The id. */
	public static String ID = "org.adichatz.launcher.editor.LauncherFormEditor";

	/** The logger. */
	private static Logger logger = Logger.getInstance();

	/** The xml text editor. */
	private XmlTextEditor xmlTextEditor;

	/** The launcher tree form page. */
	private LauncherTreeFormPage launcherTreeFormPage;

	/** The activation listener. */
	private IWindowListener activationListener;

	/** The stamp. */
	private long stamp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.FormEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		// Change name as standard name so that created memento in workbench.xmi file is compliant.
		setPartName("Launcher.xml");
		activationListener = new IWindowListener() {
			@Override
			public void windowActivated(IWorkbenchWindow window) {
				Shell shell = getSite().getShell();
				File launcherFile = LauncherTools.getLauncherFile();
				if (!launcherFile.exists()) {
					MessageDialog.openInformation(shell, getValueFromBundle("error.activated.file.deleted.title"),
							getValueFromBundle("error.activated.file.deleted.message", launcherFile.getAbsolutePath()));
					close(false);
				}
				long currentStamp = launcherFile.lastModified();
				if (currentStamp != stamp) {
					if (MessageDialog.openQuestion(shell, getValueFromBundle("error.activated.outofsync.title"),
							getValueFromBundle("error.activated.outofsync.message", launcherFile.getAbsolutePath()))) {
						launcherTreeFormPage.refresh();
						IDocumentProvider documentProvider = xmlTextEditor.getDocumentProvider();
						try {
							if (documentProvider instanceof IDocumentProviderExtension) {
								IDocumentProviderExtension extension = (IDocumentProviderExtension) documentProvider;
								extension.synchronize(getEditorInput());
							} else {
								xmlTextEditor.doSetInput(getEditorInput());
							}
						} catch (CoreException e) {
							logger.logError(e);
						}
					}
					stamp = currentStamp;
				}
			}

			@Override
			public void windowDeactivated(IWorkbenchWindow window) {
			}

			@Override
			public void windowClosed(IWorkbenchWindow window) {
			}

			@Override
			public void windowOpened(IWorkbenchWindow window) {
			}
		};
		PlatformUI.getWorkbench().addWindowListener(activationListener);
		stamp = LauncherTools.getLauncherFile().lastModified();
	}

	/**
	 * Fire dirty property change.
	 */
	public void fireDirtyPropertyChange() {
		firePropertyChange(PROP_DIRTY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	protected void addPages() {
		try {
			launcherTreeFormPage = new LauncherTreeFormPage(this);
			addPage(launcherTreeFormPage);
			setPageImage(0, LauncherTools.getRegisteredImage(LauncherTools.IMG_TREE));
			addXMLEditor(1);
		} catch (PartInitException e) {
			logger.logError(e);
		}
	}

	/**
	 * Adds the xml editor.
	 *
	 * @param pageIndex
	 *            the page index
	 */
	private void addXMLEditor(int pageIndex) {
		try {
			xmlTextEditor = new XmlTextEditor(this, pageIndex);
			int fSourcePageIndex = addPage(xmlTextEditor, getEditorInput());
			setPageText(fSourcePageIndex, "XML");
			setPageImage(fSourcePageIndex, LauncherTools.getRegisteredImage(LauncherTools.IMG_XML));
			firePropertyChange(PROP_TITLE);
		} catch (PartInitException e) {
			logger.logError(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime. IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		if (xmlTextEditor.getXmlViewer().isEditable())
			xmlTextEditor.doSave(progressMonitor);
		stamp = LauncherTools.getLauncherFile().lastModified();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * Gets the launcher tree form page.
	 *
	 * @return the launcher tree form page
	 */
	public LauncherTreeFormPage getLauncherTreeFormPage() {
		return launcherTreeFormPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.FormEditor#dispose()
	 */
	public void dispose() {
		if (activationListener != null) {
			PlatformUI.getWorkbench().removeWindowListener(activationListener);
			activationListener = null;
		}
	}
}
