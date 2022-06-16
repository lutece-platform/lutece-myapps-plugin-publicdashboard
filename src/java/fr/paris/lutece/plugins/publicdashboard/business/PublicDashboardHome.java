/*
 * Copyright (c) 2002-2022, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

package fr.paris.lutece.plugins.publicdashboard.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class provides instances management methods (create, find, ...) for Dashboard objects
 */
public final class PublicDashboardHome
{
	private static final String DASHBOARD_DAO_BEAN = "componentdashboard.dashboardDAO";
	private static final String DASHBOARD_PLUGIN = "componentdashboard";
    // Static variable pointed at the DAO instance
    private static IPublicDashboardDAO _dao = SpringContextService.getBean( DASHBOARD_DAO_BEAN );
    private static Plugin _plugin = PluginService.getPlugin( DASHBOARD_PLUGIN );

    /**
     * Private constructor - this class need not be instantiated
     */
    private PublicDashboardHome( )
    {
    }

    /**
     * Create an instance of the dashboard class
     * 
     * @param dashboard
     *            The instance of the Dashboard which contains the informations to store
     * @return The instance of dashboard which has been created with its primary key.
     */
    public static PublicDashboard create( PublicDashboard dashboard )
    {
        _dao.insert( dashboard, _plugin );

        return dashboard;
    }

    /**
     * Update of the dashboard which is specified in parameter
     * 
     * @param dashboard
     *            The instance of the Dashboard which contains the data to store
     * @return The instance of the dashboard which has been updated
     */
    public static PublicDashboard update( PublicDashboard dashboard )
    {
        _dao.store( dashboard, _plugin );

        return dashboard;
    }

    /**
     * Remove the dashboard whose identifier is specified in parameter
     * 
     * @param nKey
     *            The dashboard Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    /**
     * Returns an instance of a dashboard whose identifier is specified in parameter
     * 
     * @param nKey
     *            The dashboard primary key
     * @return an instance of Dashboard
     */
    public static Optional<PublicDashboard> findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the dashboard objects and returns them as a list
     * 
     * @return the list which contains the data of all the dashboard objects
     */
    public static List<PublicDashboard> getDashboardsList( )
    {
        return _dao.selectDashboardsList( _plugin );
    }

    /**
     * Load the id of all the dashboard objects and returns them as a list
     * 
     * @return the list which contains the id of all the dashboard objects
     */
    public static List<Integer> getIdDashboardsList( )
    {
        return _dao.selectIdDashboardsList( _plugin );
    }

    /**
     * Load the data of all the dashboard objects and returns them as a referenceList
     * 
     * @return the referenceList which contains the data of all the dashboard objects
     */
    public static ReferenceList getDashboardsReferenceList( )
    {
        return _dao.selectDashboardsReferenceList( _plugin );
    }

    /**
     * Load the data of all the avant objects and returns them as a list
     * 
     * @param listIds
     *            liste of ids
     * @return the list which contains the data of all the avant objects
     */
    public static List<PublicDashboard> getDashboardsListByIds( List<Integer> listIds )
    {
        return _dao.selectDashboardsListByIds( _plugin, listIds );
    }

    /**
     * Load the id of all the dashboard objects and returns them as a list
     * 
     * @return the list which contains the id of all the dashboard objects
     */
    public static List<Integer> getIdDashboardsListByPosition( )
    {
        return _dao.selectIdDashboardsListByPosition( _plugin );
    }

    /**
     * Load the data of all the publicDashboard objects and returns them as a list
     * 
     * @return the list which contains the data of all the publicDashboard objects
     */
    public static List<PublicDashboard> getDashboardsListByPosition( )
    {
        return _dao.selectDashboardsListByPosition( _plugin );
    }

    /**
     * Load the data of all the publicDashboard objects and returns them as a list
     * 
     * @return the list which contains the data of all the publicDashboard objects
     */
    public static List<PublicDashboard> getDashboardsListFromZone( int zone )
    {
        return _dao.selectDashboardsListByZone( _plugin, zone );
    }

    /**
     * Load the data of all Dashboard and returns them as a map with the zone of the dashboard as key
     * 
     * @return all Dashboard and returns them as a map with the zone of the dashboard as key
     */
    public static Map<String, List<PublicDashboard>> getMapZoneDashboard( List<PublicDashboard> lstPublicDashboard )
    {
        Map<String, List<PublicDashboard>> mapZoneDashboard = new HashMap<>( );
        for ( PublicDashboard dashboard : lstPublicDashboard )
        {
            if ( mapZoneDashboard.containsKey( String.valueOf( dashboard.getZone( ) ) ) )
            {
                mapZoneDashboard.get( String.valueOf( dashboard.getZone( ) ) ).add( dashboard );
            }
            else
            {
                List<PublicDashboard> lstPublicDashboardZone = new ArrayList<>( );
                lstPublicDashboardZone.add( dashboard );
                mapZoneDashboard.put( String.valueOf( dashboard.getZone( ) ), lstPublicDashboardZone );
            }
        }

        return mapZoneDashboard;
    }

}
