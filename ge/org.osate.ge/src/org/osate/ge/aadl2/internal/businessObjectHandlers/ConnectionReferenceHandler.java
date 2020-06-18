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
package org.osate.ge.aadl2.internal.businessObjectHandlers;

import java.util.Optional;

import org.osate.aadl2.instance.ConnectionReference;
import org.osate.ge.BusinessObjectContext;
import org.osate.ge.CanonicalBusinessObjectReference;
import org.osate.ge.GraphicalConfiguration;
import org.osate.ge.GraphicalConfigurationBuilder;
import org.osate.ge.RelativeBusinessObjectReference;
import org.osate.ge.businessObjectHandlers.GetGraphicalConfigurationContext;
import org.osate.ge.businessObjectHandlers.GetNameContext;
import org.osate.ge.businessObjectHandlers.IsApplicableContext;
import org.osate.ge.businessObjectHandlers.ReferenceContext;
import org.osate.ge.graphics.Color;
import org.osate.ge.graphics.ConnectionBuilder;
import org.osate.ge.graphics.Graphic;
import org.osate.ge.graphics.Style;
import org.osate.ge.graphics.StyleBuilder;
import org.osate.ge.internal.util.AgeAadlUtil;
import org.osate.ge.query.StandaloneQuery;
import org.osate.ge.services.QueryService;

public class ConnectionReferenceHandler extends AadlBusinessObjectHandler {
	private static final Graphic graphic = ConnectionBuilder.create().build();
	private static final Style style = StyleBuilder.create().backgroundColor(Color.BLACK).build();
	private static final Style partialStyle = StyleBuilder.create().backgroundColor(Color.BLACK).dotted().build();
	private static StandaloneQuery srcQuery = StandaloneQuery
			.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference((
							ConnectionReference cr) -> AgeAadlUtil
							.getPathToBusinessObject(cr.getComponentInstance(), cr.getSource()))
					.first());
	private static StandaloneQuery partialSrcQuery = StandaloneQuery
			.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference(
							(ConnectionReference cr) -> AgeAadlUtil
							.getPathToBusinessObject(cr.getComponentInstance(), cr.getSource()),
							1)
					.first());
	private static StandaloneQuery dstQuery = StandaloneQuery
			.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference((
							ConnectionReference cr) -> AgeAadlUtil
							.getPathToBusinessObject(cr.getComponentInstance(), cr.getDestination()))
					.first());
	private static StandaloneQuery partialDstQuery = StandaloneQuery.create((rootQuery) -> rootQuery.parent()
			.descendantsByBusinessObjectsRelativeReference((
					ConnectionReference cr) -> AgeAadlUtil
					.getPathToBusinessObject(cr.getComponentInstance(), cr.getDestination()), 1)
			.first());

	@Override
	public boolean isApplicable(final IsApplicableContext ctx) {
		return ctx.getBusinessObject(ConnectionReference.class).isPresent();
	}

	@Override
	public CanonicalBusinessObjectReference getCanonicalReference(final ReferenceContext ctx) {
		final ConnectionReference bo = ctx.getBusinessObject(ConnectionReference.class).get();
		return new CanonicalBusinessObjectReference(AadlReferenceUtil.INSTANCE_ID,
				AadlReferenceUtil.CONNECTION_REFERENCE_KEY,
				AadlReferenceUtil.getSystemInstanceKey(bo),
				buildConnectionReferenceId(bo));
	}

	@Override
	public RelativeBusinessObjectReference getRelativeReference(final ReferenceContext ctx) {
		return new RelativeBusinessObjectReference(AadlReferenceUtil.INSTANCE_ID,
				AadlReferenceUtil.CONNECTION_REFERENCE_KEY,
				buildConnectionReferenceId(ctx.getBusinessObject(ConnectionReference.class).get()));
	}

	@Override
	public Optional<GraphicalConfiguration> getGraphicalConfiguration(final GetGraphicalConfigurationContext ctx) {
		final BusinessObjectContext boc = ctx.getBusinessObjectContext();
		final QueryService queryService = ctx.getQueryService();
		BusinessObjectContext src = queryService.getFirstResult(srcQuery, boc);
		BusinessObjectContext dst = queryService.getFirstResult(dstQuery, boc);
		boolean partial = false;

		if(src == null) {
			src = queryService.getFirstResult(partialSrcQuery, boc);
			partial = true;
		}

		if(dst == null) {
			dst = queryService.getFirstResult(partialDstQuery, boc);
			partial = true;
		}

		if (src == dst) {
			return Optional.empty();
		}

		// Don't display connection references when one endpoint is an ancestor of the other. This can happen for a subset of partial connections.
		if (src != null && dst != null) {
			for(BusinessObjectContext srcAncestor = src.getParent(); srcAncestor != null; srcAncestor = srcAncestor.getParent()) {
				if(srcAncestor == dst) {
					return Optional.empty();
				}
			}

			for(BusinessObjectContext dstAncestor = dst.getParent(); dstAncestor != null; dstAncestor = dstAncestor.getParent()) {
				if(dstAncestor == src) {
					return Optional.empty();
				}
			}
		}

		return Optional.of(GraphicalConfigurationBuilder.create().
				graphic(graphic).
				style(partial ? partialStyle : style).
				source(src).
				destination(dst).
				build());
	}

	@Override
	public String getName(final GetNameContext ctx) {
		return ctx.getBusinessObject(ConnectionReference.class)
				.map(cr -> cr.getFullName())
				.orElse("");
	}

	private static String buildConnectionReferenceId(final ConnectionReference cr) {
		return (cr.getConnection() == null ? "<null>" : cr.getConnection().getFullName()) + " : "
				+ cr.getSource().getInstanceObjectPath().toLowerCase() + " -> "
				+ cr.getDestination().getInstanceObjectPath().toLowerCase();
	}
}
