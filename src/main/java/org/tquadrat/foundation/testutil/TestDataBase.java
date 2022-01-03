/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.testutil;

import static org.apiguardian.api.API.Status.STABLE;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

import org.apiguardian.api.API;

/**
 *  This class is meant as the base class for test data records.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestDataBase.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.1.0
 *
 *  @param  <E> The type of the expected result.
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( {"AbstractClassNeverImplemented", "AbstractClassWithoutAbstractMethods"} )
@API( status = STABLE, since = "0.0.5" )
public abstract class TestDataBase<E> implements Serializable
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code TestDataBase} objects.
     */
    @SuppressWarnings( "rawtypes" )
    public static final TestDataBase [] EMPTY_TestDataBase_ARRAY = new TestDataBase [0];

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The optional description of the test.
     */
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    private final Optional<String> m_Description;

    /**
     *  The expected result.
     */
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    private final Optional<E> m_Expected;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     *  @hidden
     */
    @Serial
    private static final long serialVersionUID = 1L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code TestDataBase} instance.
     *
     *  @param  expected    The expected result for the test; if {@code null}
     *      the test should fail.
     */
    protected TestDataBase( final E expected ) { this( expected, null ); }

    /**
     *  Creates a new {@code TestDataBase} instance.
     *
     *  @param  expected    The expected result for the test; if {@code null}
     *      the test should fail.
     *  @param  description The description for this test.
     */
    protected TestDataBase( final E expected, final String description )
    {
        m_Description = Optional.ofNullable( description );
        m_Expected = Optional.ofNullable( expected );
    }   //  TestDataBase()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the description for the test.
     *
     *  @return An instance of
     *      {@link Optional}
     *      that holds the description.
     */
    public final Optional<String> description() { return m_Description; }

    /**
     *  Returns the expected result for the test. An empty return value
     *  indicates that the test should fail.
     *
     *  @return An instance of
     *      {@link Optional}
     *      that holds the expected result.
     */
    public final Optional<E> expected() { return m_Expected; }

    /**
     *  Indicates whether the test should fail.
     *
     *  @return {@code true} if the test should fail, {@code false} if it
     *      should be successful.
     */
    public final boolean shouldFail() { return m_Expected.isEmpty(); }
}
//  class TestDataBase

/*
 *  End of File
 */