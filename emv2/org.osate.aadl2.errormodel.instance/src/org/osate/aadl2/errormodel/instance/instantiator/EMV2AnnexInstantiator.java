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
package org.osate.aadl2.errormodel.instance.instantiator;

import static org.eclipse.xtext.EcoreUtil2.getContainerOfType;
import static org.osate.xtext.aadl2.errormodel.util.EMV2TypeSetUtil.isNoError;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.Feature;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.InternalFeature;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyConstant;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.TriggerPort;
import org.osate.aadl2.contrib.deployment.DeploymentProperties;
import org.osate.aadl2.errormodel.instance.AccessPropagation;
import org.osate.aadl2.errormodel.instance.AnonymousTypeSet;
import org.osate.aadl2.errormodel.instance.BindingPropagation;
import org.osate.aadl2.errormodel.instance.BindingType;
import org.osate.aadl2.errormodel.instance.CompositeStateInstance;
import org.osate.aadl2.errormodel.instance.ConnectionEndPropagation;
import org.osate.aadl2.errormodel.instance.ConstrainedInstanceObject;
import org.osate.aadl2.errormodel.instance.ConstraintElement;
import org.osate.aadl2.errormodel.instance.ConstraintExpression;
import org.osate.aadl2.errormodel.instance.EMV2AnnexInstance;
import org.osate.aadl2.errormodel.instance.EMV2InstanceFactory;
import org.osate.aadl2.errormodel.instance.EMV2InstanceObject;
import org.osate.aadl2.errormodel.instance.EOperation;
import org.osate.aadl2.errormodel.instance.ErrorDetectionInstance;
import org.osate.aadl2.errormodel.instance.ErrorPropagationConditionInstance;
import org.osate.aadl2.errormodel.instance.ErrorPropagationInstance;
import org.osate.aadl2.errormodel.instance.EventInstance;
import org.osate.aadl2.errormodel.instance.FeaturePropagation;
import org.osate.aadl2.errormodel.instance.OldPropagationPathInstance;
import org.osate.aadl2.errormodel.instance.PointPropagation;
import org.osate.aadl2.errormodel.instance.PropagationPointInstance;
import org.osate.aadl2.errormodel.instance.StateInstance;
import org.osate.aadl2.errormodel.instance.StateMachineInstance;
import org.osate.aadl2.errormodel.instance.StateTransitionInstance;
import org.osate.aadl2.errormodel.instance.TypeInstance;
import org.osate.aadl2.errormodel.instance.TypeProductInstance;
import org.osate.aadl2.errormodel.instance.TypeSetElement;
import org.osate.aadl2.errormodel.instance.TypeSetInstance;
import org.osate.aadl2.instance.AnnexInstance;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceFactory;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.PropertyAssociationInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.util.InstanceUtil;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.annexsupport.AnnexInstantiator;
import org.osate.xtext.aadl2.errormodel.errorModel.AllExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.AndExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.CompositeState;
import org.osate.xtext.aadl2.errormodel.errorModel.ConditionElement;
import org.osate.xtext.aadl2.errormodel.errorModel.ConditionExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.EMV2Path;
import org.osate.xtext.aadl2.errormodel.errorModel.EMV2PropertyAssociation;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorEvent;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorStateMachine;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorTransition;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorCodeValue;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorDetection;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorEvent;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorPath;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorPropagation;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorSink;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorSource;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorType;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorTypes;
import org.osate.xtext.aadl2.errormodel.errorModel.FeatureorPPReference;
import org.osate.xtext.aadl2.errormodel.errorModel.OrExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.OrmoreExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.OutgoingPropagationCondition;
import org.osate.xtext.aadl2.errormodel.errorModel.PropagationPath;
import org.osate.xtext.aadl2.errormodel.errorModel.PropagationPoint;
import org.osate.xtext.aadl2.errormodel.errorModel.QualifiedErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.QualifiedPropagationPoint;
import org.osate.xtext.aadl2.errormodel.errorModel.SConditionElement;
import org.osate.xtext.aadl2.errormodel.errorModel.TransitionBranch;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeSet;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeToken;
import org.osate.xtext.aadl2.errormodel.util.EMV2Properties;
import org.osate.xtext.aadl2.errormodel.util.EMV2TypeSetUtil;
import org.osate.xtext.aadl2.errormodel.util.EMV2Util;
import org.osate.xtext.aadl2.properties.util.InstanceModelUtil;

public class EMV2AnnexInstantiator implements AnnexInstantiator {
	public static final String PROPERTY_NAME = "org.osate.emv2.instance";

	@Override
	public void instantiateAnnex(ComponentInstance instance, String annexName,
			AnalysisErrorReporterManager errorManager) {
		if (!"true".equalsIgnoreCase(System.getProperty(PROPERTY_NAME))) {
			// Don't instantiate EMV2 elements unless explicitly enabled.
			return;
		}
		var soms = getContainerOfType(instance, SystemInstance.class).getSystemOperationModes();
		if (soms.size() > 1 || soms.size() == 1 && !InstanceUtil.isNoMode(soms.get(0))) {
			// TODO Remove this after we figure out how to deal with modes.
			return;
		}

		EMV2AnnexInstance emv2AI = EMV2InstanceFactory.eINSTANCE.createEMV2AnnexInstance();
		emv2AI.setName("EMV2");
		instance.getAnnexInstances().add(emv2AI);

		Collection<PropagationPoint> pps = EMV2Util.getAllPropagationPoints(instance.getComponentClassifier());
		for (PropagationPoint pp : pps) {
			instantiatePropagationPoint(pp, emv2AI);
		}

		var eps = EMV2Util.getAllErrorPropagations(instance.getComponentClassifier());
		instantiateErrorPropagations(eps, emv2AI);

//		Collection<ErrorBehaviorEvent> events = EMV2Util.getAllErrorBehaviorEvents(instance);
//		for (ErrorBehaviorEvent ev : events) {
//			instantiateEvent(ev, emv2AI);
//		}

		for (var source : EMV2Util.getAllErrorSources(instance)) {
			instantiateErrorSource(source, emv2AI);
		}

		for (var sink : EMV2Util.getAllErrorSinks(instance)) {
			instantiateErrorSink(sink, emv2AI);
		}

		for (var path : EMV2Util.getAllErrorPaths(instance)) {
			instantiateErrorPath(path, emv2AI);
		}

//		ErrorBehaviorStateMachine ebsm = EMV2Util.getAllErrorBehaviorStateMachine(instance);
//		if (ebsm != null) {
//			instantiateStateMachine(ebsm, emv2AI);
//		}
//
//		Collection<ErrorBehaviorTransition> transitions = EMV2Util.getAllErrorBehaviorTransitions(instance);
//		for (ErrorBehaviorTransition tr : transitions) {
//			instantiateStateTransition(tr, emv2AI);
//		}
//
//		Collection<CompositeState> compstates = EMV2Util.getAllCompositeStates(instance);
//		for (CompositeState cs : compstates) {
//			instantiateCompositeState(cs, emv2AI);
//		}
//
//		Collection<OutgoingPropagationCondition> OPCs = EMV2Util.getAllOutgoingPropagationConditions(instance);
//		for (OutgoingPropagationCondition opc : OPCs) {
//			instantiateOutgoingPropagationCondition(opc, emv2AI);
//		}
//
//		Collection<ErrorDetection> eds = EMV2Util.getAllErrorDetections(instance.getComponentClassifier());
//		for (ErrorDetection ed : eds) {
//			instantiateErrorDetection(ed, emv2AI);
//		}
//
//		Collection<ConnectionInstance> connis = instance.getConnectionInstances();
//		for (ConnectionInstance conni : connis) {
//			instantiateConnectionPropagationPaths(conni, emv2AI);
//		}

		Collection<PropagationPath> ppaths = EMV2Util.getAllPropagationPaths(instance.getComponentClassifier());
		for (PropagationPath ppath : ppaths) {
			instantiateUserDefinedPath(ppath, emv2AI, instance);
		}

//		// for bindings we need to first process all components EMV2 instantiations since the binding property instance
//		// is attached to the component being bound and points to the resource.
//		// During the depth first traversal the resource component may not have its EMV2 instantiated yet, thus, we cannot create the binding propagation path
//		// instance.
//
//		if (instance instanceof SystemInstance) {
//			for (ComponentInstance ci : EcoreUtil2.eAllOfType(instance, ComponentInstance.class)) {
//				instantiateBindingPaths(ci, emv2AI);
//			}
//		}
	}

	@Override
	public void instantiateAnnex(SystemInstance instance, String annexName, AnalysisErrorReporterManager errorManager) {
		if (!"true".equalsIgnoreCase(System.getProperty(PROPERTY_NAME))) {
			// Don't instantiate EMV2 elements unless explicitly enabled.
			return;
		}
		var soms = instance.getSystemOperationModes();
		if (soms.size() > 1 || soms.size() == 1 && !InstanceUtil.isNoMode(soms.get(0))) {
			// TODO Remove this after we figure out how to deal with modes.
			return;
		}
		EcoreUtil2.eAllOfType(instance, ComponentInstance.class).forEach(component -> {
			component.getConnectionInstances().forEach(connection -> instantiateConnectionPath(connection, component));
		});
		instantiateBindingPaths(instance);
	}

