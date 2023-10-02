/*
 * ============================================================================
 *  Copyright © 2002-2023 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.testutil.impl;

import static java.util.Collections.enumeration;
import static java.util.Objects.isNull;
import static org.apiguardian.api.API.Status.STABLE;

import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;

import org.apiguardian.api.API;

/**
 *  A simple implementation of
 *  {@link ResourceBundle}
 *  for testing purposes.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ResourceBundleImpl.java 1074 2023-10-02 12:05:06Z tquadrat $
 *  @since 0.0.1
 */
@API( status = STABLE, since = "0.0.1" )

public class ResourceBundleImpl extends ResourceBundle
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The values.
     */
    private final Map<String,Object> m_Texts;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code ResourceBundleImpl} instance.
     *
     *  @param  texts   The texts.
     */
    public ResourceBundleImpl( final Map<String,Object> texts )
    {
        m_Texts = isNull( texts ) ? Map.of() : texts;
    }   //  ResourceBundleImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    protected final Object handleGetObject( final String key )
    {
        return m_Texts.get( key );
    }   //  handleGetObject()

    /**
     *  {@inheritDoc}
     */
    @Override
    public Enumeration<String> getKeys()
    {
        final var retValue = enumeration( m_Texts.keySet() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getKeys()
}
//  class ResourceBundleImpl

/*
 *  End of File
 */