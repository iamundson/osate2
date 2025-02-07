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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.osate.aadl2.errormodel.instance.CompositeStateInstance;
import org.osate.aadl2.errormodel.instance.ConstraintElement;
import org.osate.aadl2.errormodel.instance.EMV2InstancePackage;
import org.osate.aadl2.errormodel.instance.StateInstance;
import org.osate.xtext.aadl2.errormodel.errorModel.CompositeState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite State Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.aadl2.errormodel.instance.impl.CompositeStateInstanceImpl#getTargetState <em>Target State</em>}</li>
 *   <li>{@link org.osate.aadl2.errormodel.instance.impl.CompositeStateInstanceImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.osate.aadl2.errormodel.instance.impl.CompositeStateInstanceImpl#getCompositeState <em>Composite State</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompositeStateInstanceImpl extends EMV2InstanceObjectImpl implements CompositeStateInstance {
	/**
	 * The cached value of the '{@link #getTargetState() <em>Target State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetState()
	 * @generated
	 * @ordered
	 */
	protected StateInstance targetState;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected ConstraintElement condition;

	/**
	 * The cached value of the '{@link #getCompositeState() <em>Composite State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeState()
	 * @generated
	 * @ordered
	 */
	protected CompositeState compositeState;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeStateInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EMV2InstancePackage.Literals.COMPOSITE_STATE_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StateInstance getTargetState() {
		if (targetState != null && targetState.eIsProxy()) {
			InternalEObject oldTargetState = (InternalEObject) targetState;
			targetState = (StateInstance) eResolveProxy(oldTargetState);
			if (targetState != oldTargetState) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__TARGET_STATE, oldTargetState, targetState));
				}
			}
		}
		return targetState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateInstance basicGetTargetState() {
		return targetState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetState(StateInstance newTargetState) {
		StateInstance oldTargetState = targetState;
		targetState = newTargetState;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__TARGET_STATE, oldTargetState, targetState));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConstraintElement getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(ConstraintElement newCondition, NotificationChain msgs) {
		ConstraintElement oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION, oldCondition, newCondition);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCondition(ConstraintElement newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null) {
				msgs = ((InternalEObject) condition).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION, null, msgs);
			}
			if (newCondition != null) {
				msgs = ((InternalEObject) newCondition).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION, null, msgs);
			}
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION, newCondition, newCondition));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompositeState getCompositeState() {
		if (compositeState != null && compositeState.eIsProxy()) {
			InternalEObject oldCompositeState = (InternalEObject) compositeState;
			compositeState = (CompositeState) eResolveProxy(oldCompositeState);
			if (compositeState != oldCompositeState) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__COMPOSITE_STATE, oldCompositeState,
							compositeState));
				}
			}
		}
		return compositeState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeState basicGetCompositeState() {
		return compositeState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompositeState(CompositeState newCompositeState) {
		CompositeState oldCompositeState = compositeState;
		compositeState = newCompositeState;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__COMPOSITE_STATE, oldCompositeState, compositeState));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION:
			return basicSetCondition(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__TARGET_STATE:
			if (resolve) {
				return getTargetState();
			}
			return basicGetTargetState();
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION:
			return getCondition();
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__COMPOSITE_STATE:
			if (resolve) {
				return getCompositeState();
			}
			return basicGetCompositeState();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__TARGET_STATE:
			setTargetState((StateInstance) newValue);
			return;
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION:
			setCondition((ConstraintElement) newValue);
			return;
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__COMPOSITE_STATE:
			setCompositeState((CompositeState) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__TARGET_STATE:
			setTargetState((StateInstance) null);
			return;
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION:
			setCondition((ConstraintElement) null);
			return;
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__COMPOSITE_STATE:
			setCompositeState((CompositeState) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__TARGET_STATE:
			return targetState != null;
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__CONDITION:
			return condition != null;
		case EMV2InstancePackage.COMPOSITE_STATE_INSTANCE__COMPOSITE_STATE:
			return compositeState != null;
		}
		return super.eIsSet(featureID);
	}

} // CompositeStateInstanceImpl