	private void instantiateConnectionPath(ConnectionInstance connection, ComponentInstance component) {
		if (connection.isComplete()) {
			var sourcePropagations = new ArrayList<ConnectionEndPropagation>();
			var destinationPropagations = new ArrayDeque<ConnectionEndPropagation>();
			var encounteredAcross = false;
			for (var ref : connection.getConnectionReferences()) {
				if (!encounteredAcross) {
					ConnectionEndPropagation propagation;
					if (ref.getSource() instanceof FeatureInstance source) {
						propagation = findFeaturePropagation(source);
					} else if (ref.getSource() instanceof ComponentInstance source) {
						propagation = findAccessPropagation(source);
					} else {
						throw new RuntimeException("Unexpected connection end: " + ref.getSource());
					}
					if (propagation != null && propagation.getDirection().outgoing()) {
						sourcePropagations.add(propagation);
					}
				}
				if (ref.getConnection().isAcross()) {
					encounteredAcross = true;
				}
				if (encounteredAcross) {
					ConnectionEndPropagation propagation;
					if (ref.getDestination() instanceof FeatureInstance destination) {
						propagation = findFeaturePropagation(destination);
					} else if (ref.getDestination() instanceof ComponentInstance destination) {
						propagation = findAccessPropagation(destination);
					} else {
						throw new RuntimeException("Unexpected connection end: " + ref.getDestination());
					}
					if (propagation != null && propagation.getDirection().incoming()) {
						destinationPropagations.addFirst(propagation);
					}
				}
			}
			if (!sourcePropagations.isEmpty() && !destinationPropagations.isEmpty()) {
				var connectionPath = EMV2InstanceFactory.eINSTANCE.createConnectionPath();
				connectionPath.setName(connection.getName());
				connectionPath.setConnection(connection);
				connectionPath.getSourcePropagations().addAll(sourcePropagations);
				connectionPath.getDestinationPropagations().addAll(destinationPropagations);
				getOrCreateEMV2AnnexInstance(component).getPropagationPaths().add(connectionPath);
			}
		}
	}

	/*
	 * The instantiation of BindingPath objects is a bit complicated. The idea is that there should be one BindingPath
	 * object per declarative PropertyAssociation for that binding. However, a single declarative PropertyAssociation
	 * can be instantiated into multiple PropertyAssociationInstance objects if the property is inherit and it is
	 * applied to a container. This method collects all of the values from every PropertyAssociationInstance and groups
	 * them by their singular declarative PropertyAssociation.
	 *
	 * For example, suppose that an Actual_Processor_Binding is applied to a process and that process contains multiple
	 * threads. Now also suppose that the process and all of the threads have in processor propagations and the
	 * processor that is the target of the binding has an out bindings propagation. This will result in one
	 * BindingPath object being created in which the source is the bindings propagation on the processor and the
	 * destination propagations are all of the processor propagations on the process and threads.
	 *
	 * The collection of all bindings for a given type must not only be grouped by a common singular declarative
	 * PropertyAssociation, but also by their instance property values. There are some situations in which a single
	 * declarative binding PropertyAssociation should result in multiple BindingPath objects. If a declarative binding
	 * PropertyAssociation is not at the top system, but is instead in a child component, and the classifier of that
	 * child component is used in multiple subcomponents, then the InstanceReferenceValue objects would be different for
	 * the different subtrees of the instance model and we would want to create multiple BindingPath objects. The record
	 * UniqueBindingKey fulfills this requirement by allowing a grouping by declarative PropertyAssociation and the
	 * instance value objects.
	 *
	 * In addition to grouping by inherited properties, a BindingPath can also have a list of sources or list of
	 * destinations if the binding property lists multiple targets. For example, if a thread is bound to three
	 * processors, then a BindingPath will be created which lists the propagations for all three processors. Memory
	 * binding works the same way, but connection binding is different. For connection binding, the list represents a
	 * sequence of components that data flows through. Therefore, a BindingPath is created from the out connection
	 * propagation on the binding source to the in bindings propagation on the first binding target and another
	 * BindingPath is created from the out bindings propagation on the last binding target to the in connection
	 * propagation on the binding source.
	 */
	private void instantiateBindingPaths(SystemInstance instance) {
		// Key has the binding targets and the values are the binding sources.
		var commonProcessorBindings = new LinkedHashMap<UniqueBindingKey, List<ComponentInstance>>();
		var commonMemoryBindings = new LinkedHashMap<UniqueBindingKey, List<ComponentInstance>>();
		var commonConnectionBindings = new LinkedHashMap<UniqueBindingKey, List<ComponentInstance>>();
		EcoreUtil2.eAllOfType(instance, ComponentInstance.class).forEach(source -> {
			collectCommonBindings(source, commonProcessorBindings, DeploymentProperties::getActualProcessorBinding,
					DeploymentProperties::getActualProcessorBinding_EObject);
			collectCommonBindings(source, commonMemoryBindings, DeploymentProperties::getActualMemoryBinding,
					DeploymentProperties::getActualMemoryBinding_EObject);
			collectCommonBindings(source, commonConnectionBindings, DeploymentProperties::getActualConnectionBinding,
					DeploymentProperties::getActualConnectionBinding_EObject);
		});
		commonProcessorBindings.forEach((key, sources) -> {
			var sourcePropagations = sources.stream()
					.map(source -> findBindingPropagation(source, BindingType.PROCESSOR))
					.filter(Objects::nonNull)
					.toList();
			var targetPropagations = key.bindingTargets()
					.stream()
					.map(target -> findBindingPropagation(target, BindingType.BINDINGS))
					.filter(Objects::nonNull)
					.toList();
			instantiateBindingPath(sourcePropagations, targetPropagations, BindingType.PROCESSOR);
			instantiateBindingPath(targetPropagations, sourcePropagations, BindingType.PROCESSOR);
		});
		commonMemoryBindings.forEach((key, sources) -> {
			var sourcePropagations = sources.stream()
					.map(source -> findBindingPropagation(source, BindingType.MEMORY))
					.filter(Objects::nonNull)
					.toList();
			var targetPropagations = key.bindingTargets()
					.stream()
					.map(target -> findBindingPropagation(target, BindingType.BINDINGS))
					.filter(Objects::nonNull)
					.toList();
			instantiateBindingPath(sourcePropagations, targetPropagations, BindingType.MEMORY);
			instantiateBindingPath(targetPropagations, sourcePropagations, BindingType.MEMORY);
		});
		commonConnectionBindings.forEach((key, sources) -> {
			var sourcePropagations = sources.stream()
					.map(source -> findBindingPropagation(source, BindingType.CONNECTION))
					.filter(Objects::nonNull)
					.toList();
			var targets = key.bindingTargets();
			var firstTargetPropagation = findBindingPropagation(targets.get(0), BindingType.BINDINGS);
			BindingPropagation lastTargetPropagation;
			if (targets.size() == 1) {
				lastTargetPropagation = firstTargetPropagation;
			} else {
				lastTargetPropagation = findBindingPropagation(targets.get(targets.size() - 1), BindingType.BINDINGS);
			}
			if (firstTargetPropagation != null) {
				instantiateBindingPath(sourcePropagations, List.of(firstTargetPropagation), BindingType.CONNECTION);
			}
			if (lastTargetPropagation != null) {
				instantiateBindingPath(List.of(lastTargetPropagation), sourcePropagations, BindingType.CONNECTION);
			}
		});
	}

	private record UniqueBindingKey(PropertyAssociation propertyAssociation, List<ComponentInstance> bindingTargets) {
	}

	private static void collectCommonBindings(ComponentInstance source,
			Map<UniqueBindingKey, List<ComponentInstance>> commonBindings,
			Function<ComponentInstance, Optional<List<InstanceObject>>> getProperty,
			Function<ComponentInstance, PropertyExpression> getExpression) {
		getProperty.apply(source).ifPresent(targets -> {
			var targetComponents = targets.stream()
					.filter(ComponentInstance.class::isInstance)
					.map(ComponentInstance.class::cast)
					.toList();
			if (!targetComponents.isEmpty()) {
				var expression = getExpression.apply(source);
				var instanceAssociation = getContainerOfType(expression, PropertyAssociationInstance.class);
				var key = new UniqueBindingKey(instanceAssociation.getPropertyAssociation(), targetComponents);
				commonBindings.computeIfAbsent(key, k -> new ArrayList<>()).add(source);
			}
		});
	}

	private void instantiateBindingPath(List<BindingPropagation> sourcePropagations,
			List<BindingPropagation> destinationPropagations, BindingType bindingType) {
		var outgoingSources = sourcePropagations.stream()
				.filter(propagation -> propagation.getDirection().outgoing())
				.toList();
		var incomingDestinations = destinationPropagations.stream()
				.filter(propagation -> propagation.getDirection().incoming())
				.toList();
		if (!outgoingSources.isEmpty() && !incomingDestinations.isEmpty()) {
			var commonContainer = Stream.concat(outgoingSources.stream(), incomingDestinations.stream())
					.map(propagation -> getContainerOfType(propagation, ComponentInstance.class))
					.reduce(EMV2AnnexInstantiator::getCommonContainer)
					.get();
			var substringIndex = commonContainer.getInstanceObjectPath().length() + 1;
			var sourcePaths = outgoingSources.stream()
					.map(propagation -> propagation.getInstanceObjectPath().substring(substringIndex))
					.collect(Collectors.joining(", "));
			if (outgoingSources.size() > 1) {
				sourcePaths = '(' + sourcePaths + ')';
			}
			var destinationPaths = incomingDestinations.stream()
					.map(propagation -> propagation.getInstanceObjectPath().substring(substringIndex))
					.collect(Collectors.joining(", "));
			if (incomingDestinations.size() > 1) {
				destinationPaths = '(' + destinationPaths + ')';
			}
			var bindingPath = EMV2InstanceFactory.eINSTANCE.createBindingPath();
			bindingPath.setName(sourcePaths + " -> " + destinationPaths);
			bindingPath.setType(bindingType);
			bindingPath.getSourcePropagations().addAll(sourcePropagations);
			bindingPath.getDestinationPropagations().addAll(destinationPropagations);
			getOrCreateEMV2AnnexInstance(commonContainer).getPropagationPaths().add(bindingPath);
		}
	}

	private static ComponentInstance getCommonContainer(ComponentInstance a, ComponentInstance b) {
		if (EcoreUtil.isAncestor(a, b)) {
			return a;
		} else {
			for (var container : EcoreUtil2.getAllContainers(a)) {
				if (container instanceof ComponentInstance containerComponent
						&& EcoreUtil.isAncestor(containerComponent, b)) {
					return containerComponent;
				}
			}
			return null;
		}
	}

	private EMV2AnnexInstance getOrCreateEMV2AnnexInstance(ComponentInstance component) {
		var annex = findEMV2AnnexInstance(component);
		if (annex == null) {
			annex = EMV2InstanceFactory.eINSTANCE.createEMV2AnnexInstance();
			annex.setName("EMV2");
			component.getAnnexInstances().add(annex);
		}
		return annex;
	}

	private void instantiatePropagationPoint(PropagationPoint g, EMV2AnnexInstance annex) {
		PropagationPointInstance gi = EMV2InstanceFactory.eINSTANCE.createPropagationPointInstance();
		gi.setName(g.getName());
		gi.setPropagationPoint(g);
		annex.getPropagationPoints().add(gi);
	}

