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

import static java.util.Objects.nonNull;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.testutil.TestUtils.requireNotEmptyArgument;

import javax.lang.model.element.Name;

import org.apiguardian.api.API;

/**
 *  An implementation of
 *  {@link Name}
 *  for test purposes.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: NameTestImpl.java 1074 2023-10-02 12:05:06Z tquadrat $
 *  @since 0.0.1
 */
@API( status = STABLE, since = "0.0.1" )
public class NameTestImpl implements Name
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The value.
     */
    private final String m_Value;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code NameTestImpl} instance.
     *
     *  @param  value   The value.
     */
    public NameTestImpl( final CharSequence value )
    {
        m_Value = requireNotEmptyArgument( value, "value" ).toString().intern();
    }   //  NameTestImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final char charAt( final int index ) { return m_Value.charAt( index ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean contentEquals( final CharSequence cs )
    {
        final var retValue = nonNull( cs ) && cs.equals( m_Value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  contentEquals()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean equals( final Object obj )
    {
        var retValue = this == obj;
        if( !retValue && (obj instanceof final Name name ) )
        {
            retValue = contentEquals( name );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  equals()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int hashCode() { return m_Value.hashCode(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int length() { return m_Value.length(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final CharSequence subSequence( final int start, final int end ) { return m_Value.subSequence( start, end ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString() { return m_Value; }
}
//  class NameTestImpl

/*
 *  End of File
 */