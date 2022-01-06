/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
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

import static java.lang.String.format;
import static java.lang.Thread.getAllStackTraces;
import static java.lang.reflect.AccessibleObject.setAccessible;
import static java.lang.reflect.Modifier.isStatic;
import static java.lang.reflect.Modifier.isTransient;
import static java.util.Arrays.asList;
import static java.util.Arrays.deepToString;
import static java.util.Collections.emptySet;
import static java.util.Objects.deepEquals;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.apiguardian.api.API;

/**
 *  Some methods that are useful in the context of testing.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestUtils.java 840 2021-01-10 21:37:03Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( {"UtilityClass", "UtilityClassCanBeEnum", "MethodCanBeVariableArityMethod", "UseOfObsoleteDateTimeApi"} )
@API( status = STABLE, since = "0.0.5" )
public final class TestUtils
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The system property for the code of country for the current user:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_USER_COUNTRY = "user.country";

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The flag that tracks the assertion on/off status for this package.
     */
    private static boolean m_AssertionOn;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The empty string.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String EMPTY_STRING = "";

    /**
     *  An empty array of
     *  {@link String}
     *  instances.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String [] EMPTY_String_ARRAY = new String [0];

    /**
     *  A String containing the sequence &quot;null&quot;.
     */
    /*
     * The cast is required because if omitted, String.valueOf( char [] ) would
     * be called. This in turn would cause an unwanted NullPointerException.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String NULL_STRING = String.valueOf( (Object) null );

    static
    {
        //---* Determine the assertion status *--------------------------------
        m_AssertionOn = false;
        /*
         * As the JUnit tests will always be executed with the flag
         * "-ea" (assertions enabled), this code sequence is not tested in all
         * branches.
         */
        //noinspection AssertWithSideEffects,PointlessBooleanExpression,NestedAssignment
        assert (m_AssertionOn = true) == true : "Assertion is switched off";
    }

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class.
     */
    private TestUtils() { /* Just exists */ }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns all threads that are currently alive.
     *
     *  @return The living threads.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Collection<Thread> getLiveThreads()
    {
        final var retValue = getAllStackTraces().keySet();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getLiveThreads()

    /**
     *  Checks whether JDK assertion is currently activated, meaning that the
     *  program was started with the command line flags {@code -ea} or
     *  {@code -enableassertions}. If assertions are activated for some
     *  selected packages only and {@code org.tquadrat.test} is not amongst
     *  these, or {@code org.tquadrat.test} is explicitly disabled with
     *  {@code -da} or {@code -disableassertions}, this method will return
     *  {@code false}. But even it may return {@code true}, it is possible that
     *  assertions are still not activated for some packages.
     *
     *  @return {@code true} if assertions are activated for the
     *      package {@code org.tquadrat.foundation.test} and hopefully also for
     *      any other package, {@code false} otherwise.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean isAssertionOn() { return m_AssertionOn; }

    /**
     *  Tests if the given String is {@code null} or the empty String.
     *
     *  @param  s   The String to test.
     *  @return {@code true} if the given String reference is
     *      {@code null} or the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final boolean isEmpty( final CharSequence s ) { return isNull( s ) || s.isEmpty(); }

    /**
     *  Tests if the given String is {@code null}, the empty String, or just
     *  containing whitespace.
     *
     *  @param  s   The String to test.
     *  @return {@code true} if the given String reference is not
     *      {@code null} and not the empty String.
     *
     *  @see String#isBlank()
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final boolean isEmptyOrBlank( final CharSequence s )
    {
        final var retValue = isNull( s ) || s.toString().isBlank();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  isEmptyOrBlank()

    /**
     *  Tests if the given String is not {@code null} and not the empty
     *  String.
     *
     *  @param  s   The String to test.
     *  @return {@code true} if the given String reference is not
     *      {@code null} and not the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final boolean isNotEmpty( final CharSequence s ) { return nonNull( s ) && !s.isEmpty(); }

    /**
     *  Tests if the given String is not {@code null}, not the empty String,
     *  and that it contains other characters than just whitespace.
     *
     *  @param  s   The String to test.
     *  @return {@code true} if the given String reference is not
     *      {@code null} and not the empty String, and it contains other
     *      characters than just whitespace.
     *
     *  @see String#isBlank()
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final boolean isNotEmptyOrBlank( final CharSequence s )
    {
        final var retValue = nonNull( s ) && !s.toString().isBlank();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  isNotEmptyOrBlank()

    /**
     *  This method uses reflection to determine if the two objects are
     *  equal.<br>
     *  <br>It uses
     *  {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)}
     *  to gain access to private fields. This means that it will throw a
     *  security exception if run under a security manager, if the permissions
     *  are not set up correctly. It is also not as efficient as testing
     *  explicitly.<br>
     *  <br>Transient members will be not be tested, as they are likely derived
     *  fields, and not part of the value of the object instance.<br>
     *  <br>Static fields will not be tested. Superclass fields will be
     *  included.
     *
     *  @param  lhs <code>this</code> object.
     *  @param  rhs The other object
     *  @return {@code true} if the two objects have tested equals,
     *      {@code false} otherwise.
     *
     *  @author Apache Software Foundation
     *  @extauthor Steve Downey - steve.downey@netfolio.com
     *  @author Gary Gregory
     *  @author Pete Gieser
     *  @author Arun Mammen Thomas
     *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean reflectionEquals( final Object lhs, final Object rhs )
    {
        return reflectionEquals( lhs, rhs, false, null, null );
    }   //  reflectionEquals()

    /**
     *  This method uses reflection to determine if the two objects are
     *  equal.<br>
     *  <br>It uses
     *  {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)}
     *  to gain access to private fields. This means that it will throw a
     *  security exception if run under a security manager and the permissions
     *  are not set up correctly. It is also not as efficient as testing
     *  explicitly.<br>
     *  <br>Transient members will be not be tested, as they are likely derived
     *  fields, and not part of the value of the object instance.<br>
     *  <br>Static fields will not be tested. Superclass fields will be
     *  included.
     *
     *  @param  lhs <code>this</code> object.
     *  @param  rhs The other object
     *  @param  excludeFields   A
     *      {@link Collection}
     *      of String field names to exclude from testing.
     *  @return {@code true} if the two objects have tested equals,
     *      {@code false} otherwise.
     *
     *  @author Apache Software Foundation
     *  @extauthor Steve Downey - steve.downey@netfolio.com
     *  @author Gary Gregory
     *  @author Pete Gieser
     *  @author Arun Mammen Thomas
     *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean reflectionEquals( final Object lhs, final Object rhs, final Collection<String> excludeFields )
    {
        return reflectionEquals( lhs, rhs, requireNonNullArgument( excludeFields, "excludeFields" ).toArray( EMPTY_String_ARRAY ) );
    }   //  reflectionEquals()

    /**
     *  This method uses reflection to determine if the two objects are
     *  equal.<br>
     *  <br>It uses
     *  {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)}
     *  to gain access to private fields. This means that it will throw a
     *  security exception if run under a security manager, if the permissions
     *  are not set up correctly. It is also not as efficient as testing
     *  explicitly.<br>
     *  <br>Transient members will be not be tested, as they are likely derived
     *  fields, and not part of the value of the object instance.<br>
     *  <br>Static fields will not be tested. Superclass fields will be
     *  included.
     *
     *  @param  lhs <code>this</code> object.
     *  @param  rhs The other object
     *  @param  excludeFields   An array of String field names to exclude from
     *      testing; may be {@code null}.
     *  @return {@code true} if the two objects have tested equals,
     *      {@code false} otherwise.
     *
     *  @author Apache Software Foundation
     *  @extauthor Steve Downey - steve.downey@netfolio.com
     *  @author Gary Gregory
     *  @author Pete Gieser
     *  @author Arun Mammen Thomas
     *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean reflectionEquals( final Object lhs, final Object rhs, final String [] excludeFields )
    {
        return reflectionEquals( lhs, rhs, false, null, excludeFields );
    }   //  reflectionEquals()

    /**
     *  This method uses reflection to determine if the two objects are
     *  equal.<br>
     *  <br>It uses
     *  {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)}
     *  to gain access to private fields. This means that it will throw a
     *  security exception if run under a security manager, if the permissions
     *  are not set up correctly. It is also not as efficient as testing
     *  explicitly.<br>
     *  <br>If the <code>testTransients</code> parameter is set to
     *  {@code true}, transient members will be tested, otherwise they are
     *  ignored, as they are likely derived fields, and not part of the value
     *  of the object instance.<br>
     *  <br>Static fields will not be tested. Superclass fields will be
     *  included.
     *
     *  @param  lhs <code>this</code> object.
     *  @param  rhs The other object
     *  @param  testTransients  {@code true} whether to include transient
     *      fields, {@code false} otherwise.
     *  @return {@code true} if the two objects have tested equals,
     *      {@code false} otherwise.
     *
     *  @author Apache Software Foundation
     *  @extauthor Steve Downey - steve.downey@netfolio.com
     *  @author Gary Gregory
     *  @author Pete Gieser
     *  @author Arun Mammen Thomas
     *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean reflectionEquals( final Object lhs, final Object rhs, final boolean testTransients )
    {
        return reflectionEquals( lhs, rhs, testTransients, null, null );
    }   //  reflectionEquals()

    /**
     *  This method uses reflection to determine if the two objects are
     *  equal.<br>
     *  <br>It uses
     *  {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)}
     *  to gain access to private fields. This means that it will throw a
     *  security exception if run under a security manager, if the permissions
     *  are not set up correctly. It is also not as efficient as testing
     *  explicitly.<br>
     *  <br>If the <code>testTransients</code> parameter is set to
     *  {@code true}, transient members will be tested, otherwise they are
     *  ignored, as they are likely derived fields, and not part of the value
     *  of the object instance.<br>
     *  <br>Static fields will not be tested. Superclass fields will be
     *  appended up to and including the specified superclass. A {@code null}
     *  superclass is treated as
     *  {@link java.lang.Object}.
     *
     *  @param  lhs <code>this</code> object.
     *  @param  rhs The other object
     *  @param  testTransients  {@code true} whether to include transient
     *      fields, {@code false} otherwise.
     *  @param  reflectUpToClass    The superclass to reflect up to
     *      (inclusive), may be {@code null}
     *  @return {@code true} if the two objects have tested equals,
     *      {@code false} otherwise.
     *
     *  @author Apache Software Foundation
     *  @extauthor Steve Downey - steve.downey@netfolio.com
     *  @author Gary Gregory
     *  @author Pete Gieser
     *  @author Arun Mammen Thomas
     *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean reflectionEquals( final Object lhs, final Object rhs, final boolean testTransients, final Class<?> reflectUpToClass )
    {
        return reflectionEquals( lhs, rhs, testTransients, reflectUpToClass, null );
    }   //  reflectionEquals()

    /**
     *  This method uses reflection to determine if the two objects are
     *  equal.<br>
     *  <br>It uses
     *  {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)}
     *  to gain access to private fields. This means that it will throw a
     *  security exception if run under a security manager, if the permissions
     *  are not set up correctly. It is also not as efficient as testing
     *  explicitly.<br>
     *  <br>If the <code>testTransients</code> parameter is set to
     *  {@code true}, transient members will be tested, otherwise they are
     *  ignored, as they are likely derived fields, and not part of the value
     *  of the object instance.<br>
     *  <br>Static fields will not be tested. Superclass fields will be
     *  appended up to and including the specified superclass. A {@code null}
     *  superclass is treated as
     *  {@link java.lang.Object}.
     *
     *  @param  lhs <code>this</code> object.
     *  @param  rhs The other object
     *  @param  testTransients  {@code true} whether to include transient
     *      fields, {@code false} otherwise.
     *  @param  reflectUpToClass    The superclass to reflect up to
     *      (inclusive), may be {@code null}
     *  @param  excludeFields   An array of String field names to exclude from
     *      testing; may be {@code null}.
     *  @return {@code true} if the two objects have tested equals,
     *      {@code false} otherwise.
     *
     *  @author Apache Software Foundation
     *  @extauthor Steve Downey - steve.downey@netfolio.com
     *  @author Gary Gregory
     *  @author Pete Gieser
     *  @author Arun Mammen Thomas
     *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean reflectionEquals( final Object lhs, final Object rhs, final boolean testTransients, final Class<?> reflectUpToClass, final String [] excludeFields )
    {
        var retValue = lhs == rhs;
        if( !retValue && nonNull( lhs ) && nonNull( rhs ) )
        {
            /*
             * Find the leaf class since there may be transients in the leaf
             * class or in classes between the leaf and root. If we are not
             * testing transients or a subclass has no ivars, then a subclass
             * can test equals to a superclass.
             */
            final var lhsClass = lhs.getClass();
            final var rhsClass = rhs.getClass();
            Class<?> testClass = null;
            if( lhsClass.isInstance( rhs ) )
            {
                testClass = lhsClass;
                if( !rhsClass.isInstance( lhs ) )
                {
                    //---* rhsClass is a subclass of lhsClass *----------------
                    testClass = rhsClass;
                }
            }
            else if( rhsClass.isInstance( lhs ) )
            {
                testClass = rhsClass;
                if( !lhsClass.isInstance( rhs ) )
                {
                    //---* lhsClass is a subclass of rhsClass *----------------
                    testClass = lhsClass;
                }
            }

            if( nonNull( testClass ) )
            {
                //---* The two classes are related *---------------------------
                final Set<String> excluded = nonNull( excludeFields ) ? new HashSet<>( asList( excludeFields ) ) : emptySet();
                try
                {
                    retValue = testReflective( lhs, rhs, testClass, testTransients, excluded );
                    while( retValue && nonNull( testClass.getSuperclass() ) && (testClass != reflectUpToClass) )
                    {
                        testClass = testClass.getSuperclass();
                        retValue = testReflective( lhs, rhs, testClass, testTransients, excluded );
                    }
                }
                catch( @SuppressWarnings( "unused" ) final IllegalArgumentException e )
                {
                    /*
                     * In this case, we tried to test a subclass vs. a
                     * superclass and the subclass has ivars or the ivars are
                     * transient, and we are testing transients.
                     * If a subclass has ivars that we are trying to test them,
                     * we get an exception, and we know that the objects are not
                     * equal.
                     */
                    retValue = false;
                }
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  reflectionEquals()

    /**
     *  Checks if the given value {@code a} is {@code null} and throws
     *  a
     *  {@link NullPointerException}
     *  if it is {@code null}; calls
     *  {@link java.util.Objects#requireNonNull(Object)}
     *  internally.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check.
     *  @return The value if it is not {@code null}.
     *  @throws NullPointerException   {@code a} is {@code null}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNull( final T a ) { return java.util.Objects.requireNonNull( a ); }

    /**
     *  Checks if the given value {@code a} is {@code null} and throws
     *  a
     *  {@link NullPointerException}
     *  with the specified message if it is {@code null}. Calls
     *  {@link java.util.Objects#requireNonNull(Object, String)}
     *  internally.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check.
     *  @param  message The message that is set to the thrown exception.
     *  @return The value if it is not {@code null}.
     *  @throws NullPointerException   {@code a} is {@code null}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNull( final T a, final String message ) { return java.util.Objects.requireNonNull( a, message ); }

    /**
     *  Checks if the given argument {@code a} is {@code null} and throws
     *  a
     *  {@link NullPointerException}
     *  if it is {@code null}.
     *
     *  @param  <T> The type of the argument to check.
     *  @param  a   The argument to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@code null}.
     *  @throws IllegalArgumentException    {@code name} is empty.
     *  @throws NullPointerException   {@code name} or {@code a} is
     *      {@code null}.
     */
    @SuppressWarnings( "ProhibitedExceptionThrown" )
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullArgument( final T a, final String name )
    {
        if( isNull( name) )
        {
            throw new NullPointerException( "name is null" );
        }
        else if( name.isEmpty() )
        {
            throw new IllegalArgumentException( "name is empty" );
        }
        if( isNull( a ) )
        {
            throw new NullPointerException( format( "Argument '%s' is null", name ) );
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  requireNonNullArgument()

    /**
     *  Checks if the given argument {@code a} is {@code null} or empty
     *  and throws a
     *  {@link NullPointerException}
     *  if it is {@code null}, or a
     *  {@link IllegalArgumentException}
     *  if it is empty.<br>
     *  <br>Only Strings, arrays,
     *  {@link java.util.Collection}s, and
     *  {@link java.util.Map}s
     *  will be checked on being empty.<br>
     *  <br>Because the interface
     *  {@link java.util.Enumeration}
     *  does not provide an API for the check on emptiness
     *  ({@link java.util.Enumeration#hasMoreElements() hasMoreElements()}
     *  will return {@code false} after all elements have been taken from
     *  the {@code Enumeration} instance), the result for arguments of this
     *  type has to be taken with caution.<br>
     *  <br>For instances of
     *  {@link java.util.stream.Stream},
     *  the method
     *  {@link java.util.stream.Stream#findAny()}
     *  is used to determine if the stream has elements. Using
     *  {@link java.util.stream.Stream#count() count()}
     *  may have negative impact on performance if the argument has a large
     *  amount of elements.
     *
     *  @param  <T> The type of the argument to check.
     *  @param  a   The argument to check; may be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@code null}.
     *  @throws NullPointerException   {@code name} or {@code a} is
     *      {@code null}.
     *  @throws IllegalArgumentException   {@code name} or {@code a} is empty.
     */
    @SuppressWarnings( "ProhibitedExceptionThrown" )
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNotEmptyArgument( final T a, final String name )
    {
        if( isNull( name ) )
        {
            throw new NullPointerException( "name is null" );
        }
        else if( name.isEmpty() )
        {
            throw new IllegalArgumentException( "name is empty" );
        }

        //---* Check for null *------------------------------------------------
        if( isNull( a ) )
        {
            throw new NullPointerException( format( "Argument '%s' is null", name ) );
        }

        //---* Check the type *------------------------------------------------
        //noinspection IfStatementWithTooManyBranches
        if( a instanceof CharSequence charSequence )
        {
            if( charSequence.isEmpty() )
            {
                throw new IllegalArgumentException( format( "Argument '%s' is empty", name ) );
            }
        }
        else if( a.getClass().isArray() )
        {
            if( Array.getLength( a ) == 0 )
            {
                throw new IllegalArgumentException( format( "Argument '%s' is empty", name ) );
            }
        }
        else if( a instanceof Collection<?> collection )
        {
            if( collection.isEmpty() )
            {
                throw new IllegalArgumentException( format( "Argument '%s' is empty", name ) );
            }
        }
        else if( a instanceof Map<?,?> map )
        {
            if( map.isEmpty() )
            {
                throw new IllegalArgumentException( format( "Argument '%s' is empty", name ) );
            }
        }
        else if( a instanceof Enumeration<?> enumeration )
        {
            if( !enumeration.hasMoreElements() )
            {
                throw new IllegalArgumentException( format( "Argument '%s' is empty", name ) );
            }
        }
        else if( a instanceof Stream<?> stream )
        {
            final var o = stream.findAny();
            if( o.isEmpty() )
            {
                throw new IllegalArgumentException( format( "Argument '%s' is empty", name ) );
            }
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  requireNotEmptyArgument()

    /**
     *  Tests the fields on the given instances on equal.
     *
     *  @param  lhs The left-hand object.
     *  @param  rhs The right-hand object.
     *  @param  testClass   The class that defines the details.
     *  @param  useTransients   {@code true} if to test transient fields
     *      also, {@code false} otherwise.
     *  @param  excludeFields   Set of field names to exclude from testing.
     *  @return {@code true} if all relevant fields are equal,
     *      {@code false} otherwise.
     */
    @SuppressWarnings( "OverlyComplexBooleanExpression" )
    private static boolean testReflective( final Object lhs, final Object rhs, final Class<?> testClass, final boolean useTransients, final Collection<String> excludeFields )
    {
        final var fields = testClass.getDeclaredFields();
        setAccessible( fields, true );
        var retValue = true;
        for( var i = 0; (i < fields.length) && retValue; ++i )
        {
            final var f = fields [i];
            final var modifiers = f.getModifiers();
            if( !excludeFields.contains( f.getName() )
                && (f.getName().indexOf( '$' ) == -1)
                && (useTransients || !isTransient( modifiers ))
                && (!isStatic( modifiers )) )
            {
                try
                {
                    retValue = deepEquals( f.get( lhs ), f.get( rhs ) );
                }
                catch( final IllegalAccessException e )
                {
                    /*
                     * This can't happen. We would get a SecurityException
                     * instead. But we prefer to throw a runtime exception in
                     * case the impossible happens, instead of silently
                     * swallowing it.
                     */
                    throw new InternalError( "Unexpected IllegalAccessException", e );
                }
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  testReflective()

    /**
     *  Converts the given argument {@code object} into a
     *  {@link String},
     *  usually by calling its
     *  {@link Object#toString() toString()}
     *  method. If the value of the argument is {@code null}, the text
     *  &quot;null&quot; will be returned instead. Arrays will be
     *  converted to a string through calling the respective {@code toString()}
     *  method from
     *  {@link java.util.Arrays}
     *  (this distinguishes this implementation from
     *  {link java.util.Objects#toString(Object, String)}).
     *  Values of type
     *  {@link java.util.Date} or
     *  {@link java.util.Calendar}
     *  will be translated based on the default locale - whatever that is.
     *
     *  @param  object  The object; may be {@code null}.
     *  @return The object's string representation.
     *
     *  @see java.util.Arrays#toString(boolean[])
     *  @see java.util.Arrays#toString(byte[])
     *  @see java.util.Arrays#toString(char[])
     *  @see java.util.Arrays#toString(double[])
     *  @see java.util.Arrays#toString(float[])
     *  @see java.util.Arrays#toString(int[])
     *  @see java.util.Arrays#toString(long[])
     *  @see java.util.Arrays#toString(Object[])
     *  @see java.util.Arrays#toString(short[])
     *  @see java.util.Arrays#deepToString(Object[])
     *  @see java.util.Locale#getDefault()
     *  @see #NULL_STRING
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String toString( final Object object )
    {
        return toString( object, NULL_STRING );
    }   //  toString()

    /**
     *  Converts the given argument {@code object} into a
     *  {@link String},
     *  usually by calling its
     *  {@link Object#toString() toString()}
     *  method. If the value of the argument is {@code null}, the text
     *  provided as the {@code nullDefault} argument will be returned instead.
     *  Arrays will be converted to a string through calling the respective
     *  {@code toString()} method from
     *  {@link java.util.Arrays}
     *  (this distinguishes this implementation from
     *  {link java.util.Objects#toString(Object, String)}).
     *  Values of type
     *  {@link java.util.Date} or
     *  {@link java.util.Calendar}
     *  will be translated based on the default locale - whatever that is.
     *
     *  @param  object  The object; may be {@code null}.
     *  @param  nullDefault The text that should be returned if {@code object}
     *      is {@code null}.
     *  @return The object's string representation.
     *
     *  @see java.util.Arrays#toString(boolean[])
     *  @see java.util.Arrays#toString(byte[])
     *  @see java.util.Arrays#toString(char[])
     *  @see java.util.Arrays#toString(double[])
     *  @see java.util.Arrays#toString(float[])
     *  @see java.util.Arrays#toString(int[])
     *  @see java.util.Arrays#toString(long[])
     *  @see java.util.Arrays#toString(Object[])
     *  @see java.util.Arrays#toString(short[])
     *  @see java.util.Arrays#deepToString(Object[])
     *  @see java.util.Locale#getDefault()
     */
    @SuppressWarnings( {"IfStatementWithTooManyBranches", "ChainOfInstanceofChecks"} )
    @API( status = STABLE, since = "0.0.5" )
    public static final String toString( final Object object, final String nullDefault )
    {
        var retValue = requireNonNullArgument( nullDefault, "nullDefault" );
        if( nonNull( object ) )
        {
            final var objectClass = object.getClass();
            if( objectClass.isArray() )
            {
                if( objectClass == byte [].class )
                {
                    retValue = Arrays.toString( (byte []) object );
                }
                else if( objectClass == short [].class )
                {
                    retValue = Arrays.toString( (short []) object );
                }
                else if( objectClass == int [].class )
                {
                    retValue = Arrays.toString( (int []) object );
                }
                else if( objectClass == long [].class )
                {
                    retValue = Arrays.toString( (long []) object );
                }
                else if( objectClass == char [].class )
                {
                    retValue = Arrays.toString( (char []) object );
                }
                else if( objectClass == float [].class )
                {
                    retValue = Arrays.toString( (float []) object );
                }
                else if( objectClass == double [].class )
                {
                    retValue = Arrays.toString( (double []) object );
                }
                else if( objectClass == boolean [].class )
                {
                    retValue = Arrays.toString( (boolean []) object );
                }
                else
                {
                    retValue = deepToString( (Object []) object );
                }
            }
            else
            {
                retValue = object.toString();
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class TestUtils

/*
 *  End of File
 */