	private void instantiateEvent(ErrorBehaviorEvent g, EMV2AnnexInstance annex) {
		ComponentInstance ci = (ComponentInstance) annex.eContainer();
		EventInstance gi = createEventInstance(g);
		annex.getEvents().add(gi);
		if (g instanceof ErrorEvent) {
			 TypeSet ts = ((ErrorEvent)g).getTypeSet();
			if (ts != null) {
				for (TypeToken tt : ts.getTypeTokens()) {
					ConstrainedInstanceObject cio = createConstrainedInstanceObject(gi, tt);
					gi.getGeneratedTypedEvents().add(cio);
					instantiatePropertyAssociations(cio, ci, g, tt);
				}
			} else {
				instantiatePropertyAssociations(gi, ci, g, null);
			}
		}
	}

	private EventInstance createEventInstance(ErrorBehaviorEvent g) {
		EventInstance gi = EMV2InstanceFactory.eINSTANCE.createEventInstance();
		gi.setName(g.getName());
		gi.setEvent(g);
		return gi;
	}

	private ConstrainedInstanceObject createConstrainedInstanceObject(EventInstance context, TypeToken token) {
		ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE.createConstrainedInstanceObject();
		cio.setInstanceObject(context);
		if (!token.getType().isEmpty()) {
			cio.setName(context.getName() + ":" + token.toString());
			cio.getConstraint().add(EcoreUtil.copy(token));
		} else {
			cio.setName(context.getName());
		}
		return cio;
	}

	private void instantiateStateMachine(ErrorBehaviorStateMachine ebsm, EMV2AnnexInstance annex) {
		ComponentInstance ci = (ComponentInstance) annex.eContainer();
		StateMachineInstance svi = EMV2InstanceFactory.eINSTANCE.createStateMachineInstance();
		annex.setStateMachine(svi);
		StateInstance initState = null;
		for (ErrorBehaviorState st : ebsm.getStates()) {
			StateInstance istate = createStateInstance(st);
			if (st.isIntial()) {
				initState = istate;
			}
			svi.getStates().add(istate);
			// add property associations
			instantiatePropertyAssociations(istate, ci, st, null);
		}
		svi.setCurrentState(initState);
	}

	private StateInstance createStateInstance(ErrorBehaviorState ss) {
		StateInstance si = EMV2InstanceFactory.eINSTANCE.createStateInstance();
		si.setName(ss.getName());
		si.setState(ss);
		return si;
	}

	private void instantiateStateTransition(ErrorBehaviorTransition st, EMV2AnnexInstance annex) {
		StateMachineInstance smi = annex.getStateMachine();
		if (smi == null) {
			return ;
		}
		if (st.getDestinationBranches().isEmpty()) {
			instantiateStateTransition(st, null, annex);
		} else {
			for (TransitionBranch br : st.getDestinationBranches()) {
				instantiateStateTransition(st, br, annex);
			}
		}
	}

	private void instantiateStateTransition(ErrorBehaviorTransition st, TransitionBranch tb,
			EMV2AnnexInstance annex) {
		StateMachineInstance smi = annex.getStateMachine();
		StateTransitionInstance sti = EMV2InstanceFactory.eINSTANCE.createStateTransitionInstance();
		sti.setName(st.getName());
		if (tb != null) {
			sti.setStateTransition(tb);
		} else {
			sti.setStateTransition(st);
		}
		annex.getTransitions().add(sti);
		ConditionExpression behaviorCondition = st.getCondition();
		ConstraintElement cio = instantiateCondition(behaviorCondition, annex);
		sti.setCondition(cio);
		boolean isSteadyState = tb != null ? tb.isSteadyState() : st.isSteadyState();
		ErrorBehaviorState target = tb != null ? tb.getTarget() : st.getTarget();
		if (isSteadyState) {
			if (st.isAllStates()) {
				annex.getTransitions().remove(sti);
				for (StateInstance si : smi.getStates()) {
					StateTransitionInstance nsti = EcoreUtil.copy(sti);
					nsti.getInStates().add(si);
					nsti.setTargetState(si);
				}
			} else {
				StateInstance ssti = findStateInstance(annex, st.getSource());
				if (ssti != null) {
					sti.getInStates().add(ssti);
					sti.setTargetState(ssti);
				}
			}
		} else {
			// explicit target state
			sti.setTargetState(findStateInstance(annex, target));
			if (st.isAllStates()) {
				for (StateInstance si : smi.getStates()) {
					sti.getInStates().add(si);
				}
			} else {
				StateInstance si = findStateInstance(annex, st.getSource());
				if (si != null) {
					sti.getInStates().add(si);
				}
			}
		}
	}

	private void instantiateCompositeState(CompositeState st, EMV2AnnexInstance annex) {
		CompositeStateInstance sti = EMV2InstanceFactory.eINSTANCE.createCompositeStateInstance();
		sti.setName(st.getName());
		sti.setCompositeState(st);
		annex.getComposites().add(sti);
		ConditionExpression behaviorCondition = st.getCondition();
		ConstraintElement cio = instantiateCondition(behaviorCondition, annex);
		sti.setCondition(cio);
		// explicit target state
		sti.setTargetState(findStateInstance(annex, st.getState()));
	}

	private void instantiateErrorPropagations(List<ErrorPropagation> eps, EMV2AnnexInstance annex) {
		var propagationInstances = new TreeMap<String, ErrorPropagationInstance>(String.CASE_INSENSITIVE_ORDER);
		for (var ep : eps) {
			var epi = propagationInstances.computeIfAbsent(EMV2Util.getName(ep),
					name -> createErrorPropagationInstance(annex, name, ep));
			if (epi == null) {
				// This can happen if the propagation points to an InternalFeature. In that case, simply skip this one.
				break;
			}
			switch (ep.getDirection()) {
			case IN:
				assert epi.getInErrorPropagation() == null && epi.getInTypeSet() == null : "In fields are already set.";
				epi.setInErrorPropagation(ep);
				epi.setInTypeSet(createAnonymousTypeSet(ep.getTypeSet()));
				break;
			case OUT:
				assert epi.getOutErrorPropagation() == null && epi.getOutTypeSet() == null
						: "Out fields are already set.";
				epi.setOutErrorPropagation(ep);
				epi.setOutTypeSet(createAnonymousTypeSet(ep.getTypeSet()));
				break;
			case IN_OUT:
				throw new RuntimeException(
						"Propagation has an in out direction which is not supported by the grammar: " + ep);
			}
		}
	}

	private ErrorPropagationInstance createErrorPropagationInstance(EMV2AnnexInstance annex, String name,
			ErrorPropagation ep) {
		ErrorPropagationInstance propagation;
		if ("access".equalsIgnoreCase(ep.getKind())) {
			propagation = createAccessPropagation(name);
		} else if (ep.getKind() != null) {
			propagation = createBindingPropagation(name, ep.getKind());
		} else if (ep.getFeatureorPPRef() != null) {
			var featureOrPPRef = ep.getFeatureorPPRef();
			var featureOrPP = featureOrPPRef.getFeatureorPP();
			if (featureOrPP instanceof Feature) {
				propagation = createFeaturePropagation(annex, name, featureOrPPRef);
			} else if (featureOrPP instanceof PropagationPoint point) {
				propagation = createPointPropagation(annex, name, point);
			} else if (featureOrPP instanceof InternalFeature) {
				// Propagation not instantiated since InternalFeatures are not instantiated.
				return null;
			} else {
				throw new RuntimeException(
						"featureorPPRef points to something other than a Feature, an InternalFeature, or a PropagationPoint: "
								+ featureOrPP);
			}
		} else {
			throw new RuntimeException("Both kind and featureOrPPRef are null: " + ep);
		}
		annex.getPropagations().add(propagation);
		return propagation;
	}

	private FeaturePropagation createFeaturePropagation(EMV2AnnexInstance annex, String name,
			FeatureorPPReference featureReference) {
		var propagation = EMV2InstanceFactory.eINSTANCE.createFeaturePropagation();
		propagation.setName(name);
		propagation
				.setFeature(findFeatureInstance(getContainerOfType(annex, ComponentInstance.class), featureReference));
		return propagation;
	}

	private PointPropagation createPointPropagation(EMV2AnnexInstance annex, String name,
			PropagationPoint propagationPoint) {
		var propagation = EMV2InstanceFactory.eINSTANCE.createPointPropagation();
		propagation.setName(name);
		propagation.setPoint(findPropagationPointInstance(annex, propagationPoint));
		return propagation;
	}

	private AccessPropagation createAccessPropagation(String name) {
		var propagation = EMV2InstanceFactory.eINSTANCE.createAccessPropagation();
		propagation.setName(name);
		return propagation;
	}

	private BindingPropagation createBindingPropagation(String name, String kind) {
		var propagation = EMV2InstanceFactory.eINSTANCE.createBindingPropagation();
		propagation.setName(name);
		propagation.setBinding(BindingType.get(kind.toLowerCase()));
		return propagation;
	}

	private AnonymousTypeSet createAnonymousTypeSet(TypeSet set) {
		var anonymousTypeSet = EMV2InstanceFactory.eINSTANCE.createAnonymousTypeSet();
		anonymousTypeSet.getElements().addAll(createTypeSetElements(set.getTypeTokens(), 0));
		anonymousTypeSet.setName(anonymousTypeSet.getElements()
				.stream()
				.map(NamedElement::getName)
				.collect(Collectors.joining(", ", "{", "}")));
		return anonymousTypeSet;
	}

	private List<TypeSetElement> createTypeSetElements(List<TypeToken> tokens, int depth) {
		var results = new ArrayList<TypeSetElement>();
		for (var token : tokens) {
			if (token.getType().size() == 1) {
				var element = token.getType().get(0);
				if (element instanceof ErrorType type) {
					results.add(createTypeInstance(type));
				} else if (element instanceof TypeSet set) {
					results.add(createTypeSetInstance(set, depth));
				} else {
					throw new RuntimeException("element is something other than an ErrorType or a TypeSet: " + element);
				}
			} else {
				results.add(createTypeProductInstance(token));
			}
		}
		return results;
	}

