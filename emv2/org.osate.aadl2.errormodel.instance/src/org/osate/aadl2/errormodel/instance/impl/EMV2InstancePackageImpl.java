/**
 * Copyright (c) 2004-2020 Carnegie Mellon University and others. (see Contributors file).
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
 */
package org.osate.aadl2.errormodel.instance.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.errormodel.instance.BindingPropagation;
import org.osate.aadl2.errormodel.instance.BindingType;
import org.osate.aadl2.errormodel.instance.CompositeStateInstance;
import org.osate.aadl2.errormodel.instance.ConstrainedInstanceObject;
import org.osate.aadl2.errormodel.instance.ConstraintElement;
import org.osate.aadl2.errormodel.instance.ConstraintExpression;
import org.osate.aadl2.errormodel.instance.EMV2AnnexInstance;
import org.osate.aadl2.errormodel.instance.EMV2InstanceFactory;
import org.osate.aadl2.errormodel.instance.EMV2InstanceObject;
import org.osate.aadl2.errormodel.instance.EMV2InstancePackage;
import org.osate.aadl2.errormodel.instance.EOperation;
import org.osate.aadl2.errormodel.instance.ErrorDetectionInstance;
import org.osate.aadl2.errormodel.instance.ErrorFlowInstance;
import org.osate.aadl2.errormodel.instance.ErrorPropagationConditionInstance;
import org.osate.aadl2.errormodel.instance.ErrorPropagationInstance;
import org.osate.aadl2.errormodel.instance.EventInstance;
import org.osate.aadl2.errormodel.instance.FeaturePropagation;
import org.osate.aadl2.errormodel.instance.PointPropagation;
import org.osate.aadl2.errormodel.instance.PropagationPathInstance;
import org.osate.aadl2.errormodel.instance.PropagationPointInstance;
import org.osate.aadl2.errormodel.instance.StateInstance;
import org.osate.aadl2.errormodel.instance.StateMachineInstance;
import org.osate.aadl2.errormodel.instance.StateTransitionInstance;
import org.osate.aadl2.errormodel.instance.TypeProductInstance;
import org.osate.aadl2.errormodel.instance.TypeReference;
import org.osate.aadl2.errormodel.instance.TypeSetInstance;
import org.osate.aadl2.errormodel.instance.TypeTokenInstance;
import org.osate.aadl2.instance.InstancePackage;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EMV2InstancePackageImpl extends EPackageImpl implements EMV2InstancePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emv2AnnexInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emv2InstanceObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateMachineInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constrainedInstanceObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateTransitionInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeStateInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorFlowInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorPropagationConditionInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorDetectionInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propagationPointInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propagationPathInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorPropagationInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featurePropagationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointPropagationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bindingPropagationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeTokenInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeSetInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeProductInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eOperationEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bindingTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.osate.aadl2.errormodel.instance.EMV2InstancePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EMV2InstancePackageImpl() {
		super(eNS_URI, EMV2InstanceFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link EMV2InstancePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EMV2InstancePackage init() {
		if (isInited) {
			return (EMV2InstancePackage) EPackage.Registry.INSTANCE.getEPackage(EMV2InstancePackage.eNS_URI);
		}

		// Obtain or create and register package
		Object registeredEMV2InstancePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		EMV2InstancePackageImpl theEMV2InstancePackage = registeredEMV2InstancePackage instanceof EMV2InstancePackageImpl
				? (EMV2InstancePackageImpl) registeredEMV2InstancePackage
				: new EMV2InstancePackageImpl();

		isInited = true;

		// Initialize simple dependencies
		Aadl2Package.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();
		ErrorModelPackage.eINSTANCE.eClass();
		InstancePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEMV2InstancePackage.createPackageContents();

		// Initialize created meta-data
		theEMV2InstancePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEMV2InstancePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EMV2InstancePackage.eNS_URI, theEMV2InstancePackage);
		return theEMV2InstancePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEMV2AnnexInstance() {
		return emv2AnnexInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_Transitions() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_PropagationPoints() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_Events() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_StateMachine() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_ErrorPropagationConditions() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_ErrorDetections() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_ErrorFlows() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_Composites() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_PropagationPaths() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEMV2AnnexInstance_Propagations() {
		return (EReference) emv2AnnexInstanceEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEMV2InstanceObject() {
		return emv2InstanceObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStateMachineInstance() {
		return stateMachineInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateMachineInstance_States() {
		return (EReference) stateMachineInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateMachineInstance_CurrentState() {
		return (EReference) stateMachineInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateMachineInstance_StateMachine() {
		return (EReference) stateMachineInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStateInstance() {
		return stateInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateInstance_State() {
		return (EReference) stateInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConstrainedInstanceObject() {
		return constrainedInstanceObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstrainedInstanceObject_InstanceObject() {
		return (EReference) constrainedInstanceObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstrainedInstanceObject_Constraint() {
		return (EReference) constrainedInstanceObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConstrainedInstanceObject_PropagationKind() {
		return (EAttribute) constrainedInstanceObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStateTransitionInstance() {
		return stateTransitionInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateTransitionInstance_StateTransition() {
		return (EReference) stateTransitionInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateTransitionInstance_InStates() {
		return (EReference) stateTransitionInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateTransitionInstance_Condition() {
		return (EReference) stateTransitionInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateTransitionInstance_TargetState() {
		return (EReference) stateTransitionInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompositeStateInstance() {
		return compositeStateInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompositeStateInstance_TargetState() {
		return (EReference) compositeStateInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompositeStateInstance_Condition() {
		return (EReference) compositeStateInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompositeStateInstance_CompositeState() {
		return (EReference) compositeStateInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getErrorFlowInstance() {
		return errorFlowInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorFlowInstance_Emv2Element() {
		return (EReference) errorFlowInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorFlowInstance_Incoming() {
		return (EReference) errorFlowInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorFlowInstance_Outgoing() {
		return (EReference) errorFlowInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorFlowInstance_Source() {
		return (EAttribute) errorFlowInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorFlowInstance_Sink() {
		return (EAttribute) errorFlowInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getErrorPropagationConditionInstance() {
		return errorPropagationConditionInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPropagationConditionInstance_Emv2Element() {
		return (EReference) errorPropagationConditionInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPropagationConditionInstance_InStates() {
		return (EReference) errorPropagationConditionInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPropagationConditionInstance_Condition() {
		return (EReference) errorPropagationConditionInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPropagationConditionInstance_OutgoingPropagation() {
		return (EReference) errorPropagationConditionInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorPropagationConditionInstance_Source() {
		return (EAttribute) errorPropagationConditionInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorPropagationConditionInstance_Sink() {
		return (EAttribute) errorPropagationConditionInstanceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getErrorDetectionInstance() {
		return errorDetectionInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorDetectionInstance_Emv2Element() {
		return (EReference) errorDetectionInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorDetectionInstance_InStates() {
		return (EReference) errorDetectionInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorDetectionInstance_Condition() {
		return (EReference) errorDetectionInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorDetectionInstance_ErrorCode() {
		return (EAttribute) errorDetectionInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorDetectionInstance_Port() {
		return (EReference) errorDetectionInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPropagationPointInstance() {
		return propagationPointInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPropagationPointInstance_PropagationPoint() {
		return (EReference) propagationPointInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEventInstance() {
		return eventInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEventInstance_Event() {
		return (EReference) eventInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEventInstance_GeneratedTypedEvents() {
		return (EReference) eventInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConstraintExpression() {
		return constraintExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConstraintExpression_Operator() {
		return (EAttribute) constraintExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConstraintExpression_K() {
		return (EAttribute) constraintExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraintExpression_ConstraintElements() {
		return (EReference) constraintExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConstraintElement() {
		return constraintElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPropagationPathInstance() {
		return propagationPathInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPropagationPathInstance_Emv2Element() {
		return (EReference) propagationPathInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPropagationPathInstance_Source() {
		return (EReference) propagationPathInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPropagationPathInstance_Target() {
		return (EReference) propagationPathInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getErrorPropagationInstance() {
		return errorPropagationInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPropagationInstance_InErrorPropagation() {
		return (EReference) errorPropagationInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPropagationInstance_OutErrorPropagation() {
		return (EReference) errorPropagationInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPropagationInstance_InTokens() {
		return (EReference) errorPropagationInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPropagationInstance_OutTokens() {
		return (EReference) errorPropagationInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeaturePropagation() {
		return featurePropagationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeaturePropagation_Feature() {
		return (EReference) featurePropagationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPointPropagation() {
		return pointPropagationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPointPropagation_Point() {
		return (EReference) pointPropagationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBindingPropagation() {
		return bindingPropagationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBindingPropagation_Binding() {
		return (EAttribute) bindingPropagationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeTokenInstance() {
		return typeTokenInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeReference() {
		return typeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeReference_Type() {
		return (EReference) typeReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeSetInstance() {
		return typeSetInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeSetInstance_ResolvedSet() {
		return (EReference) typeSetInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeSetInstance_DeclaredSet() {
		return (EReference) typeSetInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeSetInstance_Tokens() {
		return (EReference) typeSetInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeProductInstance() {
		return typeProductInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeProductInstance_Types() {
		return (EReference) typeProductInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getEOperation() {
		return eOperationEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getBindingType() {
		return bindingTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMV2InstanceFactory getEMV2InstanceFactory() {
		return (EMV2InstanceFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) {
			return;
		}
		isCreated = true;

		// Create classes and their features
		emv2AnnexInstanceEClass = createEClass(EMV2_ANNEX_INSTANCE);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__TRANSITIONS);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__PROPAGATION_POINTS);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__EVENTS);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__STATE_MACHINE);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__ERROR_PROPAGATION_CONDITIONS);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__ERROR_DETECTIONS);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__ERROR_FLOWS);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__COMPOSITES);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__PROPAGATION_PATHS);
		createEReference(emv2AnnexInstanceEClass, EMV2_ANNEX_INSTANCE__PROPAGATIONS);

		emv2InstanceObjectEClass = createEClass(EMV2_INSTANCE_OBJECT);

		stateMachineInstanceEClass = createEClass(STATE_MACHINE_INSTANCE);
		createEReference(stateMachineInstanceEClass, STATE_MACHINE_INSTANCE__STATES);
		createEReference(stateMachineInstanceEClass, STATE_MACHINE_INSTANCE__CURRENT_STATE);
		createEReference(stateMachineInstanceEClass, STATE_MACHINE_INSTANCE__STATE_MACHINE);

		stateInstanceEClass = createEClass(STATE_INSTANCE);
		createEReference(stateInstanceEClass, STATE_INSTANCE__STATE);

		constrainedInstanceObjectEClass = createEClass(CONSTRAINED_INSTANCE_OBJECT);
		createEReference(constrainedInstanceObjectEClass, CONSTRAINED_INSTANCE_OBJECT__INSTANCE_OBJECT);
		createEReference(constrainedInstanceObjectEClass, CONSTRAINED_INSTANCE_OBJECT__CONSTRAINT);
		createEAttribute(constrainedInstanceObjectEClass, CONSTRAINED_INSTANCE_OBJECT__PROPAGATION_KIND);

		stateTransitionInstanceEClass = createEClass(STATE_TRANSITION_INSTANCE);
		createEReference(stateTransitionInstanceEClass, STATE_TRANSITION_INSTANCE__STATE_TRANSITION);
		createEReference(stateTransitionInstanceEClass, STATE_TRANSITION_INSTANCE__IN_STATES);
		createEReference(stateTransitionInstanceEClass, STATE_TRANSITION_INSTANCE__CONDITION);
		createEReference(stateTransitionInstanceEClass, STATE_TRANSITION_INSTANCE__TARGET_STATE);

		compositeStateInstanceEClass = createEClass(COMPOSITE_STATE_INSTANCE);
		createEReference(compositeStateInstanceEClass, COMPOSITE_STATE_INSTANCE__TARGET_STATE);
		createEReference(compositeStateInstanceEClass, COMPOSITE_STATE_INSTANCE__CONDITION);
		createEReference(compositeStateInstanceEClass, COMPOSITE_STATE_INSTANCE__COMPOSITE_STATE);

		errorFlowInstanceEClass = createEClass(ERROR_FLOW_INSTANCE);
		createEReference(errorFlowInstanceEClass, ERROR_FLOW_INSTANCE__EMV2_ELEMENT);
		createEReference(errorFlowInstanceEClass, ERROR_FLOW_INSTANCE__INCOMING);
		createEReference(errorFlowInstanceEClass, ERROR_FLOW_INSTANCE__OUTGOING);
		createEAttribute(errorFlowInstanceEClass, ERROR_FLOW_INSTANCE__SOURCE);
		createEAttribute(errorFlowInstanceEClass, ERROR_FLOW_INSTANCE__SINK);

		errorPropagationConditionInstanceEClass = createEClass(ERROR_PROPAGATION_CONDITION_INSTANCE);
		createEReference(errorPropagationConditionInstanceEClass, ERROR_PROPAGATION_CONDITION_INSTANCE__EMV2_ELEMENT);
		createEReference(errorPropagationConditionInstanceEClass, ERROR_PROPAGATION_CONDITION_INSTANCE__IN_STATES);
		createEReference(errorPropagationConditionInstanceEClass, ERROR_PROPAGATION_CONDITION_INSTANCE__CONDITION);
		createEReference(errorPropagationConditionInstanceEClass,
				ERROR_PROPAGATION_CONDITION_INSTANCE__OUTGOING_PROPAGATION);
		createEAttribute(errorPropagationConditionInstanceEClass, ERROR_PROPAGATION_CONDITION_INSTANCE__SOURCE);
		createEAttribute(errorPropagationConditionInstanceEClass, ERROR_PROPAGATION_CONDITION_INSTANCE__SINK);

		errorDetectionInstanceEClass = createEClass(ERROR_DETECTION_INSTANCE);
		createEReference(errorDetectionInstanceEClass, ERROR_DETECTION_INSTANCE__EMV2_ELEMENT);
		createEReference(errorDetectionInstanceEClass, ERROR_DETECTION_INSTANCE__IN_STATES);
		createEReference(errorDetectionInstanceEClass, ERROR_DETECTION_INSTANCE__CONDITION);
		createEAttribute(errorDetectionInstanceEClass, ERROR_DETECTION_INSTANCE__ERROR_CODE);
		createEReference(errorDetectionInstanceEClass, ERROR_DETECTION_INSTANCE__PORT);

		propagationPointInstanceEClass = createEClass(PROPAGATION_POINT_INSTANCE);
		createEReference(propagationPointInstanceEClass, PROPAGATION_POINT_INSTANCE__PROPAGATION_POINT);

		eventInstanceEClass = createEClass(EVENT_INSTANCE);
		createEReference(eventInstanceEClass, EVENT_INSTANCE__EVENT);
		createEReference(eventInstanceEClass, EVENT_INSTANCE__GENERATED_TYPED_EVENTS);

		constraintExpressionEClass = createEClass(CONSTRAINT_EXPRESSION);
		createEAttribute(constraintExpressionEClass, CONSTRAINT_EXPRESSION__OPERATOR);
		createEAttribute(constraintExpressionEClass, CONSTRAINT_EXPRESSION__K);
		createEReference(constraintExpressionEClass, CONSTRAINT_EXPRESSION__CONSTRAINT_ELEMENTS);

		constraintElementEClass = createEClass(CONSTRAINT_ELEMENT);

		propagationPathInstanceEClass = createEClass(PROPAGATION_PATH_INSTANCE);
		createEReference(propagationPathInstanceEClass, PROPAGATION_PATH_INSTANCE__EMV2_ELEMENT);
		createEReference(propagationPathInstanceEClass, PROPAGATION_PATH_INSTANCE__SOURCE);
		createEReference(propagationPathInstanceEClass, PROPAGATION_PATH_INSTANCE__TARGET);

		errorPropagationInstanceEClass = createEClass(ERROR_PROPAGATION_INSTANCE);
		createEReference(errorPropagationInstanceEClass, ERROR_PROPAGATION_INSTANCE__IN_ERROR_PROPAGATION);
		createEReference(errorPropagationInstanceEClass, ERROR_PROPAGATION_INSTANCE__OUT_ERROR_PROPAGATION);
		createEReference(errorPropagationInstanceEClass, ERROR_PROPAGATION_INSTANCE__IN_TOKENS);
		createEReference(errorPropagationInstanceEClass, ERROR_PROPAGATION_INSTANCE__OUT_TOKENS);

		featurePropagationEClass = createEClass(FEATURE_PROPAGATION);
		createEReference(featurePropagationEClass, FEATURE_PROPAGATION__FEATURE);

		pointPropagationEClass = createEClass(POINT_PROPAGATION);
		createEReference(pointPropagationEClass, POINT_PROPAGATION__POINT);

		bindingPropagationEClass = createEClass(BINDING_PROPAGATION);
		createEAttribute(bindingPropagationEClass, BINDING_PROPAGATION__BINDING);

		typeTokenInstanceEClass = createEClass(TYPE_TOKEN_INSTANCE);

		typeReferenceEClass = createEClass(TYPE_REFERENCE);
		createEReference(typeReferenceEClass, TYPE_REFERENCE__TYPE);

		typeSetInstanceEClass = createEClass(TYPE_SET_INSTANCE);
		createEReference(typeSetInstanceEClass, TYPE_SET_INSTANCE__RESOLVED_SET);
		createEReference(typeSetInstanceEClass, TYPE_SET_INSTANCE__DECLARED_SET);
		createEReference(typeSetInstanceEClass, TYPE_SET_INSTANCE__TOKENS);

		typeProductInstanceEClass = createEClass(TYPE_PRODUCT_INSTANCE);
		createEReference(typeProductInstanceEClass, TYPE_PRODUCT_INSTANCE__TYPES);

		// Create enums
		eOperationEEnum = createEEnum(EOPERATION);
		bindingTypeEEnum = createEEnum(BINDING_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) {
			return;
		}
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		InstancePackage theInstancePackage = (InstancePackage) EPackage.Registry.INSTANCE
				.getEPackage(InstancePackage.eNS_URI);
		ErrorModelPackage theErrorModelPackage = (ErrorModelPackage) EPackage.Registry.INSTANCE
				.getEPackage(ErrorModelPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		Aadl2Package theAadl2Package = (Aadl2Package) EPackage.Registry.INSTANCE.getEPackage(Aadl2Package.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		emv2AnnexInstanceEClass.getESuperTypes().add(theInstancePackage.getAnnexInstance());
		emv2InstanceObjectEClass.getESuperTypes().add(theInstancePackage.getInstanceObject());
		stateMachineInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		stateInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		constrainedInstanceObjectEClass.getESuperTypes().add(getConstraintElement());
		stateTransitionInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		compositeStateInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		errorFlowInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		errorPropagationConditionInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		errorDetectionInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		propagationPointInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		eventInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		constraintExpressionEClass.getESuperTypes().add(getConstraintElement());
		constraintElementEClass.getESuperTypes().add(getEMV2InstanceObject());
		propagationPathInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		errorPropagationInstanceEClass.getESuperTypes().add(getConstrainedInstanceObject());
		featurePropagationEClass.getESuperTypes().add(getErrorPropagationInstance());
		pointPropagationEClass.getESuperTypes().add(getErrorPropagationInstance());
		bindingPropagationEClass.getESuperTypes().add(getErrorPropagationInstance());
		typeTokenInstanceEClass.getESuperTypes().add(getEMV2InstanceObject());
		typeReferenceEClass.getESuperTypes().add(getTypeTokenInstance());
		typeSetInstanceEClass.getESuperTypes().add(getTypeTokenInstance());
		typeProductInstanceEClass.getESuperTypes().add(getTypeTokenInstance());

		// Initialize classes and features; add operations and parameters
		initEClass(emv2AnnexInstanceEClass, EMV2AnnexInstance.class, "EMV2AnnexInstance", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEMV2AnnexInstance_Transitions(), getStateTransitionInstance(), null, "transitions", null, 0,
				-1, EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_PropagationPoints(), getPropagationPointInstance(), null,
				"propagationPoints", null, 0, -1, EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_Events(), getEventInstance(), null, "events", null, 0, -1,
				EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_StateMachine(), getStateMachineInstance(), null, "stateMachine", null, 0, 1,
				EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_ErrorPropagationConditions(), getErrorPropagationConditionInstance(), null,
				"errorPropagationConditions", null, 0, -1, EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_ErrorDetections(), getErrorDetectionInstance(), null, "errorDetections",
				null, 0, -1, EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_ErrorFlows(), getErrorFlowInstance(), null, "errorFlows", null, 0, -1,
				EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_Composites(), getCompositeStateInstance(), null, "composites", null, 0, -1,
				EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_PropagationPaths(), getPropagationPathInstance(), null, "propagationPaths",
				null, 0, -1, EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMV2AnnexInstance_Propagations(), getErrorPropagationInstance(), null, "propagations", null,
				0, -1, EMV2AnnexInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(emv2InstanceObjectEClass, EMV2InstanceObject.class, "EMV2InstanceObject", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(stateMachineInstanceEClass, StateMachineInstance.class, "StateMachineInstance", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateMachineInstance_States(), getStateInstance(), null, "states", null, 0, -1,
				StateMachineInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateMachineInstance_CurrentState(), getStateInstance(), null, "currentState", null, 0, 1,
				StateMachineInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateMachineInstance_StateMachine(), theErrorModelPackage.getErrorBehaviorStateMachine(),
				null, "stateMachine", null, 0, 1, StateMachineInstance.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateInstanceEClass, StateInstance.class, "StateInstance", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateInstance_State(), theErrorModelPackage.getErrorBehaviorState(), null, "state", null, 0,
				1, StateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constrainedInstanceObjectEClass, ConstrainedInstanceObject.class, "ConstrainedInstanceObject",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstrainedInstanceObject_InstanceObject(), theInstancePackage.getInstanceObject(), null,
				"instanceObject", null, 0, 1, ConstrainedInstanceObject.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstrainedInstanceObject_Constraint(), theErrorModelPackage.getTypeToken(), null,
				"constraint", null, 0, -1, ConstrainedInstanceObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstrainedInstanceObject_PropagationKind(), ecorePackage.getEString(), "propagationKind",
				null, 0, 1, ConstrainedInstanceObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateTransitionInstanceEClass, StateTransitionInstance.class, "StateTransitionInstance",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateTransitionInstance_StateTransition(), theEcorePackage.getEObject(), null,
				"stateTransition", null, 0, 1, StateTransitionInstance.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateTransitionInstance_InStates(), getStateInstance(), null, "inStates", null, 0, -1,
				StateTransitionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateTransitionInstance_Condition(), getConstraintElement(), null, "condition", null, 0, 1,
				StateTransitionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateTransitionInstance_TargetState(), getStateInstance(), null, "targetState", null, 0, 1,
				StateTransitionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeStateInstanceEClass, CompositeStateInstance.class, "CompositeStateInstance", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeStateInstance_TargetState(), getStateInstance(), null, "targetState", null, 0, 1,
				CompositeStateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeStateInstance_Condition(), getConstraintElement(), null, "condition", null, 0, 1,
				CompositeStateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeStateInstance_CompositeState(), theErrorModelPackage.getCompositeState(), null,
				"compositeState", null, 0, 1, CompositeStateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(errorFlowInstanceEClass, ErrorFlowInstance.class, "ErrorFlowInstance", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getErrorFlowInstance_Emv2Element(), theAadl2Package.getNamedElement(), null, "emv2Element", null,
				0, 1, ErrorFlowInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorFlowInstance_Incoming(), getConstrainedInstanceObject(), null, "incoming", null, 0, 1,
				ErrorFlowInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorFlowInstance_Outgoing(), getConstrainedInstanceObject(), null, "outgoing", null, 0, 1,
				ErrorFlowInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorFlowInstance_Source(), ecorePackage.getEBoolean(), "source", null, 0, 1,
				ErrorFlowInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorFlowInstance_Sink(), ecorePackage.getEBoolean(), "sink", null, 0, 1,
				ErrorFlowInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(errorPropagationConditionInstanceEClass, ErrorPropagationConditionInstance.class,
				"ErrorPropagationConditionInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getErrorPropagationConditionInstance_Emv2Element(),
				theErrorModelPackage.getOutgoingPropagationCondition(), null, "emv2Element", null, 0, 1,
				ErrorPropagationConditionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorPropagationConditionInstance_InStates(), getStateInstance(), null, "inStates", null, 0,
				-1, ErrorPropagationConditionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorPropagationConditionInstance_Condition(), getConstraintElement(), null, "condition",
				null, 0, 1, ErrorPropagationConditionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorPropagationConditionInstance_OutgoingPropagation(), getConstrainedInstanceObject(), null,
				"outgoingPropagation", null, 0, 1, ErrorPropagationConditionInstance.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorPropagationConditionInstance_Source(), ecorePackage.getEBoolean(), "source", null, 0, 1,
				ErrorPropagationConditionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorPropagationConditionInstance_Sink(), ecorePackage.getEBoolean(), "sink", null, 0, 1,
				ErrorPropagationConditionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(errorDetectionInstanceEClass, ErrorDetectionInstance.class, "ErrorDetectionInstance", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getErrorDetectionInstance_Emv2Element(), theErrorModelPackage.getErrorDetection(), null,
				"emv2Element", null, 0, 1, ErrorDetectionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorDetectionInstance_InStates(), getStateInstance(), null, "inStates", null, 0, -1,
				ErrorDetectionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorDetectionInstance_Condition(), getConstraintElement(), null, "condition", null, 0, 1,
				ErrorDetectionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorDetectionInstance_ErrorCode(), ecorePackage.getEString(), "errorCode", null, 0, 1,
				ErrorDetectionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorDetectionInstance_Port(), theInstancePackage.getFeatureInstance(), null, "port", null, 0,
				1, ErrorDetectionInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propagationPointInstanceEClass, PropagationPointInstance.class, "PropagationPointInstance",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropagationPointInstance_PropagationPoint(), theErrorModelPackage.getPropagationPoint(), null,
				"propagationPoint", null, 0, 1, PropagationPointInstance.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventInstanceEClass, EventInstance.class, "EventInstance", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEventInstance_Event(), theErrorModelPackage.getErrorBehaviorEvent(), null, "event", null, 0,
				1, EventInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventInstance_GeneratedTypedEvents(), getConstrainedInstanceObject(), null,
				"generatedTypedEvents", null, 0, -1, EventInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintExpressionEClass, ConstraintExpression.class, "ConstraintExpression", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraintExpression_Operator(), getEOperation(), "operator", null, 0, 1,
				ConstraintExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintExpression_K(), ecorePackage.getELong(), "k", null, 0, 1,
				ConstraintExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstraintExpression_ConstraintElements(), getConstraintElement(), null, "constraintElements",
				null, 0, -1, ConstraintExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintElementEClass, ConstraintElement.class, "ConstraintElement", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(propagationPathInstanceEClass, PropagationPathInstance.class, "PropagationPathInstance",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropagationPathInstance_Emv2Element(), theAadl2Package.getNamedElement(), null, "emv2Element",
				null, 0, 1, PropagationPathInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropagationPathInstance_Source(), getConstraintElement(), null, "source", null, 0, 1,
				PropagationPathInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropagationPathInstance_Target(), getConstrainedInstanceObject(), null, "target", null, 0, 1,
				PropagationPathInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(errorPropagationInstanceEClass, ErrorPropagationInstance.class, "ErrorPropagationInstance",
				IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getErrorPropagationInstance_InErrorPropagation(), theErrorModelPackage.getErrorPropagation(),
				null, "inErrorPropagation", null, 0, 1, ErrorPropagationInstance.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorPropagationInstance_OutErrorPropagation(), theErrorModelPackage.getErrorPropagation(),
				null, "outErrorPropagation", null, 0, 1, ErrorPropagationInstance.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorPropagationInstance_InTokens(), getTypeTokenInstance(), null, "inTokens", null, 0, -1,
				ErrorPropagationInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorPropagationInstance_OutTokens(), getTypeTokenInstance(), null, "outTokens", null, 0, -1,
				ErrorPropagationInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(errorPropagationInstanceEClass, theAadl2Package.getDirectionType(), "getDirection", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		initEClass(featurePropagationEClass, FeaturePropagation.class, "FeaturePropagation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeaturePropagation_Feature(), theInstancePackage.getFeatureInstance(), null, "feature", null,
				0, 1, FeaturePropagation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pointPropagationEClass, PointPropagation.class, "PointPropagation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPointPropagation_Point(), getPropagationPointInstance(), null, "point", null, 0, 1,
				PointPropagation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bindingPropagationEClass, BindingPropagation.class, "BindingPropagation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBindingPropagation_Binding(), getBindingType(), "binding", null, 0, 1,
				BindingPropagation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(typeTokenInstanceEClass, TypeTokenInstance.class, "TypeTokenInstance", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(typeReferenceEClass, TypeReference.class, "TypeReference", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeReference_Type(), theErrorModelPackage.getErrorType(), null, "type", null, 0, 1,
				TypeReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(typeReferenceEClass, theErrorModelPackage.getErrorType(), "getResolvedType", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEClass(typeSetInstanceEClass, TypeSetInstance.class, "TypeSetInstance", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeSetInstance_ResolvedSet(), theErrorModelPackage.getTypeSet(), null, "resolvedSet", null,
				0, 1, TypeSetInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeSetInstance_DeclaredSet(), theErrorModelPackage.getTypeSet(), null, "declaredSet", null,
				0, 1, TypeSetInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeSetInstance_Tokens(), getTypeTokenInstance(), null, "tokens", null, 0, -1,
				TypeSetInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeProductInstanceEClass, TypeProductInstance.class, "TypeProductInstance", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeProductInstance_Types(), getTypeReference(), null, "types", null, 0, -1,
				TypeProductInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(eOperationEEnum, EOperation.class, "EOperation");
		addEEnumLiteral(eOperationEEnum, EOperation.ANY);
		addEEnumLiteral(eOperationEEnum, EOperation.ALL);
		addEEnumLiteral(eOperationEEnum, EOperation.ONEOF);
		addEEnumLiteral(eOperationEEnum, EOperation.KOFN);
		addEEnumLiteral(eOperationEEnum, EOperation.KORMORE);
		addEEnumLiteral(eOperationEEnum, EOperation.KORLESS);

		initEEnum(bindingTypeEEnum, BindingType.class, "BindingType");
		addEEnumLiteral(bindingTypeEEnum, BindingType.PROCESSOR);
		addEEnumLiteral(bindingTypeEEnum, BindingType.MEMORY);
		addEEnumLiteral(bindingTypeEEnum, BindingType.CONNECTION);
		addEEnumLiteral(bindingTypeEEnum, BindingType.BINDING);
		addEEnumLiteral(bindingTypeEEnum, BindingType.BINDINGS);
		addEEnumLiteral(bindingTypeEEnum, BindingType.ACCESS);

		// Create resource
		createResource(eNS_URI);
	}

} // EMV2InstancePackageImpl