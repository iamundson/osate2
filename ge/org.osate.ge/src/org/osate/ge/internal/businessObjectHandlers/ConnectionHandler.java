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
package org.osate.ge.internal.businessObjectHandlers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.Context;
import org.osate.aadl2.SubprogramCall;
import org.osate.ge.BusinessObjectContext;
import org.osate.ge.GraphicalConfiguration;
import org.osate.ge.GraphicalConfigurationBuilder;
import org.osate.ge.aadl2.internal.AadlNamingUtil;
import org.osate.ge.di.CanDelete;
import org.osate.ge.di.CanRename;
import org.osate.ge.di.GetGraphicalConfiguration;
import org.osate.ge.di.GetName;
import org.osate.ge.di.IsApplicable;
import org.osate.ge.di.Names;
import org.osate.ge.di.ValidateName;
import org.osate.ge.graphics.Color;
import org.osate.ge.graphics.ConnectionBuilder;
import org.osate.ge.graphics.Graphic;
import org.osate.ge.graphics.Style;
import org.osate.ge.graphics.StyleBuilder;
import org.osate.ge.internal.util.AadlInheritanceUtil;
import org.osate.ge.query.StandaloneQuery;
import org.osate.ge.services.QueryService;

public class ConnectionHandler {
	private static final Graphic graphic = ConnectionBuilder.create().build();
	private static StandaloneQuery srcQuery = StandaloneQuery
			.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference(
							(Connection c) -> getBusinessObjectsPathToConnectedElement(c.getAllSourceContext(),
									c.getRootConnection().getSource()))
					.first());
	private static StandaloneQuery partialSrcQuery = StandaloneQuery
			.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference(
							(Connection c) -> getBusinessObjectsPathToConnectedElement(c.getAllSourceContext(),
									c.getRootConnection().getSource()),
							1)
					.first());
	private static StandaloneQuery dstQuery = StandaloneQuery
			.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference(
							(Connection c) -> getBusinessObjectsPathToConnectedElement(c.getAllDestinationContext(),
									c.getRootConnection().getDestination()))
					.first());
	private static StandaloneQuery partialDstQuery = StandaloneQuery
			.create((rootQuery) -> rootQuery.parent()
					.descendantsByBusinessObjectsRelativeReference(
							(Connection c) -> getBusinessObjectsPathToConnectedElement(c.getAllDestinationContext(),
									c.getRootConnection().getDestination()),
							1)
					.first());

	@IsApplicable
	@CanRename
	@CanDelete
	public boolean isApplicable(final @Named(Names.BUSINESS_OBJECT) Connection c) {
		return true;
	}

	@GetGraphicalConfiguration
	public GraphicalConfiguration getGraphicalConfiguration(final @Named(Names.BUSINESS_OBJECT) Connection c,
			final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext boc, final QueryService queryService) {
		BusinessObjectContext src = queryService.getFirstResult(srcQuery, boc);
		BusinessObjectContext dst = queryService.getFirstResult(dstQuery, boc);
		boolean partial = false;

		if (src == null) {
			src = queryService.getFirstResult(partialSrcQuery, boc);
			partial = true;
		}

		if (dst == null) {
			dst = queryService.getFirstResult(partialDstQuery, boc);
			partial = true;
		}

		final StyleBuilder sb = StyleBuilder
				.create(AadlInheritanceUtil.isInherited(boc) ? Styles.INHERITED_ELEMENT : Style.EMPTY)
				.backgroundColor(Color.BLACK);
		if (partial) {
			sb.dotted();
		}

		return GraphicalConfigurationBuilder.create().graphic(graphic).style(sb.build()).source(src).destination(dst)
				.build();
	}

	/**
	 * Gets an array of business objects which describes the logical diagram element path to the connection end.
	 * @param ctx
	 * @param connectionEnd
	 * @return
	 */
	private static Object[] getBusinessObjectsPathToConnectedElement(final Context ctx,
			final ConnectedElement connectedElement) {
		if (connectedElement == null) {
			return null;
		}

		final List<Object> path = new ArrayList<>(6);
		if (ctx instanceof SubprogramCall) {
			path.add(ctx.eContainer());
		}

		if (ctx != null) {
			path.add(ctx);
		}

		for (ConnectedElement tmp = connectedElement; tmp != null; tmp = tmp.getNext()) {
			path.add(tmp.getConnectionEnd());
		}

		return path.toArray();
	}

	// Labels
	@GetName
	public String getName(final @Named(Names.BUSINESS_OBJECT) Connection c) {
		return c.getName();
	}

// Renaming
	@ValidateName
	public String validateName(final @Named(Names.BUSINESS_OBJECT) Connection c,
			final @Named(Names.NAME) String value) {
		return AadlNamingUtil.checkNameValidity(c, value);
	}
}