	private TypeInstance createTypeInstance(ErrorType type) {
		var typeInstance = EMV2InstanceFactory.eINSTANCE.createTypeInstance();
		typeInstance.setName(type.getName());
		typeInstance.setType(type);
		return typeInstance;
	}

	private TypeSetInstance createTypeSetInstance(TypeSet set, int depth) {
		var typeSetInstance = EMV2InstanceFactory.eINSTANCE.createTypeSetInstance();
		typeSetInstance.setName(set.getName());
		typeSetInstance.setTypeSet(set);
		if (depth > 50) {
			// TODO Add error marker stating that there is a cycle.
		} else {
			typeSetInstance.getElements()
					.addAll(createTypeSetElements(EMV2Util.resolveAlias(set).getTypeTokens(), depth + 1));
		}
		return typeSetInstance;
	}

	private TypeProductInstance createTypeProductInstance(TypeToken token) {
		var product = EMV2InstanceFactory.eINSTANCE.createTypeProductInstance();
		product.setName(token.getType().stream().map(NamedElement::getName).collect(Collectors.joining(" * ")));
		for (var element : token.getType()) {
			if (element instanceof ErrorType type) {
				product.getTypes().add(createTypeInstance(type));
			}
			// TODO Add error marker to instance model if element is a type set.
		}
		return product;
	}

	private void instantiateErrorSource(ErrorSource source, EMV2AnnexInstance annex) {
		if (source.isAll()) {
			// TODO Instantiate 'all' error sources after we figure out what 'all' means.
			return;
		}
		var propagation = (ErrorPropagation) source.getSourceModelElement();
		if (propagation.getFeatureorPPRef() != null
				&& propagation.getFeatureorPPRef().getFeatureorPP() instanceof InternalFeature) {
			// Error source not instantiated since propagations that refer to internal features are not instantiated.
			return;
		}
		var sourceInstance = EMV2InstanceFactory.eINSTANCE.createErrorSourceInstance();
		sourceInstance.setName(source.getName());
		sourceInstance.setErrorSource(source);
		sourceInstance.setPropagation(findErrorPropagationInstance(annex, propagation));
		var typeSet = source.getTypeTokenConstraint();
		if (typeSet == null) {
			typeSet = propagation.getTypeSet();
		}
		sourceInstance.setTypeSet(createAnonymousTypeSet(typeSet));
		annex.getErrorFlows().add(sourceInstance);
	}

	private void instantiateErrorSink(ErrorSink sink, EMV2AnnexInstance annex) {
		if (sink.isAllIncoming()) {
			// TODO Instantiate 'all' error sinks after we figure out what 'all' means.
			return;
		}
		var propagation = sink.getIncoming();
		if (propagation.getFeatureorPPRef() != null
				&& propagation.getFeatureorPPRef().getFeatureorPP() instanceof InternalFeature) {
			// Error sink not instantiated since propagations that refer to internal features are not instantiated.
			return;
		}
		var sinkInstance = EMV2InstanceFactory.eINSTANCE.createErrorSinkInstance();
		sinkInstance.setName(sink.getName());
		sinkInstance.setErrorSink(sink);
		sinkInstance.setPropagation(findErrorPropagationInstance(annex, propagation));
		var typeSet = sink.getTypeTokenConstraint();
		if (typeSet == null) {
			typeSet = propagation.getTypeSet();
		}
		sinkInstance.setTypeSet(createAnonymousTypeSet(typeSet));
		annex.getErrorFlows().add(sinkInstance);
	}

	private void instantiateErrorPath(ErrorPath path, EMV2AnnexInstance annex) {
		if (path.isAllIncoming() || path.isAllOutgoing()) {
			// TODO Instantiate 'all' error paths after we figure out what 'all' means.
			return;
		}
		if (path.getTargetToken() == null) {
			// TODO Instantiate after we figure out what it means when the target token is omitted.
			return;
		}
		var sourcePropagation = path.getIncoming();
		if (sourcePropagation.getFeatureorPPRef() != null
				&& sourcePropagation.getFeatureorPPRef().getFeatureorPP() instanceof InternalFeature) {
			// Error path not instantiated since propagations that refer to internal features are not instantiated.
			return;
		}
		var destinationPropagation = path.getOutgoing();
		if (destinationPropagation.getFeatureorPPRef() != null
				&& destinationPropagation.getFeatureorPPRef().getFeatureorPP() instanceof InternalFeature) {
			// Error path not instantiated since propagations that refer to internal features are not instantiated.
			return;
		}
		var pathInstance = EMV2InstanceFactory.eINSTANCE.createErrorPathInstance();
		pathInstance.setName(path.getName());
		pathInstance.setErrorPath(path);
		pathInstance.setSourcePropagation(findErrorPropagationInstance(annex, sourcePropagation));
		pathInstance.setDestinationPropagation(findErrorPropagationInstance(annex, destinationPropagation));
		var sourceTypeSet = path.getTypeTokenConstraint();
		if (sourceTypeSet == null) {
			sourceTypeSet = sourcePropagation.getTypeSet();
		}
		pathInstance.setSourceTypeSet(createAnonymousTypeSet(sourceTypeSet));
		pathInstance.setDestinationTypeSet(createAnonymousTypeSet(path.getTargetToken()));
		annex.getErrorFlows().add(pathInstance);
	}

	/**
	 *
	 * @param ep
	 * @param ts type constraint from flow. Can be null. If null then use type set from error propagation
	 * @param eai
	 * @return
	 */
	private ConstrainedInstanceObject createErrorPropagationCIO(ErrorPropagation ep, TypeSet ts,
			EMV2AnnexInstance eai) {
		ComponentInstance ci = (ComponentInstance) eai.eContainer();
		ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE.createConstrainedInstanceObject();
		FeatureorPPReference fppref = ep.getFeatureorPPRef();
		if (fppref != null) {
			NamedElement fpp = fppref.getFeatureorPP();
			if (fpp instanceof Feature) {
				FeatureInstance fi = findFeatureInstance(ci, fppref);
				cio.setInstanceObject(fi);
				cio.setName(fi.getName());
			} else if (fpp instanceof PropagationPoint) {
				PropagationPointInstance ppi = findPropagationPointInstance(eai, (PropagationPoint) fpp);
				cio.setInstanceObject(ppi);
				cio.setName(ppi.getName());
			}
		} else if (ep.getKind() != null) {
			cio.setInstanceObject(ci);
			cio.setPropagationKind(ep.getKind());
		}
		TypeSet outts = (ts == null) ? ep.getTypeSet() : ts;
		if (outts != null) {
			for (TypeToken tt : outts.getTypeTokens()) {
				cio.getConstraint().add(EcoreUtil.copy(tt));
			}
		}
		return cio;
	}

	private void instantiateOutgoingPropagationCondition(OutgoingPropagationCondition opc, EMV2AnnexInstance annex) {
		ErrorPropagationConditionInstance bi = EMV2InstanceFactory.eINSTANCE.createErrorPropagationConditionInstance();
		bi.setName(opc.getName());
		bi.setEmv2Element(opc);
		ConditionExpression behaviorCondition = opc.getCondition();
		ConstraintElement cio = instantiateCondition(behaviorCondition, annex);
		bi.setCondition(cio);
		// explicit target state
		if (opc.isAllStates()) {
			StateMachineInstance smi = annex.getStateMachine();
			if (smi != null) {
				for (StateInstance si : smi.getStates()) {
					bi.getInStates().add(si);
				}
			}
		} else {
			bi.getInStates().add(findStateInstance(annex, opc.getState()));
		}
		if (opc.isAllPropagations()) {
			Collection<ErrorPropagation> outeps = EMV2Util
					.getAllOutgoingErrorPropagations(((ComponentInstance) annex.eContainer()).getComponentClassifier());
			for (ErrorPropagation outep : outeps) {
				ErrorPropagationConditionInstance bicopy = EcoreUtil.copy(bi);
				ConstrainedInstanceObject outcio = createErrorPropagationCIO(outep, opc.getTypeToken(), annex);
				bicopy.setOutgoingPropagation(outcio);
				annex.getErrorPropagationConditions().add(bicopy);
			}
		} else {
			ErrorPropagation outep = opc.getOutgoing();
			ConstrainedInstanceObject outcio = createErrorPropagationCIO(outep, opc.getTypeToken(), annex);
			bi.setOutgoingPropagation(outcio);
			annex.getErrorPropagationConditions().add(bi);
		}
	}

	private void instantiateErrorDetection(ErrorDetection ed, EMV2AnnexInstance annex) {
		ErrorDetectionInstance bi = EMV2InstanceFactory.eINSTANCE.createErrorDetectionInstance();
		bi.setName(ed.getName());
		bi.setEmv2Element(ed);
		annex.getErrorDetections().add(bi);
		ConditionExpression behaviorCondition = ed.getCondition();
		ConstraintElement cio = instantiateCondition(behaviorCondition, annex);
		bi.setCondition(cio);
		// explicit target state
		if (ed.isAllStates()) {
			StateMachineInstance smi = annex.getStateMachine();
			if (smi != null) {
				for (StateInstance si : smi.getStates()) {
					bi.getInStates().add(si);
				}
			}
		} else {
			bi.getInStates().add(findStateInstance(annex, ed.getState()));
		}
		// action. We keep shared action instances such that there is only one per type
		TriggerPort tp = (TriggerPort) ed.getDetectionReportingPort().getElement();
		ComponentInstance component = (ComponentInstance) annex.eContainer();
		if (tp instanceof Feature) {
			bi.setPort(component.findFeatureInstance((Feature) tp));
		} else {
			// internal feature not instantiated in core model
		}
		ErrorCodeValue ec = ed.getErrorCode();
		if (!ec.getIntValue().isEmpty()) {
			bi.setErrorCode(ec.getIntValue());
		} else if (!ec.getEnumLiteral().isEmpty()) {
			bi.setErrorCode(ec.getEnumLiteral());
		} else if (ec.getConstant() != null) {
			PropertyConstant pc = ec.getConstant();
			PropertyExpression val = pc.getConstantValue();
			if (val instanceof IntegerLiteral) {
				bi.setErrorCode(String.valueOf(((IntegerLiteral) val).getValue()));
			} else if (val instanceof StringLiteral) {
				bi.setErrorCode(((StringLiteral) val).getValue());
			}
		}
	}

