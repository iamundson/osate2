/*******************************************************************************
 * Copyright (c) 2004-2022 Carnegie Mellon University and others. (see Contributors file).
 * All Rights Reserved.
 *
 * NO WARRANTY. ALL MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE
 * OR MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON UNIVERSITY DOES NOT
 * MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * SPDX-License-Identifier: EPL-2.0
 *
 * Created, in part, with funding and support from the United States Government. (see Acknowledgments file).
 *
 * This program includes and/or can make use of certain third party source code, object code, documentation and other
 * files ("Third Party Software"). The Third Party Software that is used by this program is dependent upon your system
 * configuration. By using this program, You agree to comply with any and all relevant Third Party Software terms and
 * conditions contained in any such Third Party Software or separate license file distributed with such Third Party
 * Software. The parties who own the Third Party Software ("Third Party Licensors") are intended third party benefici-
 * aries to this license with respect to the terms applicable to their Third Party Software. Third Party Software li-
 * censes only apply to the Third Party Software and not any other portion of this program or this program as a whole.
 *******************************************************************************/
package org.osate.examples.tests;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.testsupport.Aadl2InjectorProvider;
import org.osate.testsupport.TestHelper;

import com.google.inject.Inject;


@RunWith(XtextRunner.class)
@InjectWith(Aadl2InjectorProvider.class)
public class Arp4761Test {
	@Inject
	private TestHelper<AadlPackage> testHelper;
	private String bundle = "org.osate.examples";
	private String entry = "examples/arp4761/";
	private List<String> files = Arrays.asList("advanced/functional/wbs/command-control.aadl",
			"advanced/functional/wbs/generic.aadl",
			"advanced/functional/wbs/main.aadl", "advanced/functional/electrical.aadl",
			"advanced/functional/generic_functions.aadl", "advanced/functional/hydraulic.aadl",
			"advanced/functional/main.aadl", "advanced/functional/throttle.aadl",
			"advanced/functional/WBSFunctionalErrorLib.aadl", "advanced/implementation/bscu.aadl",
			"advanced/implementation/common.aadl", "advanced/implementation/communication.aadl",
			"advanced/implementation/pedals.aadl", "advanced/implementation/platform.aadl",
			"advanced/implementation/power.aadl", "advanced/implementation/pump.aadl",
			"advanced/implementation/valves.aadl", "advanced/implementation/wbs.aadl",
			"advanced/implementation/WBSImplementationErrorLib.aadl", "advanced/implementation/wheel.aadl",
			"advanced/integration/main.aadl", "advanced/integration/WBSMappingErrorLib.aadl",
			"advanced/misc/functional-initial.aadl", "simple/bscu.aadl", "simple/common.aadl",
			"simple/communication.aadl", "simple/error.aadl", "simple/pedals.aadl", "simple/platform.aadl",
			"simple/power.aadl", "simple/proofs.aadl", "simple/pump.aadl", "simple/valves.aadl", "simple/wbs.aadl",
			"simple/wheel.aadl");

	@Test
	public void emptyTest() throws Exception {
		for (String file : files) {
			AadlPackage pkg = testHelper.parseBundleFile(bundle, entry + file);
			final EList<Classifier> cls = pkg.getOwnedPublicSection().getOwnedClassifiers();
			for (Classifier classifier : cls) {
				if (classifier instanceof SystemImplementation) {
					SystemImplementation impl = (SystemImplementation) classifier;
					SystemInstance si = InstantiateModel.instantiate(impl);
				}
			}
		}
	}
}