	private StateInstance findStateInstance(EMV2AnnexInstance annex, ErrorBehaviorState state) {
		StateMachineInstance svi = annex.getStateMachine();
		if (svi != null) {
			return findStateInstance(svi,state);
		}
		return null;
	}

	private StateInstance findStateInstance(StateMachineInstance svi, ErrorBehaviorState state) {
		for (StateInstance si : svi.getStates()) {
			if (si.getName().equals(state.getName())) {
				return si;
			}
		}
		return null;
	}

	private ConstraintElement instantiateCondition(ConditionExpression condition, EMV2AnnexInstance annex) {

		// Mapping of AND expression
		if (condition instanceof AndExpression) {
			AndExpression expression = (AndExpression) condition;
			ConstraintExpression andExpr = EMV2InstanceFactory.eINSTANCE.createConstraintExpression();
			andExpr.setOperator(EOperation.ALL);
			for (ConditionExpression ce : expression.getOperands()) {
				ConstraintElement res = instantiateCondition(ce, annex);
				if (res != null) {
					andExpr.getConstraintElements().add(res);
				}
			}

			return andExpr;
		}

		// Mapping of All expression
		if (condition instanceof AllExpression) {
			AllExpression allCondition = (AllExpression) condition;
			if (allCondition.getCount() == 0) {
				ConstraintExpression allExpr = EMV2InstanceFactory.eINSTANCE.createConstraintExpression();
				allExpr.setOperator(EOperation.ALL);
				for (ConditionExpression ce : allCondition.getOperands()) {
					ConstraintElement res = instantiateCondition(ce, annex);
					if (res != null) {
						allExpr.getConstraintElements().add(res);
					}
				}
				return allExpr;
			}
			return null;
		}

		// Mapping of OR expression
		if (condition instanceof OrExpression) {
			OrExpression orExpression = (OrExpression) condition;
			ConstraintExpression allExpr = EMV2InstanceFactory.eINSTANCE.createConstraintExpression();
			allExpr.setOperator(EOperation.ONEOF);
			for (ConditionExpression ce : orExpression.getOperands()) {
				ConstraintElement res = instantiateCondition(ce, annex);
				if (res != null) {
					allExpr.getConstraintElements().add(res);
				}
			}
			return allExpr;
		}

		// Mapping of ORMORE expression
		if (condition instanceof OrmoreExpression) {
			OrmoreExpression omCondition = (OrmoreExpression) condition;

			if (omCondition.getCount() == 1) {
				/* 1 ormore is mapped to a OR gate */
				ConstraintExpression allExpr = EMV2InstanceFactory.eINSTANCE.createConstraintExpression();
				allExpr.setOperator(EOperation.ANY);
				for (ConditionExpression ce : omCondition.getOperands()) {
					ConstraintElement res = instantiateCondition(ce, annex);
					if (res != null) {
						allExpr.getConstraintElements().add(res);
					}
				}
				return allExpr;
			} else {
				/* x ormore with x > 1 is mapped to a ORMORE gate */
				ConstraintExpression omExpr = EMV2InstanceFactory.eINSTANCE.createConstraintExpression();
				omExpr.setOperator(EOperation.KORMORE);
				omExpr.setK(omCondition.getCount());
				for (ConditionExpression ce : omCondition.getOperands()) {
					ConstraintElement res = instantiateCondition(ce, annex);
					if (res != null) {
						omExpr.getConstraintElements().add(res);
					}
				}
				return omExpr;
			}
		}

		// Mapping of single condition element
		if (condition instanceof ConditionElement) {
			ConditionElement conditionElement = (ConditionElement) condition;

			if (condition instanceof SConditionElement) {
				SConditionElement sconditionElement = (SConditionElement) condition;
				if (sconditionElement.getQualifiedState() != null) {
					/**
					 * In the following, it seems that we reference another
					 * component. This is typically the case when the condition is
					 * within an composite error behavior.
					 *
					 * So, we find the referenced component in the component
					 * hierarchy and add all its contributors to the returned
					 * events.
					 */
					QualifiedErrorBehaviorState qs = sconditionElement.getQualifiedState();
					ComponentInstance component = (ComponentInstance) annex.eContainer();
					ComponentInstance referencedComponent = EMV2Util.getLastComponentInstance(qs, component);
					ErrorBehaviorState state = EMV2Util.getState(sconditionElement);
					// either original type or mapped to constraint in condition or type set on state declaration
					TypeSet referencedErrorType = (sconditionElement.getConstraint() != null)
							? sconditionElement.getConstraint()
							: state.getTypeSet();
					EMV2AnnexInstance eai = findEMV2AnnexInstance(referencedComponent);
					StateInstance si = findStateInstance(eai, state);
					// state only
					ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE.createConstrainedInstanceObject();
					cio.setInstanceObject(si);
					cio.setName(si.getName());
					if (referencedErrorType != null) {
						cio.getConstraint().addAll(EcoreUtil.copyAll(referencedErrorType.getTypeTokens()));
					}
					return cio;
				} else if (sconditionElement.getQualifiedErrorPropagationReference() != null) {
					EMV2Path path = sconditionElement.getQualifiedErrorPropagationReference();
					ComponentInstance component = (ComponentInstance) annex.eContainer();
					ComponentInstance referencedComponent = EMV2Util.getLastComponentInstance(path, component);
					ErrorPropagation ep = EMV2Util.getErrorPropagation(path);
					// either original type or mapped to constraint in condition or type set on state declaration
					TypeSet referencedErrorType = (sconditionElement.getConstraint() != null)
							? sconditionElement.getConstraint()
							: ep.getTypeSet();
					ConstrainedInstanceObject cio = createErrorPropagationCIO(ep, EcoreUtil.copy(referencedErrorType),
							findEMV2AnnexInstance(referencedComponent));
					return cio;
				}
			} // end SConditionElement

			if (conditionElement.getConstraint() != null) {
				if (isNoError(conditionElement.getConstraint())) {
					// this is a recovery transition since an incoming propagation constraint is NoError
					return null;
				}
			}
			if (conditionElement.getQualifiedErrorPropagationReference() != null) {
				ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE.createConstrainedInstanceObject();
				EMV2Path path = conditionElement.getQualifiedErrorPropagationReference();

				NamedElement errorModelElement = EMV2Util.getErrorModelElement(path);
				ComponentInstance component = (ComponentInstance) annex.eContainer();
				ComponentInstance referencedComponent = EMV2Util.getLastComponentInstance(path, component);
				EMV2AnnexInstance referencedAnnex = findEMV2AnnexInstance(referencedComponent);
				/**
				 * Here, we have an error event. Likely, this is something we
				 * can get when we are analyzing error component behavior.
				 */
				if (errorModelElement instanceof ErrorEvent) {
					EventInstance evi = findEventInstance(referencedAnnex, (ErrorEvent) errorModelElement);
					if (evi != null) {
						cio.setInstanceObject(evi);
						cio.setName(evi.getName());
						TypeSet ts = conditionElement.getConstraint() != null ? conditionElement.getConstraint()
								: ((ErrorEvent) errorModelElement).getTypeSet();
						if (ts != null) {
							cio.getConstraint().addAll(EcoreUtil.copyAll(ts.getTypeTokens()));
						}
						return cio;
					}
				}

				/**
				 * Here, we have an error propagation. This is notified with the
				 * in propagation within a composite error model.
				 */
				if (errorModelElement instanceof ErrorPropagation) {
					ErrorPropagation errorPropagation = (ErrorPropagation) errorModelElement;
					TypeSet ts = conditionElement.getConstraint() != null ? conditionElement.getConstraint()
							: errorPropagation.getTypeSet();
					cio = createErrorPropagationCIO(errorPropagation, ts, referencedAnnex);
					return cio;
				}

			}
		}
		return null;
	}


	private PropagationPointInstance findPropagationPointInstance(EMV2AnnexInstance annex, PropagationPoint pp) {
		for (PropagationPointInstance ei : annex.getPropagationPoints()) {
			if (ei.getPropagationPoint() == pp) {
				return ei;
			}
		}
		return null;
	}

	private EventInstance findEventInstance(EMV2AnnexInstance annex, ErrorBehaviorEvent ev) {
		for (EventInstance ei : annex.getEvents()) {
			if (ei.getEvent() == ev) {
				return ei;
			}
		}
		return null;
	}

	private EMV2AnnexInstance findEMV2AnnexInstance(ComponentInstance ci) {
		for (AnnexInstance ai : ci.getAnnexInstances()) {
			if (ai instanceof EMV2AnnexInstance emv2AI) {
				return emv2AI;
			}
		}
		return null;
	}

	private FeatureInstance findFeatureInstance(ComponentInstance ci, FeatureorPPReference fppref) {
		if (fppref == null) {
			return null;
		}
		NamedElement fpp = fppref.getFeatureorPP();
		FeatureInstance fi = ci.findFeatureInstance((Feature) fpp);
		FeatureorPPReference curfppref = fppref.getNext();
		while (curfppref != null) {
			fi = fi.findFeatureInstance((Feature) curfppref.getFeatureorPP());
			if (curfppref.getNext() != null) {
				if (fi == null) {
					return null;
				}
				curfppref = curfppref.getNext();
			} else {
				return fi;
			}
		}
		return fi;
	}

	private ErrorPropagationInstance findErrorPropagationInstance(EMV2AnnexInstance annex, ConnectionInstanceEnd cie,
			boolean outgoing) {
		for (ErrorPropagationInstance epi : annex.getPropagations()) {
			if (outgoing ? epi.getDirection().outgoing() : epi.getDirection().incoming()) {
				if (epi instanceof FeaturePropagation) {
					if (((FeaturePropagation) epi).getFeature() == cie) {
						return epi;
					}
				} else if (epi instanceof AccessPropagation) {
					if (getContainerOfType(epi, ComponentInstance.class) == cie) {
						return epi;
					}
				} else if (epi instanceof PointPropagation) {
					if (((PointPropagation) epi).getPoint() == cie) {
						return epi;
					}
				} else if (epi instanceof BindingPropagation) {
					/*
					 * TODO This is probably wrong and needs to be revisited. I added this code to mimic Peter's
					 * behavior so that his tests will pass.
					 *
					 * In his original instantiator, ErrorPropagation.instanceObject is set to the containing
					 * ComponentInstance for propagations that have a binding reference. Then this method would simply
					 * search for the first propagation in which the instanceObject field equals cie. This means that if
					 * cie is a ComponentInstance, then the first propagation with a binding reference is returned. No
					 * consideration is given to what the binding reference is: "processor", "memory", "connection",
					 * etc.
					 *
					 * This is almost certainly not correct and we will need to revist this method when we figure out
					 * propagation paths.
					 */
					if (getContainerOfType(epi, ComponentInstance.class) == cie) {
						return epi;
					}
				} else {
					throw new RuntimeException("Unexpected type: " + epi);
				}

				/*
				 * The following is Peter's original code for this loop.
				 */
//				if (epi.getInstanceObject() == cie) {
//					return epi;
//				}
			}
		}
		return null;
	}

	private ErrorPropagationInstance findErrorPropagationInstance(EMV2AnnexInstance annex, ErrorPropagation ep) {
		var declarativeName = EMV2Util.getName(ep);
		for (ErrorPropagationInstance epi : annex.getPropagations()) {
			if (epi.getName().equalsIgnoreCase(declarativeName)) {
				return epi;
			}
		}
		return null;
	}

	private FeaturePropagation findFeaturePropagation(FeatureInstance feature) {
		var annex = findEMV2AnnexInstance(EcoreUtil2.getContainerOfType(feature, ComponentInstance.class));
		if (annex == null) {
			return null;
		}
		for (var propagation : annex.getPropagations()) {
			if (propagation instanceof FeaturePropagation featurePropagation
					&& featurePropagation.getFeature() == feature) {
				return featurePropagation;
			}
		}
		return null;
	}

	private PointPropagation findPointPropagation(PropagationPointInstance point) {
		var annex = findEMV2AnnexInstance(EcoreUtil2.getContainerOfType(point, ComponentInstance.class));
		if (annex == null) {
			return null;
		}
		for (var propagation : annex.getPropagations()) {
			if (propagation instanceof PointPropagation pointPropagation && pointPropagation.getPoint() == point) {
				return pointPropagation;
			}
		}
		return null;
	}

	private AccessPropagation findAccessPropagation(ComponentInstance component) {
		var annex = findEMV2AnnexInstance(component);
		if (annex == null) {
			return null;
		}
		for (var propagation : annex.getPropagations()) {
			if (propagation instanceof AccessPropagation accessPropagation) {
				return accessPropagation;
			}
		}
		return null;
	}

	private BindingPropagation findBindingPropagation(ComponentInstance component, BindingType binding) {
		var annex = findEMV2AnnexInstance(component);
		if (annex == null) {
			return null;
		}
		for (var propagation : annex.getPropagations()) {
			if (propagation instanceof BindingPropagation bindingPropagation
					&& bindingPropagation.getBinding() == binding) {
				return bindingPropagation;
			}
		}
		return null;
	}

	private void instantiateConnectionPropagationPaths(ConnectionInstance conni, EMV2AnnexInstance annex) {
		ConnectionInstanceEnd src = conni.getSource();
		ConnectionInstanceEnd dst = conni.getDestination();
		EMV2AnnexInstance srcAnnex = findEMV2AnnexInstance(src.getComponentInstance());
		EMV2AnnexInstance dstAnnex = findEMV2AnnexInstance(dst.getComponentInstance());
		if (srcAnnex != null && dstAnnex != null) {
			for (ConstrainedInstanceObject assignment : allOutgoingCIOs(src, srcAnnex)) {
				if (assignment.getInstanceObject() == src) {
					EList<TypeToken> outTypeTokens = assignment.getConstraint();
					for (TypeToken tt : outTypeTokens) {
						Collection<ConstrainedInstanceObject> dstCIOs = allOutPropagationConditionCIOs(dst, tt,
								dstAnnex);
						for (ConstrainedInstanceObject dstCIO : dstCIOs) {
							OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE
									.createOldPropagationPathInstance();
							ppi.setSource(assignment);
							ppi.setTarget(dstCIO);
							ppi.setName(conni.getName() + "-" + dstCIO.getName());
							annex.getOldPropagationPaths().add(ppi);
							addConnectionBindingCIOs(conni, annex, ppi);
						}
						if (dstCIOs.isEmpty()) {
							// use flow if no out propagation condition
							dstCIOs = allIncomingFlowCIOs(dst, tt, dstAnnex);
							for (ConstrainedInstanceObject dstCIO : dstCIOs) {
								OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE
										.createOldPropagationPathInstance();
								ppi.setSource(assignment);
								ppi.setTarget(dstCIO);
								ppi.setName(conni.getName() + "-" + dstCIO.getName());
								annex.getOldPropagationPaths().add(ppi);
								addConnectionBindingCIOs(conni, annex, ppi);
							}
						}
						dstCIOs = allTransitionConditionCIOs(dst, tt, dstAnnex);
						for (ConstrainedInstanceObject dstCIO : dstCIOs) {
							OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE
									.createOldPropagationPathInstance();
							ppi.setSource(assignment);
							ppi.setTarget(dstCIO);
							ppi.setName(conni.getName() + "-" + dstCIO.getName());
							annex.getOldPropagationPaths().add(ppi);
							addConnectionBindingCIOs(conni, annex, ppi);
						}
					}
				}
			}
			// now propagation paths from outgoing to incoming error propagations
			ErrorPropagationInstance outep = findErrorPropagationInstance(srcAnnex, src, true);
			ErrorPropagationInstance inep = findErrorPropagationInstance(dstAnnex, dst, false);
			if (outep != null && inep != null) {
				OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE.createOldPropagationPathInstance();
				ppi.setSource(outep);
				ppi.setTarget(inep);
				ppi.setName(conni.getName() + "-" + inep.getName());
				annex.getOldPropagationPaths().add(ppi);
				addConnectionBindingCIOs(conni, annex, ppi);
			}
		}
	}

	/**
	 * return all outgoing CIOs related to connections, i.e., propagation points, features, and access to components
	 * They are specified by outgoing propagation conditions and error flows
	 * binding related CIOs are retrieved separately.
	 * @param ppi ComponentInstance, FeatureInstance, PropagationPointInstance
	 * @param annex
	 * @return
	 */
	private Collection<ConstrainedInstanceObject> allOutgoingCIOs(InstanceObject ppi, EMV2AnnexInstance annex) {
		EList<ErrorPropagationConditionInstance> epcs = annex.getErrorPropagationConditions();
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();
		for (ErrorPropagationConditionInstance epc : epcs) {
			ConstrainedInstanceObject outGoing = epc.getOutgoingPropagation();
			// add if CIO for feature instance or propagation point instance
			// also add if component instance and propagation kind is access
			if (outGoing != null && outGoing.getInstanceObject() == ppi
					&& (outGoing.getPropagationKind() == null || outGoing.getPropagationKind().equals("access"))) {
				cios.add(outGoing);
			}
		}
		/*
		 * TODO The following was commented out when I removed Peter's fields of ErrorFlowInstance. I'm leaving the
		 * commented out code in for now in case we need to look at how Peter handled ConstrainedInstanceObjects. This
		 * commented out code can be removed when it is time to remove this method.
		 */
//		for (ErrorFlowInstance epc : annex.getErrorFlows()) {
//			ConstrainedInstanceObject outGoing = epc.getOutgoing();
//			// add if CIO for feature instance or propagation point instance
//			// also add if component instance and propagation kind is access
//			if (outGoing != null && outGoing.getInstanceObject() == ppi
//					&& (outGoing.getPropagationKind() == null || outGoing.getPropagationKind().equals("access"))) {
//				cios.add(outGoing);
//			}
//		}
		return cios;
	}

	private Collection<ConstrainedInstanceObject> allOutgoingBindingCIOs(ComponentInstance ppi, EMV2AnnexInstance annex,
			String bindingKind) {
		EList<ErrorPropagationConditionInstance> epcs = annex.getErrorPropagationConditions();
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();
		for (ErrorPropagationConditionInstance epc : epcs) {
			ConstrainedInstanceObject outGoing = epc.getOutgoingPropagation();
			if (outGoing != null && (outGoing.getInstanceObject() == ppi && (outGoing.getPropagationKind() == null
					|| outGoing.getPropagationKind().contentEquals(bindingKind)))) {
				cios.add(epc.getOutgoingPropagation());
			}
		}
		/*
		 * TODO The following was commented out when I removed Peter's fields of ErrorFlowInstance. I'm leaving the
		 * commented out code in for now in case we need to look at how Peter handled ConstrainedInstanceObjects. This
		 * commented out code can be removed when it is time to remove this method.
		 */
//		for (ErrorFlowInstance epc : annex.getErrorFlows()) {
//			ConstrainedInstanceObject outGoing = epc.getOutgoing();
//			if (outGoing != null && (outGoing.getInstanceObject() == ppi && (outGoing.getPropagationKind() == null
//					|| outGoing.getPropagationKind().contentEquals(bindingKind)))) {
//				cios.add(outGoing);
//			}
//		}
		return cios;
	}

	/**
	 * all incoming CIO not related to bindings. This includes state transition conditions, outgoing propagation conditions, and error flows
	 * @param ppi
	 * @param tt
	 * @param annex
	 * @return
	 */
	private Collection<ConstrainedInstanceObject> allOutPropagationConditionCIOs(InstanceObject ppi,
			TypeToken tt,
			EMV2AnnexInstance annex) {
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();
		for (ErrorPropagationConditionInstance epc : annex.getErrorPropagationConditions()) {
			if (epc.getCondition() != null) {
				for (ConstrainedInstanceObject cio : EcoreUtil2.eAllOfType(epc.getCondition(),
						ConstrainedInstanceObject.class)) {
					if (cio.getInstanceObject() == ppi && (!(ppi instanceof ComponentInstance)
							&& (cio.getPropagationKind() == null || cio.getPropagationKind().equals("access")))) {
						if (tt == null || EMV2TypeSetUtil.contains(cio.getConstraint(), tt)) {
							cios.add(cio);
						}
					}
				}
			}
		}
		return cios;
	}

	private Collection<ConstrainedInstanceObject> allTransitionConditionCIOs(InstanceObject ppi, TypeToken tt,
			EMV2AnnexInstance annex) {
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();

		EList<StateTransitionInstance> sts = annex.getTransitions();
		for (StateTransitionInstance st : sts) {
			if (st.getCondition() != null) {
				for (ConstrainedInstanceObject cio : EcoreUtil2.eAllOfType(st.getCondition(),
						ConstrainedInstanceObject.class)) {
					if (cio.getInstanceObject() == ppi
							&& (cio.getPropagationKind() == null || cio.getPropagationKind().equals("access"))) {
						if (tt == null || EMV2TypeSetUtil.contains(cio.getConstraint(), tt)) {
							cios.add(cio);
						}
					}
				}
				;
			}
		}
		return cios;
	}

	private Collection<ConstrainedInstanceObject> allIncomingFlowCIOs(InstanceObject ppi, TypeToken tt,
			EMV2AnnexInstance annex) {
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();
		/*
		 * TODO The following was commented out when I removed Peter's fields of ErrorFlowInstance. I'm leaving the
		 * commented out code in for now in case we need to look at how Peter handled ConstrainedInstanceObjects. This
		 * commented out code can be removed when it is time to remove ConstrainedInstanceObject.
		 */
//		for (ErrorFlowInstance epc : annex.getErrorFlows()) {
//			ConstrainedInstanceObject inComing = epc.getIncoming();
//			if (inComing != null && inComing.getInstanceObject() == ppi
//					&& (inComing.getPropagationKind() == null || inComing.getPropagationKind().equals("access"))) {
//				if (tt == null || EMV2TypeSetUtil.contains(inComing.getConstraint(), tt)) {
//					cios.add(inComing);
//				}
//			}
//		}
		return cios;
	}

	private Collection<ConstrainedInstanceObject> allOutPropagationConditionBindingCIOs(InstanceObject ppi,
			TypeToken tt,
			EMV2AnnexInstance annex, String bindingKind) {
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();
		for (ErrorPropagationConditionInstance epc : annex.getErrorPropagationConditions()) {
			if (epc.getCondition() != null) {
				for (ConstrainedInstanceObject cio : EcoreUtil2.eAllOfType(epc.getCondition(),
						ConstrainedInstanceObject.class)) {
					if (cio.getInstanceObject() == ppi
							&& (cio.getPropagationKind() == null || cio.getPropagationKind().equals(bindingKind))) {
						if (tt == null || EMV2TypeSetUtil.contains(cio.getConstraint(), tt)) {
							cios.add(cio);
						}
					}
				}
			}
		}
		return cios;
	}

	private Collection<ConstrainedInstanceObject> allTransitionConditionBindingCIOs(InstanceObject ppi, TypeToken tt,
			EMV2AnnexInstance annex, String bindingKind) {
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();
		EList<StateTransitionInstance> sts = annex.getTransitions();
		for (StateTransitionInstance st : sts) {
			if (st.getCondition() != null) {
				for (ConstrainedInstanceObject cio : EcoreUtil2.eAllOfType(st.getCondition(),
						ConstrainedInstanceObject.class)) {
					if (cio.getInstanceObject() == ppi
							&& (cio.getPropagationKind() == null || cio.getPropagationKind().equals(bindingKind))) {
						if (tt == null || EMV2TypeSetUtil.contains(cio.getConstraint(), tt)) {
							cios.add(cio);
						}
					}
				}
				;
			}
		}
		return cios;
	}

	private Collection<ConstrainedInstanceObject> allIncomingFlowBindingCIOs(InstanceObject ppi, TypeToken tt,
			EMV2AnnexInstance annex, String bindingKind) {
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();
		/*
		 * TODO The following was commented out when I removed Peter's fields of ErrorFlowInstance. I'm leaving the
		 * commented out code in for now in case we need to look at how Peter handled ConstrainedInstanceObjects. This
		 * commented out code can be removed when it is time to remove ConstrainedInstanceObject.
		 */
//		for (ErrorFlowInstance epc : annex.getErrorFlows()) {
//			ConstrainedInstanceObject inComing = epc.getIncoming();
//			if (inComing != null && (inComing.getInstanceObject() == ppi
//					|| (inComing.getPropagationKind() != null && inComing.getPropagationKind().equals(bindingKind)))) {
//				if (tt == null || EMV2TypeSetUtil.contains(inComing.getConstraint(), tt)) {
//					cios.add(inComing);
//				}
//			}
//		}
		return cios;
	}

	/*
	 * Peter's old method for instantiating a user defined propagation path.
	 */
//	private void instantiatePropagationPath(PropagationPath pp, EMV2AnnexInstance annex) {
//		ComponentInstance contextCI = (ComponentInstance) annex.eContainer();
//		InstanceObject srcIO = findQualifiedPropagationPoint(pp.getSource(), contextCI);
//		InstanceObject dstIO = findQualifiedPropagationPoint(pp.getTarget(), contextCI);
//		EMV2AnnexInstance srcAnnex = findEMV2AnnexInstance(srcIO.getComponentInstance());
//		EMV2AnnexInstance dstAnnex = findEMV2AnnexInstance(dstIO.getComponentInstance());
//		for (ConstrainedInstanceObject action : allOutgoingCIOs(srcIO, srcAnnex)) {
//			if (action.getInstanceObject() == srcIO) {
//				EList<TypeToken> outTypeTokens = action.getConstraint();
//				for (TypeToken tt : outTypeTokens) {
//					Collection<ConstrainedInstanceObject> dstCIOs = allOutPropagationConditionCIOs(dstIO, tt, dstAnnex);
//					for (ConstrainedInstanceObject dstCIO : dstCIOs) {
//						OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE
//								.createOldPropagationPathInstance();
//						ppi.setSource(action);
//						ppi.setTarget(dstCIO);
//						ppi.setName(pp.getName() + "-" + dstCIO.getName());
//						annex.getOldPropagationPaths().add(ppi);
//					}
//					if (dstCIOs.isEmpty()) {
//						// use flow if no out propagation condition
//						dstCIOs = allIncomingFlowCIOs(dstIO, tt, dstAnnex);
//						for (ConstrainedInstanceObject dstCIO : dstCIOs) {
//							OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE
//									.createOldPropagationPathInstance();
//							ppi.setSource(action);
//							ppi.setTarget(dstCIO);
//							ppi.setName(pp.getName() + "-" + dstCIO.getName());
//							annex.getOldPropagationPaths().add(ppi);
//						}
//					}
//					dstCIOs = allTransitionConditionCIOs(dstIO, tt, dstAnnex);
//					for (ConstrainedInstanceObject dstCIO : dstCIOs) {
//						OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE
//								.createOldPropagationPathInstance();
//						ppi.setSource(action);
//						ppi.setTarget(dstCIO);
//						ppi.setName(pp.getName() + "-" + dstCIO.getName());
//						annex.getOldPropagationPaths().add(ppi);
//					}
//				}
//			}
//		}
//	}

	private void instantiateUserDefinedPath(PropagationPath path, EMV2AnnexInstance annex, ComponentInstance context) {
		// Paths that point to a feature are not instantiated.
		if (getQualifiedPointReference(path.getSource()) instanceof PropagationPoint source
				&& getQualifiedPointReference(path.getTarget()) instanceof PropagationPoint destination) {
			var pathInstance = EMV2InstanceFactory.eINSTANCE.createUserDefinedPath();
			pathInstance.setPath(path);
			var sourcePointInstance = findPropagationPointInstance(path.getSource(), context, source);
			pathInstance.setSourcePoint(sourcePointInstance);
			var destinationPointInstance = findPropagationPointInstance(path.getTarget(), context, destination);
			pathInstance.setDestinationPoint(destinationPointInstance);
			if (path.getName() == null) {
				var substringIndex = context.getInstanceObjectPath().length() + 1;
				var sourcePath = sourcePointInstance.getInstanceObjectPath().substring(substringIndex);
				var destinationPath = destinationPointInstance.getInstanceObjectPath().substring(substringIndex);
				pathInstance.setName(sourcePath + " -> " + destinationPath);
			} else {
				pathInstance.setName(path.getName());
			}
			var sourcePropagation = findPointPropagation(sourcePointInstance);
			if (sourcePropagation != null && sourcePropagation.getDirection().outgoing()) {
				pathInstance.setSourcePropagation(sourcePropagation);
			}
			var destinationPropagation = findPointPropagation(destinationPointInstance);
			if (destinationPropagation != null && destinationPropagation.getDirection().incoming()) {
				pathInstance.setDestinationPropagation(destinationPropagation);
			}
			annex.getPropagationPaths().add(pathInstance);
		}
	}

	private static NamedElement getQualifiedPointReference(QualifiedPropagationPoint path) {
		while (path.getNext() != null) {
			path = path.getNext();
		}
		return path.getPropagationPoint();
	}

	private PropagationPointInstance findPropagationPointInstance(QualifiedPropagationPoint path,
			ComponentInstance component, PropagationPoint point) {
		while (path.getSubcomponent() != null) {
			component = component.findSubcomponentInstance(path.getSubcomponent().getSubcomponent());
			path = path.getNext();
		}
		return findPropagationPointInstance(findEMV2AnnexInstance(component), point);
	}

	/*
	 * Used in Peter code that is commented out.
	 */
//	private InstanceObject findQualifiedPropagationPoint(QualifiedPropagationPoint qpp, ComponentInstance context) {
//		QualifiedPropagationPoint lqpp = qpp;
//		ComponentInstance curci = context;
//		while (lqpp.getSubcomponent() != null) {
//			curci = curci.findSubcomponentInstance(lqpp.getSubcomponent().getSubcomponent());
//			if (curci == null) {
//				return null;
//			}
//			lqpp = lqpp.getNext();
//		}
//		if (lqpp.getPropagationPoint() != null) {
//			NamedElement ne = lqpp.getPropagationPoint();
//			if (ne instanceof Feature) {
//				return curci.findFeatureInstance((Feature) ne);
//			} else if (ne instanceof PropagationPoint) {
//				EMV2AnnexInstance aei = findEMV2AnnexInstance(curci);
//				return findPropagationPointInstance(aei, (PropagationPoint) ne);
//			}
//		}
//		return null;
//	}


	private void instantiateBindingPaths(ComponentInstance ci, EMV2AnnexInstance annex) {
		if (DeploymentProperties.acceptsActualProcessorBinding(ci)) {
			List<ComponentInstance> cpus = InstanceModelUtil.getProcessorBinding(ci);
			for (ComponentInstance cpu : cpus) {
				instantiateBindingPropagationPaths(annex, ci, cpu, "processor");
			}
		}
		if (!(ci.getCategory() != ComponentCategory.VIRTUAL_PROCESSOR)) {
			// do memory bindings
			List<ComponentInstance> mems = InstanceModelUtil.getMemoryBinding(ci);
			for (ComponentInstance mem : mems) {
				instantiateBindingPropagationPaths(annex, ci, mem, "memory");
			}
		}
		if (ci.getCategory() == ComponentCategory.VIRTUAL_BUS) {
			// do connection bindings of virtual buses
			List<ComponentInstance> boundresources = InstanceModelUtil.getConnectionBinding(ci);
			for (ComponentInstance bres : boundresources) {
				instantiateBindingPropagationPaths(annex, ci, bres, "connection");
			}
		}
		if (DeploymentProperties.acceptsActualFunctionBinding(ci)) {
			List<ComponentInstance> systems = InstanceModelUtil.getFunctionBinding(ci);
			for (ComponentInstance system : systems) {
				instantiateBindingPropagationPaths(annex, ci, system, "binding");
			}
		}
	}

	private Collection<ConstrainedInstanceObject> addConnectionBindingCIOs(ConnectionInstance conni,
			EMV2AnnexInstance annex,
			OldPropagationPathInstance ppi) {
		Collection<ConstrainedInstanceObject> cios = new ArrayList<ConstrainedInstanceObject>();
		List<ComponentInstance> boundresources = InstanceModelUtil.getConnectionBinding(conni);
		if (boundresources.isEmpty()) {
			boundresources = InstanceModelUtil.deriveBoundBuses(conni);
		}
		for (ComponentInstance boundResource : boundresources) {
			ErrorPropagation BRsrcprop = EMV2Util.findOutgoingErrorPropagation(boundResource.getComponentClassifier(),
					"bindings");
			if (BRsrcprop != null) {
				ErrorPropagationInstance srcEPI = findErrorPropagationInstance(annex, BRsrcprop);
				if (srcEPI != null) {
					OldPropagationPathInstance srcBindPPI = EcoreUtil.copy(ppi);
					srcBindPPI.setSource(srcEPI);
					srcBindPPI.setName(ppi.getName() + "-bindings-" + boundResource.getName());
					annex.getOldPropagationPaths().add(srcBindPPI);
				}
			}
			ErrorPropagation BRdstprop = EMV2Util.findIncomingErrorPropagation(boundResource.getComponentClassifier(),
					"bindings");
			if (BRdstprop != null) {
				ErrorPropagationInstance dstEPI = findErrorPropagationInstance(annex, BRdstprop);
				if (dstEPI != null) {
					OldPropagationPathInstance dstBindPPI = EcoreUtil.copy(ppi);
					dstBindPPI.setTarget(dstEPI);
					dstBindPPI.setName(ppi.getName() + "-bindings-" + boundResource.getName());
					annex.getOldPropagationPaths().add(dstBindPPI);
				}
			}
		}
		return cios;
	}

	/**
	 * Add a binding as propagation path.
	 * The first argument is the component bound to a resource (e.g. a process) and the
	 * boundResource argument the associated resources (e.g. a processor).
	 * We will add the propagation path in each direction if declared in the EMV2 annex.
	 * @param comp
	 * @param boundResource
	 */
	private void instantiateBindingPropagationPaths(EMV2AnnexInstance annex, ComponentInstance comp,
			ComponentInstance boundResource, String resourcebindingKind) {
		EMV2AnnexInstance resourceAnnex = findEMV2AnnexInstance(boundResource);
		EMV2AnnexInstance componentAnnex = findEMV2AnnexInstance(comp);
		if (resourceAnnex == null || componentAnnex == null) {
			return;
		}
		for (ConstrainedInstanceObject outGoing : allOutgoingBindingCIOs(boundResource, resourceAnnex, "bindings")) {
			EList<TypeToken> outTypeTokens = outGoing.getConstraint();
			for (TypeToken tt : outTypeTokens) {
				Collection<ConstrainedInstanceObject> dstCIOs = allOutPropagationConditionBindingCIOs(comp, tt,
						componentAnnex,
						resourcebindingKind);
				for (ConstrainedInstanceObject dstCIO : dstCIOs) {
					OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE.createOldPropagationPathInstance();
					ppi.setSource(outGoing);
					ppi.setTarget(dstCIO);
					ppi.setName(boundResource.getName() + "-bindings-" + getPrintName(dstCIO));
					annex.getOldPropagationPaths().add(ppi);
				}
				if (dstCIOs.isEmpty()) {
					// use flow if no out propagation condition
					dstCIOs = allIncomingFlowBindingCIOs(comp, tt, componentAnnex, resourcebindingKind);
					for (ConstrainedInstanceObject dstCIO : dstCIOs) {
						OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE
								.createOldPropagationPathInstance();
						ppi.setSource(outGoing);
						ppi.setTarget(dstCIO);
						ppi.setName(boundResource.getName() + "-bindings-" + getPrintName(dstCIO));
						annex.getOldPropagationPaths().add(ppi);
					}
				}
				dstCIOs = allTransitionConditionBindingCIOs(comp, tt, componentAnnex, resourcebindingKind);
				for (ConstrainedInstanceObject dstCIO : dstCIOs) {
					OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE.createOldPropagationPathInstance();
					ppi.setSource(outGoing);
					ppi.setTarget(dstCIO);
					ppi.setName(boundResource.getName() + "-bindings-" + getPrintName(dstCIO));
					annex.getOldPropagationPaths().add(ppi);
				}
			}
		}
		for (ConstrainedInstanceObject outGoing : allOutgoingBindingCIOs(comp, componentAnnex, resourcebindingKind)) {
			EList<TypeToken> outTypeTokens = outGoing.getConstraint();
			for (TypeToken tt : outTypeTokens) {
				Collection<ConstrainedInstanceObject> dstCIOs = allOutPropagationConditionBindingCIOs(boundResource, tt,
						resourceAnnex,
						"bindings");
				for (ConstrainedInstanceObject dstCIO : dstCIOs) {
					OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE.createOldPropagationPathInstance();
					ppi.setSource(outGoing);
					ppi.setTarget(dstCIO);
					ppi.setName(getPrintName(outGoing) + getPrintName(dstCIO));
					annex.getOldPropagationPaths().add(ppi);
				}
				if (dstCIOs.isEmpty()) {
					// use flow if no out propagation condition
					dstCIOs = allIncomingFlowBindingCIOs(boundResource, tt, resourceAnnex, "bindings");
					for (ConstrainedInstanceObject dstCIO : dstCIOs) {
						OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE
								.createOldPropagationPathInstance();
						ppi.setSource(outGoing);
						ppi.setTarget(dstCIO);
						ppi.setName(getPrintName(outGoing) + getPrintName(dstCIO));
						annex.getOldPropagationPaths().add(ppi);
					}
				}
				dstCIOs = allTransitionConditionBindingCIOs(boundResource, tt, resourceAnnex, "bindings");
				for (ConstrainedInstanceObject dstCIO : dstCIOs) {
					OldPropagationPathInstance ppi = EMV2InstanceFactory.eINSTANCE.createOldPropagationPathInstance();
					ppi.setSource(outGoing);
					ppi.setTarget(dstCIO);
					ppi.setName(getPrintName(outGoing) + getPrintName(dstCIO));
					annex.getOldPropagationPaths().add(ppi);
				}
			}
		}
		// subcomponents are bound too
		for (ComponentInstance subci : comp.getComponentInstances()) {
			instantiateBindingPropagationPaths(annex, subci, boundResource, resourcebindingKind);
		}
	}

	private void instantiatePropertyAssociations(EMV2InstanceObject emv2io, ComponentInstance ci,
			NamedElement emv2element, TypeToken tt) {
		ErrorTypes et = tt != null && tt.getType().size() == 1 ? tt.getType().get(0) : null;
		// token with a single type can have property values. Multiple types represents a product type
		List<EMV2PropertyAssociation> ODs = EMV2Properties.getOccurrenceDistributionProperty(ci, emv2element, et);
		instantiatePropertyAssociation(ODs, emv2io);
		List<EMV2PropertyAssociation> Hazards = EMV2Properties.getHazardsProperty(ci, emv2element, et);
		instantiatePropertyAssociation(Hazards, emv2io);
		List<EMV2PropertyAssociation> SEVs = EMV2Properties.getSeverityProperty(ci, emv2element, et);
		instantiatePropertyAssociation(SEVs, emv2io);
		List<EMV2PropertyAssociation> Likes = EMV2Properties.getLikelihoodProperty(ci, emv2element, et);
		instantiatePropertyAssociation(Likes, emv2io);
		// currently only the above properties are actually being used in analyses
		// for other EMV2 properties here is the generic method
//			List<EMV2PropertyAssociation> PAs = EMV2Properties.getProperty("EMV2::propertyname",ci, emv2element, et);
//			instantiatePropertyAssociation(PAs, emv2io);
	}

	private void instantiatePropertyAssociation(List<EMV2PropertyAssociation> PAs, EMV2InstanceObject emv2io) {
		if (!PAs.isEmpty()) {
			EMV2PropertyAssociation pa = PAs.get(0);
			PropertyAssociationInstance newPA = InstanceFactory.eINSTANCE.createPropertyAssociationInstance();
			newPA.setProperty(pa.getProperty());
			newPA.getOwnedValues().addAll(EcoreUtil.copyAll(pa.getOwnedValues()));
			// handle reference/symbolic labels? or do it when retrieving it from instance
			if (!newPA.getOwnedValues().isEmpty()) {
				emv2io.getOwnedPropertyAssociations().add(newPA);
			}
		}
	}

	private String getPrintName(ConstrainedInstanceObject cio) {
		String oname = cio.getInstanceObject().getName() + cio.getPropagationKind() == null ? ""
				: "-" + cio.getPropagationKind();
		String tt = EMV2Util.getName(cio.getConstraint());
		return oname + (tt.isEmpty() ? "" : "-" + tt);
	}


}
